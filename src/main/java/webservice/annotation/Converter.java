package webservice.annotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kobjects.base64.Base64;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import webservice.annotation.soap.SoapXmlElementList;
import webservice.annotation.soap.SoapXmlElementMap;
import webservice.annotation.soap.SoapXmlElement;
import webservice.annotation.soap.SoapXmlType;
import webservice.utils.DateUtil;

public class Converter {
	
    /**
     * 根据注解将Object转化为SoapObject发送内容
     */
	public static Object convertToObj(String namespace, Object rootObj) {
		if (rootObj == null) {
			return null;
		}
		
		// 根据获取类Type注解
		SoapXmlType soapXmlType = rootObj.getClass().getAnnotation(SoapXmlType.class);
		if (soapXmlType == null) {
			return encodeData(rootObj.getClass(), rootObj);
		} 
			
		return convertToSoapObj(namespace, rootObj);
	}
	
    /**
     * 根据Soap注解将Object转化为SoapObject对象。
     */
	public static SoapObject convertToSoapObj(String namespace, Object rootObj) {
		if (rootObj == null) {
			return null;
		}

		// 根据获取类Type注解
		SoapXmlType soapXmlType = rootObj.getClass().getAnnotation(SoapXmlType.class);
		if (soapXmlType == null) {
			return null;
		}
		
		// 构造SoapObject对象
		SoapObject rootSoapObj = new SoapObject(namespace, soapXmlType.name());

		// 遍历注解域
		Field[] rootFields = rootObj.getClass().getDeclaredFields();
		
		for (Field field : rootFields) {
			field.setAccessible(true);

			// 根据注解转化为SoapObject中的数据
			try {
				SoapXmlElement soapXml = field.getAnnotation(SoapXmlElement.class);
				if (soapXml == null) {
					continue;
				}
				
				Object childObj = field.get(rootObj);
				if (childObj == null) {
					continue;
				}

				// 根据注解进行解析
				String varName = soapXml.name();
				if (varName == null || varName.isEmpty()) {
					varName = field.getName();
				}
				
				// 判断父类元素中是否包含注解,以此深度遍历
				SoapXmlType childSoapXmlType = childObj.getClass().getAnnotation(SoapXmlType.class);
				if (childSoapXmlType == null) {
					rootSoapObj.addProperty(varName, encodeData(field.getType(), childObj));
				} else {
					rootSoapObj.addProperty(varName, convertToSoapObj(namespace, childObj));
				}
				
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}

		return rootSoapObj;
	}

    /**
     * 根据Soap注解将SoapObject转化为Object对象。
     */
	public static Object convertFromSoapObj(SoapObject rootSoapObj, Class<?> clazz) {
		if (null == rootSoapObj) {
			return null;
		}

		Object rootObj = null;
		try {
			rootObj = clazz.getConstructor(new Class[] {}).newInstance(new Object[] {});
		} catch (Exception e) {
			//e.printStackTrace();
		}

		if (rootObj == null) {
			return null;
		}

		return parseSoapData(rootObj, rootSoapObj);
	}

    /**
     * 根据Soap注解将PropertyInfo和目标Class转化为Object对象。
     */
	public static Object convertFromPropertyInfo(PropertyInfo targetPropertyInfo, Class<?> clazz) {
		if (targetPropertyInfo == null) {
			return null;
		}

		Object rootObj = targetPropertyInfo.getValue();
		if (rootObj == null) {
			return null;
		}

		SoapObject rootSoapObj = null;
		if (rootObj instanceof SoapObject) {
			rootSoapObj = (SoapObject) rootObj;

			try {
				rootObj = clazz.getConstructor(new Class[] {}).newInstance(new Object[] {});
			} catch (Exception e) {
				//e.printStackTrace();
			}

			if (rootObj == null) {
				return null;
			}
		} else {
			return rootObj;
		}

		return parseSoapData(rootObj, rootSoapObj);
	}

	private static Object parseSoapData(Object rootObj, SoapObject rootSoapObj) {
		// 构建SoapObject中的PropertyInfo信息
		HashMap<String, ArrayList<PropertyInfo>> rootPropertyMap = new HashMap<String, ArrayList<PropertyInfo>>();
		for (int i = 0; i < rootSoapObj.getPropertyCount(); i++) {
			PropertyInfo propertyInfo = new PropertyInfo();
			rootSoapObj.getPropertyInfo(i, propertyInfo);
			
			if(!rootPropertyMap.containsKey(propertyInfo.getName())){
				rootPropertyMap.put(propertyInfo.getName(), new ArrayList<PropertyInfo>());
	        }
			
			rootPropertyMap.get(propertyInfo.getName()).add(propertyInfo);
		}

		// 根据注解，遍历解析数据
		Field[] fields = rootObj.getClass().getDeclaredFields();
		
		for (Field field : fields) {
			field.setAccessible(true);
			
			try {
				SoapXmlElement soapXml = field.getAnnotation(SoapXmlElement.class);
				if (soapXml == null) {
					continue;
				}

				// 根据注解进行解析
				String varName = soapXml.name();
				if (varName == null || varName.isEmpty()) {
					varName = field.getName();
				}
				
				Object childObj = rootSoapObj.getProperty(varName);
				if (childObj == null) {
					continue;
				}

				if (childObj instanceof SoapObject) {
					Object dataObj = field.get(rootObj);
									
					// 判断父类元素中是否包含注解,以此深度遍历
					SoapXmlElementMap soapMapXmlElement = field.getAnnotation(SoapXmlElementMap.class);
					SoapXmlElementList SoapListXmlElement = field.getAnnotation(SoapXmlElementList.class);
					
					if (soapMapXmlElement == null && SoapListXmlElement == null) {
						dataObj = convertFromPropertyInfo(rootPropertyMap.get(varName).get(0), field.getType());
					} else if (soapMapXmlElement != null) {
						dataObj = parseMapData(soapMapXmlElement, rootPropertyMap.get(varName).get(0));
					} else if (SoapListXmlElement != null) {
						dataObj = parseListData(SoapListXmlElement, rootPropertyMap.get(varName));
					}

					field.set(rootObj, dataObj);
				} else if (childObj instanceof SoapPrimitive) {
					Object dataObj = parseData(field.getType(), (SoapPrimitive)childObj);

					field.set(rootObj, dataObj);
				}

			} catch (Exception e) {
				//e.printStackTrace();
			}
		}

		return rootObj;
	}

	private static Object parseMapData(SoapXmlElementMap soapMapXmlElement, PropertyInfo targetPropertyInfo) {
		Map<Object, Object> mapData = new HashMap<Object, Object>();

		SoapObject rootSoapObj = (SoapObject) targetPropertyInfo.getValue();
		if (rootSoapObj == null) {
			return null;
		}

		for (int i = 0; i < rootSoapObj.getPropertyCount(); i++) {
			PropertyInfo childPropertyInfo = new PropertyInfo();
			rootSoapObj.getPropertyInfo(i, childPropertyInfo);

			SoapObject childSoapObj = (SoapObject) childPropertyInfo.getValue();
			if (childSoapObj == null) {
				continue;
			}
			
			// 获取Key
			Object keyObj = childSoapObj.getProperty(soapMapXmlElement.key());
			if (keyObj instanceof SoapObject) {
				keyObj = convertFromSoapObj((SoapObject) keyObj, soapMapXmlElement.valueType());
			} else if (keyObj instanceof SoapPrimitive) {
				keyObj = parseData(soapMapXmlElement.keyType(), (SoapPrimitive) keyObj);
			}

			// 获取Value
			Object valueObj = childSoapObj.getProperty(soapMapXmlElement.value());
			if (valueObj instanceof SoapObject) {
				valueObj = convertFromSoapObj((SoapObject) valueObj, soapMapXmlElement.valueType());
			} else if (valueObj instanceof SoapPrimitive) {
				valueObj = parseData(soapMapXmlElement.keyType(), (SoapPrimitive)valueObj);
			}

			mapData.put(keyObj, valueObj);
		}

		return mapData;
	}
	
	
	private static Object parseListData(SoapXmlElementList soapListXmlElement, List<PropertyInfo> targetPropertyInfos) {
		List<Object> listData = new ArrayList<Object>();

		for (PropertyInfo propertyInfo : targetPropertyInfos) {
			if (propertyInfo != null) {
				SoapObject rootSoapObj = (SoapObject) propertyInfo.getValue();
				if (rootSoapObj != null) {
					Object dataObj = convertFromSoapObj(rootSoapObj, soapListXmlElement.dataType());
					if (dataObj != null) {
						listData.add(dataObj);
					}
				}
			}
		}

		return listData;
	}
	
	private static Object parseData(Class<?> type, SoapPrimitive soapPrimit) {
		Object dataObj = null;

		if (byte[].class == type) {
			dataObj = Base64.decode((String) soapPrimit.getValue());
		} else if (byte.class == type || Byte.class == type) {
			dataObj = Byte.valueOf((String) soapPrimit.getValue());
		} else if (int.class == type || Integer.class == type) {
			dataObj = Integer.valueOf((String) soapPrimit.getValue());
		} else if (long.class == type || Long.class == type) {
			dataObj = Long.valueOf((String) soapPrimit.getValue());
		} else if (float.class == type || Float.class == type) {
			dataObj = Float.valueOf((String) soapPrimit.getValue());
		} else if (boolean.class == type || Boolean.class == type) {
			dataObj = Boolean.valueOf((String) soapPrimit.getValue());
		} else if (String.class == type) {
			dataObj = String.valueOf(soapPrimit.getValue());
		} else if (Date.class == type) {
			dataObj = DateUtil.getInstance().parse((String) soapPrimit.getValue());
		}

		return dataObj;
	}
	
	private static Object encodeData(Class<?> type, Object childObj) {
		Object dataObj = null;
		
		if (byte[].class == type) {
			dataObj = new SoapPrimitive(SoapEnvelope.ENC, "base64Binary", Base64.encode((byte[]) childObj));
		} else if (byte.class == type) {
			dataObj = new SoapPrimitive(SoapEnvelope.ENC, "byte", String.valueOf(childObj));
		} else if (int.class == type) {
			dataObj = new SoapPrimitive(SoapEnvelope.ENC, "int", String.valueOf(childObj));
		} else if (long.class == type) {
			dataObj = new SoapPrimitive(SoapEnvelope.ENC, "long", String.valueOf(childObj));
		} else if (float.class == type) {
			dataObj = new SoapPrimitive(SoapEnvelope.ENC, "float", String.valueOf(childObj));
		} else if (double.class == type) {
			dataObj = new SoapPrimitive(SoapEnvelope.ENC, "double", String.valueOf(childObj));
		} else if (boolean.class == type) {
			dataObj = new SoapPrimitive(SoapEnvelope.ENC, "boolean", String.valueOf(childObj));
		} else {
			dataObj = childObj;
		}
		
		return dataObj;
	}
}

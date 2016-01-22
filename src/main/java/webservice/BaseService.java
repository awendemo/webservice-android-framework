package webservice;

import java.net.Proxy;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import webservice.annotation.Converter;

public class BaseService {
	private String SERVICE_NAMESPACE = ""; 

	private String SERVICE_URL = "";

	private Proxy proxy = null;

	public boolean init(Proxy proxy, String SERVICE_NAMESPACE, String SERVICE_URL) {
		if (null != proxy) {
			this.proxy = proxy;
		}

		if (null != SERVICE_NAMESPACE) {
			this.SERVICE_NAMESPACE = SERVICE_NAMESPACE;
		}

		if (null != SERVICE_URL) {
			this.SERVICE_URL = SERVICE_URL;
		}

		return true;
	}
	
	public Object invoke(final String METHODNAME, Class<?> retClazz, Parameter... params) {
		SoapObject requestObj = new SoapObject(SERVICE_NAMESPACE, METHODNAME);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

		for (Parameter param : params) {
			try {
				PropertyInfo propertyInfo = new PropertyInfo();
				propertyInfo.setName(param.getDecodeName());
				propertyInfo.setNamespace(SERVICE_NAMESPACE);
				propertyInfo.setValue(Converter.convertToObj(SERVICE_NAMESPACE, param.getDecodeObject()));
				requestObj.addProperty(propertyInfo);
			} catch (Exception e) {
				//e.printStackTrace();
			}	
		}
		
		envelope.bodyOut = requestObj;
		envelope.dotNet = false;

		HttpTransportSE httpTransport = new SuperHttpTransportSE(proxy, SERVICE_URL);
		httpTransport.debug = false;

		int repeat = 3;
		do {
			try {
				httpTransport.call(null, envelope);

				return this.decodeResp((SoapObject) envelope.bodyIn, retClazz);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} while((repeat--) > 0);


		return null;
	}
	
	private Object decodeResp(SoapObject returnObj, Class<?> retClazz) {
		if (returnObj == null) {
			return null;
		}

		PropertyInfo propertyInfo = new PropertyInfo();
		returnObj.getPropertyInfo(0, propertyInfo);

		return Converter.convertFromPropertyInfo(propertyInfo, retClazz);
	}
	
	public static class Parameter {
		private Object decodeObject = null;
		private String decodeName = "";
		
		public Parameter(Object decodeObject, String decodeName) {
			this.decodeObject = decodeObject;
			this.decodeName = decodeName;
		}
		
		public Object getDecodeObject() {
			return decodeObject;
		}

		public void setDecodeObject(Object decodeObject) {
			this.decodeObject = decodeObject;
		}

		public String getDecodeName() {
			return decodeName;
		}

		public void setDecodeName(String decodeName) {
			this.decodeName = decodeName;
		}
	}
}

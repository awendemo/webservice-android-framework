package webservice.demo.office2pdfconverter.bean;

import webservice.annotation.soap.SoapXmlElement;
import webservice.annotation.soap.SoapXmlType;

@SoapXmlType
public class OfficeConvertInfo {

	@SoapXmlElement
    protected byte[] officeDocBytes;
	
	@SoapXmlElement
    protected String id;
	
	@SoapXmlElement
    protected String officeDocType;
	
	@SoapXmlElement
    protected String officeDocName;

    /**
     * 获取officeDocBytes属性的值。
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getOfficeDocBytes() {
        return officeDocBytes;
    }

    /**
     * 设置officeDocBytes属性的值。
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setOfficeDocBytes(byte[] value) {
        this.officeDocBytes = value;
    }

    /**
     * 获取id属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * 获取officeDocType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeDocType() {
        return officeDocType;
    }

    /**
     * 设置officeDocType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeDocType(String value) {
        this.officeDocType = value;
    }

    /**
     * 获取officeDocName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficeDocName() {
        return officeDocName;
    }

    /**
     * 设置officeDocName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficeDocName(String value) {
        this.officeDocName = value;
    }

}

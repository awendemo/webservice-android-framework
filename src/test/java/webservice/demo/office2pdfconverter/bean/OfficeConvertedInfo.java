package webservice.demo.office2pdfconverter.bean;

import webservice.annotation.soap.SoapXmlElement;
import webservice.annotation.soap.SoapXmlType;

@SoapXmlType
public class OfficeConvertedInfo {

	@SoapXmlElement
    protected byte[] convertedPDFBytes;
	
	@SoapXmlElement
    protected String id;

    /**
     * 获取convertedPdfBytes属性的值。
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getConvertedPDFBytes() {
        return convertedPDFBytes;
    }

    /**
     * 设置convertedPdfBytes属性的值。
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setConvertedPDFBytes(byte[] value) {
        this.convertedPDFBytes = value;
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
}

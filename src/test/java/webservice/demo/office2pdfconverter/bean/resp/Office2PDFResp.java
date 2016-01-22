package webservice.demo.office2pdfconverter.bean.resp;

import webservice.annotation.soap.SoapXmlElement;
import webservice.annotation.soap.SoapXmlType;
import webservice.demo.office2pdfconverter.bean.OfficeConvertedInfo;

@SoapXmlType
public class Office2PDFResp {

	@SoapXmlElement
    protected int code;
	
	@SoapXmlElement
    protected String resultsDesc;
	
	@SoapXmlElement
    protected OfficeConvertedInfo officeConvertedInfo;

    /**
     * 获取code属性的值。
     * 
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置code属性的值。
     * 
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * 获取error属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultsDesc() {
        return resultsDesc;
    }

    /**
     * 设置error属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultsDesc(String value) {
        this.resultsDesc = value;
    }

    /**
     * 获取officeConvertedInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OfficeConvertedInfo }
     *     
     */
    public OfficeConvertedInfo getOfficeConvertedInfo() {
        return officeConvertedInfo;
    }

    /**
     * 设置officeConvertedInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OfficeConvertedInfo }
     *     
     */
    public void setOfficeConvertedInfo(OfficeConvertedInfo value) {
        this.officeConvertedInfo = value;
    }

}

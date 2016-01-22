package webservice.demo.usermanager.bean;

import webservice.annotation.soap.SoapXmlElement;
import webservice.annotation.soap.SoapXmlType;

@SoapXmlType
public class ClientInfo {

	@SoapXmlElement
    protected String sys;
	
	@SoapXmlElement
    protected String mod;
	
	@SoapXmlElement
    protected String appId;
	
    /**
     * 获取sys属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSys() {
        return sys;
    }

    /**
     * 设置sys属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSys(String value) {
        this.sys = value;
    }

    /**
     * 获取mod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMod() {
        return mod;
    }

    /**
     * 设置mod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMod(String value) {
        this.mod = value;
    }

    /**
     * 获取appID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppId() {
        return appId;
    }

    /**
     * 设置appID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppId(String value) {
        this.appId = value;
    }

}

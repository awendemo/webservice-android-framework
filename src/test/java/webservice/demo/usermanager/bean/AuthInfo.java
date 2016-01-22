package webservice.demo.usermanager.bean;

import webservice.annotation.soap.SoapXmlElement;
import webservice.annotation.soap.SoapXmlType;

@SoapXmlType
public class AuthInfo {

	@SoapXmlElement
    protected String userAccount;
	
	@SoapXmlElement
    protected String userPassword;

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

    /**
     * 获取userPassword属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * 设置userPassword属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserPassword(String value) {
        this.userPassword = value;
    }
}

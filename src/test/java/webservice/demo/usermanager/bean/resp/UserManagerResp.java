package webservice.demo.usermanager.bean.resp;

import webservice.annotation.soap.SoapXmlElement;
import webservice.annotation.soap.SoapXmlType;
import webservice.demo.usermanager.bean.UserAccountInfo;
import webservice.demo.usermanager.bean.UserInfo;

@SoapXmlType
public class UserManagerResp {

	@SoapXmlElement
    protected int code;
    
    @SoapXmlElement
    protected String resultsDesc;
    
    @SoapXmlElement
    protected String token;
    
    @SoapXmlElement
    protected UserInfo userInfo;
    
    @SoapXmlElement
    protected UserAccountInfo accountInfo;

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

    public String getResultsDesc() {
		return resultsDesc;
	}

	public void setResultsDesc(String resultsDesc) {
		this.resultsDesc = resultsDesc;
	}

    /**
     * 获取token属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置token属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToken(String value) {
        this.token = value;
    }

    /**
     * 获取userInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UserInfo }
     *     
     */
    public UserInfo getUserInfo() {
        return userInfo;
    }

    /**
     * 设置userInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UserInfo }
     *     
     */
    public void setUserInfo(UserInfo value) {
        this.userInfo = value;
    }

	public UserAccountInfo getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(UserAccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}

}

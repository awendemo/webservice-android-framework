package webservice.demo.usermanager.bean;

import webservice.annotation.soap.SoapXmlElement;
import webservice.annotation.soap.SoapXmlType;

@SoapXmlType
public class UserAccountInfo {

	@SoapXmlElement
    protected Long useraccountRemain;
	
	@SoapXmlElement
    protected Long useraccountPermission;

    /**
     * 获取userAccountRemain属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUseraccountRemain() {
        return useraccountRemain;
    }

    /**
     * 设置userAccountRemain属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUseraccountRemain(Long value) {
        this.useraccountRemain = value;
    }

    /**
     * 获取userAccountPermission属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUseraccountPermission() {
        return useraccountPermission;
    }

    /**
     * 设置userAccountPermission属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUseraccountPermission(Long value) {
        this.useraccountPermission = value;
    }

}

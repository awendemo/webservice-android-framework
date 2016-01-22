package webservice.demo.usermanager;

import java.net.Proxy;

import webservice.demo.usermanager.bean.AuthInfo;
import webservice.demo.usermanager.bean.ClientInfo;
import webservice.demo.usermanager.bean.PasswordInfo;
import webservice.demo.usermanager.bean.ThirdpartInfo;
import webservice.demo.usermanager.bean.UserInfo;
import webservice.demo.usermanager.bean.resp.UserManagerResp;

public interface UserManager {
	public boolean init(Proxy proxy, String SERVICE_NAMESPACE, String SERVICE_URL);

	public UserManagerResp registByPhone(ClientInfo clientInfo, UserInfo userInfo);
	
	public UserManagerResp registByEmail(ClientInfo clientInfo, UserInfo userInfo);
	
	public UserManagerResp login(ClientInfo clientInfo, AuthInfo authInfo);
	
	public UserManagerResp loginByThirdPart(ClientInfo clientInfo, ThirdpartInfo thirdpartInfo);
	
	public UserManagerResp logout(String token);

	public UserManagerResp updateUserInfo(String token, UserInfo userInfo);

	public UserManagerResp queryUserInfo(String token);
	
	public UserManagerResp queryUserAccount(String token);
	
	public UserManagerResp chargeUserAccount(String token, Long count);

	public UserManagerResp changePassword(String token, PasswordInfo passwordInfo);
	
	public UserManagerResp refreshToken(String token, ClientInfo clientInfo);
}

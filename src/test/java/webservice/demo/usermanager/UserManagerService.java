package webservice.demo.usermanager;

import java.net.Proxy;

import webservice.BaseService;
import webservice.demo.usermanager.bean.AuthInfo;
import webservice.demo.usermanager.bean.ClientInfo;
import webservice.demo.usermanager.bean.PasswordInfo;
import webservice.demo.usermanager.bean.ThirdpartInfo;
import webservice.demo.usermanager.bean.UserInfo;
import webservice.demo.usermanager.bean.resp.UserManagerResp;

public class UserManagerService extends BaseService implements UserManager {
	public UserManagerService() {

	}

	@Override
	public boolean init(Proxy proxy, String SERVICE_NAMESPACE, String SERVICE_URL) {

		return super.init(proxy, SERVICE_NAMESPACE, SERVICE_URL);
	}

	@Override
	public UserManagerResp registByPhone(ClientInfo clientInfo, UserInfo userInfo) {
		final String METHODNAME = "registByPhone";

		return (UserManagerResp) super.invoke(
				METHODNAME, 
				UserManagerResp.class, 
				new Parameter(clientInfo, "clientInfo"),
				new Parameter(userInfo, "userInfo"));
	}

	@Override
	public UserManagerResp registByEmail(ClientInfo clientInfo, UserInfo userInfo) {
		final String METHODNAME = "registByEmail";

		return (UserManagerResp) super.invoke(
				METHODNAME, 
				UserManagerResp.class, 
				new Parameter(clientInfo, "clientInfo"),
				new Parameter(userInfo, "userInfo"));
	}

	@Override
	public UserManagerResp login(ClientInfo clientInfo, AuthInfo authInfo) {
		final String METHODNAME = "login";

		return (UserManagerResp) super.invoke(
				METHODNAME, 
				UserManagerResp.class, 
				new Parameter(clientInfo, "clientInfo"),
				new Parameter(authInfo, "authInfo"));
	}

	@Override
	public UserManagerResp loginByThirdPart(ClientInfo clientInfo, ThirdpartInfo thirdpartInfo) {
		final String METHODNAME = "loginByThirdPart";

		return (UserManagerResp) super.invoke(
				METHODNAME, 
				UserManagerResp.class, 
				new Parameter(clientInfo, "clientInfo"),
				new Parameter(thirdpartInfo, "thirdpartInfo"));
	}

	@Override
	public UserManagerResp logout(String token) {
		final String METHODNAME = "logout";

		return (UserManagerResp) super.invoke(
				METHODNAME, 
				UserManagerResp.class, 
				new Parameter(token, "token"));
	}

	@Override
	public UserManagerResp updateUserInfo(String token, UserInfo userInfo) {
		final String METHODNAME = "updateUserInfo";

		return (UserManagerResp) super.invoke(
				METHODNAME, 
				UserManagerResp.class, 
				new Parameter(token, "token"),
				new Parameter(userInfo, "userInfo"));
	}

	@Override
	public UserManagerResp queryUserInfo(String token) {
		final String METHODNAME = "queryUserInfo";

		return (UserManagerResp) super.invoke(
				METHODNAME, 
				UserManagerResp.class, 
				new Parameter(token, "token"));
	}

	@Override
	public UserManagerResp queryUserAccount(String token) {
		final String METHODNAME = "queryUserAccount";

		return (UserManagerResp) super.invoke(
				METHODNAME, 
				UserManagerResp.class, 
				new Parameter(token, "token"));
	}

	@Override
	public UserManagerResp chargeUserAccount(String token, Long count) {
		final String METHODNAME = "chargeUserAccount";

		return (UserManagerResp) super.invoke(
				METHODNAME, 
				UserManagerResp.class, 
				new Parameter(token, "token"),
				new Parameter(count, "count"));
	}

	@Override
	public UserManagerResp changePassword(String token, PasswordInfo passwordInfo) {
		final String METHODNAME = "changePassword";

		return (UserManagerResp) super.invoke(
				METHODNAME, 
				UserManagerResp.class, 
				new Parameter(token, "token"),
				new Parameter(passwordInfo, "passwordInfo"));
	}

	@Override
	public UserManagerResp refreshToken(String token, ClientInfo clientInfo) {
		final String METHODNAME = "refreshToken";

		return (UserManagerResp) super.invoke(
				METHODNAME, 
				UserManagerResp.class, 
				new Parameter(token, "token"),
				new Parameter(clientInfo, "clientInfo"));
	}
}

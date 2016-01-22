package webservice.utils;

import webservice.demo.office2pdfconverter.Office2PDFConverter;
import webservice.demo.office2pdfconverter.Office2PDFConverterService;
import webservice.demo.usermanager.UserManager;
import webservice.demo.usermanager.UserManagerService;

public class WebServiceHelper {
	private UserManager userManager = new UserManagerService();
	
	private Office2PDFConverter office2PDFConverter = new Office2PDFConverterService();
	
	private static WebServiceHelper instance;
	
	public static synchronized WebServiceHelper getInstance(){
		if (instance == null) {
			instance = new WebServiceHelper();
			instance.initWebService();
		}
		
		return instance;
	}
	
	public boolean initWebService() {
		userManager.init(null, 
						 "http://www.signit.cn/UserManager", 
				         "https://cloud.signit.cn/webservice/UserManager?wsdl");
		
		office2PDFConverter.init(null, 
								 "http://www.signit.cn/Office2PDFConverter", 
				                 "https://cloud.signit.cn/webservice/Office2PDFConverter?wsdl");
		
		return true;
	}
	
	public UserManager getUserManager() {
		return userManager;
	}
	
	public Office2PDFConverter getOffice2PDFConverter() {
		return office2PDFConverter;
	}
}

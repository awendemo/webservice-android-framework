package webservice.demo.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

import webservice.demo.office2pdfconverter.Office2PDFConverter;
import webservice.demo.office2pdfconverter.Office2PDFConverterService;
import webservice.demo.office2pdfconverter.bean.OfficeConvertInfo;
import webservice.demo.office2pdfconverter.bean.resp.Office2PDFResp;
import webservice.demo.usermanager.UserManager;
import webservice.demo.usermanager.UserManagerService;
import webservice.demo.usermanager.bean.AuthInfo;
import webservice.demo.usermanager.bean.ClientInfo;
import webservice.demo.usermanager.bean.resp.UserManagerResp;

public class Test {

	@SuppressWarnings("resource")
	public static byte[] getContent(String filePath) throws IOException {
		File file = new File(filePath);

		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return null;
		}

		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];

		int offset = 0;
		int numRead = 0;

		while (offset < buffer.length&& (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}

		// 确保所有数据均被读取
		if (offset != buffer.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}
		
		fi.close();
		return buffer;
	}

	public static void createFile(String path, byte[] content) throws IOException{ 
		FileOutputStream fos = new FileOutputStream(path);
		fos.write(content);  
		fos.close();  
	}  
    
	
	public static void main(String[] args)  {
		InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 8888);  
		Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); 
		     		
        String UserManagerUrl = "http://10.10.10.81:8080/eSignServerMobile/webservice/UserManager";
		UserManager userManager = new UserManagerService();
		userManager.init(proxy, null, UserManagerUrl);
		
		String office2PDFConverterUrl = "http://10.10.10.81:8080/eSignServerMobile/webservice/Office2PDFConverter";
		Office2PDFConverter office2PDFConverter = new Office2PDFConverterService();
		office2PDFConverter.init(proxy, null, office2PDFConverterUrl);
		
		AuthInfo authInfo = new AuthInfo();
		authInfo.setUserAccount("test@test.com");
		authInfo.setUserPassword("test");
		
		ClientInfo clientInfo = new ClientInfo();
		clientInfo.setAppId("appid");
		clientInfo.setMod("mod");
		clientInfo.setSys("sys");
		
		UserManagerResp userManagerResp = userManager.login(clientInfo, authInfo);
		
		OfficeConvertInfo officeConverterInfo = new OfficeConvertInfo();
		officeConverterInfo.setOfficeDocName("OK.doc");
		officeConverterInfo.setOfficeDocType("doc");
		officeConverterInfo.setId("1");
		
		try {
			officeConverterInfo.setOfficeDocBytes(getContent("D:/jcov/1.doc"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Office2PDFResp office2PDFResp = office2PDFConverter.office2PDF(userManagerResp.getToken(), officeConverterInfo);
		
		System.out.println(office2PDFResp.getCode());
		try {
			createFile("D:/1.pdf", office2PDFResp.getOfficeConvertedInfo().getConvertedPDFBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package webservice.demo.office2pdfconverter;

import java.net.Proxy;

import webservice.BaseService;
import webservice.demo.office2pdfconverter.bean.OfficeConvertInfo;
import webservice.demo.office2pdfconverter.bean.resp.Office2PDFResp;

public class Office2PDFConverterService extends BaseService implements Office2PDFConverter {

	public Office2PDFConverterService() {

	}

	@Override
	public boolean init(Proxy proxy, String SERVICE_NAMESPACE, String SERVICE_URL) {

		return super.init(proxy, SERVICE_NAMESPACE, SERVICE_URL);
	}

	@Override
	public Office2PDFResp office2PDF(String token, OfficeConvertInfo officeConvertInfo) {
		final String METHODNAME = "office2PDF";

		return (Office2PDFResp) super.invoke(
				METHODNAME, 
				Office2PDFResp.class, 
				new Parameter(token, "token"),
				new Parameter(officeConvertInfo, "officeConvertInfo"));
	}
}

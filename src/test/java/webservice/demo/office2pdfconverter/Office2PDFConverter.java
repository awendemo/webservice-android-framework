package webservice.demo.office2pdfconverter;

import java.net.Proxy;

import webservice.demo.office2pdfconverter.bean.OfficeConvertInfo;
import webservice.demo.office2pdfconverter.bean.resp.Office2PDFResp;


public interface Office2PDFConverter {
	public boolean init(Proxy proxy, String SERVICE_NAMESPACE, String SERVICE_URL);

	public Office2PDFResp office2PDF(String token, OfficeConvertInfo officeConverterInfo);
}

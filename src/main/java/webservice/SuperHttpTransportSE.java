package webservice;

import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.ServiceConnection;

import java.io.*;
import java.net.Proxy;

public class SuperHttpTransportSE extends HttpTransportSE {

    public SuperHttpTransportSE(Proxy proxy, String url) {
		super(proxy, url);
	}

	@Override
	public ServiceConnection getServiceConnection() throws IOException {
        return new SuperServiceConnectionSE(proxy, url, timeout);
    }
}

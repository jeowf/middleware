package basic.client;

import java.net.InetAddress;
import java.net.UnknownHostException;

// Singleton
public class ClientConfig {
	public static final int BUFFER_SIZE = 1024;
	
	private static volatile ClientConfig instance;
	private static Object mutex = new Object();
	
	private InetAddress hostIP;
	private int serverPort;
	
	private ClientConfig() {
		this.serverPort = 10000;
		
		try {
			this.hostIP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}


	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	
	public InetAddress getHostIP() {
		return hostIP;
	}

	public void setHostIP(InetAddress hostIP) {
		this.hostIP = hostIP;
	}


	public static ClientConfig getInstance() {
		ClientConfig result = instance;
		if (result == null) {
			synchronized (mutex) {
				result = instance;
				if (result == null)
					instance = result = new ClientConfig();
			}
		}
		return result;
	}
	
	
}

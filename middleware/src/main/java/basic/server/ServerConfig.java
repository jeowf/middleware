package basic.server;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerConfig {
	public static final int BUFFER_SIZE = 1024;
	public static final int SERVER_PORT = 10000;
	
	private static volatile ServerConfig instance;
	private static Object mutex = new Object();
	
	private InetAddress hostIP;
	private int serverPort;
	
	private ServerConfig() {
		this.serverPort = SERVER_PORT;
		
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


	public static ServerConfig getInstance() {
		ServerConfig result = instance;
		if (result == null) {
			synchronized (mutex) {
				result = instance;
				if (result == null)
					instance = result = new ServerConfig();
			}
		}
		return result;
	}
	
	
}
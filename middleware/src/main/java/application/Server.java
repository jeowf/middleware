package application;

import basic.RemoteError;
import basic.server.ServerRequestHandler;

public class Server {

	public static void main(String[] args) {
		ServerRequestHandler srh = new ServerRequestHandler();
		
		try {
			srh.receive();
		} catch (RemoteError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Server Ended");
	}
	
}

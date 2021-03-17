package application;


import basic.RemoteError;
import basic.server.ServerRequestHandler;
import lifecycle.StaticInstance;

public class Server {


	public static void main(String[] args) {
		ServerRequestHandler srh = new ServerRequestHandler();
		StaticInstance staticInstance = new StaticInstance();
		
		try {
			staticInstance.create();
			srh.receive();
		} catch (RemoteError e) {
			e.printStackTrace();
		}
		
		System.out.println("Server Ended");
	}
	
}

package application;

import basic.RemoteError;
import basic.client.ClientRequestHandler;
import basic.client.Requestor;

public class Client {

	public static void main(String[] args) {
		
		ClientRequestHandler crh = new ClientRequestHandler();
		
		Requestor r = new Requestor(crh, String.class);
		
		try {
			System.out.println((String) r.invoke(0, "constructor", 2,6,"oi"));
		} catch (RemoteError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

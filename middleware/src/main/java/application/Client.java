package application;

import java.io.IOException;

import basic.RemoteError;
import basic.client.ClientRequestHandler;
import basic.client.Requestor;

public class Client {

	public static void main(String[] args) {
		
		//ClientRequestHandler crh = new ClientRequestHandler();
		
		//Requestor r = new Requestor(crh, String.class);
		
		UserProxy up = new UserProxy();
		try {
			up.requestAOR("Kevin");
			System.out.println("Nome: " + up.getNome());
			up.setNome("Kevin Corno");			
			System.out.println("Nome: " + up.getNome());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		//System.out.println(up.getNome().);


//		
//		try {
//			System.out.println((String) r.invoke(0, "equals",
//					new Object[]{"pão"}, 
//
//					new String[] {Object.class.getName()}));
//		} catch (RemoteError e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
}

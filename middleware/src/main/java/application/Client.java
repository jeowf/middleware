package application;

import java.io.IOException;
import java.util.ArrayList;

import basic.RemoteError;
import basic.client.ClientRequestHandler;
import basic.client.Requestor;

public class Client {

	// Lista de id's dos objetos que o cliente pode utilizar
	private static ArrayList<Integer> ids = new ArrayList<Integer>();
	
	// Função para invocar o lookup e consultar um ID
	public static boolean consutarId(String objType, UserProxy up) 
	{
		try {
			up.requestAOR(objType);
		}
		catch(IOException e) 
		{
			System.out.println("Erro consultando o id do objeto");
			return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		
		ClientRequestHandler crh = new ClientRequestHandler();
		
		Requestor r = new Requestor(crh, String.class);
		
		UserProxy up = new UserProxy();
		
		consutarId("RC", up);
		
		up.setNome("Kevin Corno");
		
		System.out.println("Nome: " + up.getNome());
		
		up.setNome("RC");
		
		System.out.println("Nome: " + up.getNome());
		
		//System.out.println(up.getNome().);


//		
//		try {
//			System.out.println((String) r.invoke(0, "equals",
//					new Object[]{"pÃ£o"}, 
//
//					new String[] {Object.class.getName()}));
//		} catch (RemoteError e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
}

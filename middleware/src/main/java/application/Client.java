package application;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;


import basic.RemoteError;
import basic.client.ClientRequestHandler;
import basic.client.Requestor;

public class Client {

	private static ArrayList<String> nomes = new ArrayList<String>();
	
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
		

		nomes.add("Bruna");
		nomes.add("Francisco");
		nomes.add("João");
		nomes.add("Maria");
		nomes.add("Ana Clara");
		nomes.add("Roberval");
		nomes.add("Augusto");
		nomes.add("Caroline");
		nomes.add("Ricardo");
		nomes.add("Elenice");
		
		Random rand = new Random();
		
		ClientRequestHandler crh = new ClientRequestHandler();
		
		Requestor r = new Requestor(crh, String.class);
		
		UserProxy up = new UserProxy();
		
		consutarId("User1 Teste", up);
		
		System.out.println("Primeiro get para user1: " + up.getNome());
		up.setNome("Carlinhos");
		System.out.println("Segundo get para user1: " + up.getNome());
		
		consutarId("User2 Teste", up);
		
		System.out.println("Primeiro get para user2: " + up.getNome());
		up.setNome("Marcos");
		System.out.println("Segundo get para user2: " + up.getNome());
		
		
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

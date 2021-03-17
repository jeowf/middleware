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
		
		consutarId("RC", up);
		
		up.setNome(nomes.get(rand.nextInt(10)));

		
		consutarId("Kevin Corno", up);
		
		up.setNome(nomes.get(rand.nextInt(10)));
		
		
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

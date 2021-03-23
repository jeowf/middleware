package application;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;


import basic.RemoteError;
import basic.client.ClientRequestHandler;
import basic.client.Requestor;

public class Client {

	private static ArrayList<String> nomes = new ArrayList<String>();
	
	// Fun��o para invocar o lookup e consultar um ID
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

	
	public static void destroy(String objType, UserProxy up) {
		try {
			up.destroyOBJ(objType);
		}
		catch(IOException e) 
		{
			System.out.println("Erro Destruindo o objeto");
		}
	}
	
	
	public static void main(String[] args) {
		

		nomes.add("Bruna");
		nomes.add("Francisco");
		nomes.add("Jo�o");
		nomes.add("Maria");
		nomes.add("Ana Clara");
		nomes.add("Roberval");
		nomes.add("Augusto");
		nomes.add("Caroline");
		nomes.add("Ricardo");
		nomes.add("Elenice");
		
		Random rand = new Random();
		
		UserProxy up = new UserProxy();
		
		String name = nomes.get(rand.nextInt(10));
		
		consutarId(name, up);
		
		up.setNome(name);
		
		System.out.println(up.getNome());
		
		destroy(name, up);
		//System.out.println(up.getNome());
		
		
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

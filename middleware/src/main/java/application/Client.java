package application;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;


import basic.RemoteError;
import basic.client.ClientRequestHandler;
import basic.client.Requestor;
import extension.client.InterceptorRegistryClient;
import general.enums.InterceptorType;
import patterns.strategy.InterceptorStrategy;
import patterns.strategy.LogClientInterceptor;

public class Client {
	
	InterceptorStrategy clientInterceptorStrategy;
	
	private static InterceptorStrategy genderInterceptor( InterceptorType interceptorType ) {
		if( interceptorType.equals( InterceptorType.INTERCEPTOR_LOG ) ) {
			return new LogClientInterceptor();
			
		}
		
		return null;
		
	}
	
	public void test() {
		System.out.println( "Teste" );
		/*
		// Construindo o interceptor para log
		InterceptorStrategy clientInterceptorLog = genderInterceptor( InterceptorType.INTERCEPTOR_LOG );
		clientInterceptorLog.buildInterceptor();
		
		// Registrando o interceptor na lista
		//InterceptorRegistry interceptorRegistry = new InterceptorRegistry();
		//interceptorRegistry.addInterceptor( clientInterceptorLog );
		InterceptorRegistryClient.addInterceptor( (LogClientInterceptor) clientInterceptorLog );
		
		ClientRequestHandler crh = new ClientRequestHandler();
		
		Requestor r = new Requestor(crh, String.class);
		//r.invoke(id, methodName, args, argsTypes)
		/*
		UserProxy up = new UserProxy("RC", "123", 150.0, 12345678);
		
		up.setNome("Kevin Corno");
		
		System.out.println("Nome: " + up.getNome());
		
		up.setNome("RC");
		
		System.out.println("Nome: " + up.getNome());
		
		//System.out.println(up.getNome().);


		
		try {
			System.out.println((String) r.invoke(0, "equals",
					new Object[]{"pão"}, 

					new String[] {Object.class.getName()}));
		} catch (RemoteError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
	}

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
	
	public static void main(String[] args) {
		// Construindo o interceptor para log
		InterceptorStrategy clientInterceptorLog = genderInterceptor( InterceptorType.INTERCEPTOR_LOG );
		clientInterceptorLog.buildInterceptor();
		
		// Registrando o interceptor na lista
		//InterceptorRegistry interceptorRegistry = new InterceptorRegistry();
		//interceptorRegistry.addInterceptor( clientInterceptorLog );
		InterceptorRegistryClient.addInterceptor( (LogClientInterceptor) clientInterceptorLog );
		
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
		
		ClientRequestHandler crh = new ClientRequestHandler();
		
		Requestor r = new Requestor(crh, String.class);
		/*
<<<<<<< HEAD
		//r.invoke(id, methodName, args, argsTypes)
		/*
		UserProxy up = new UserProxy("RC", "123", 150.0, 12345678);
		
		up.setNome("Kevin Corno");
=======
		
		UserProxy up = new UserProxy();
>>>>>>> origin/core-dev
		
		consutarId("User1 Teste", up);
		
		System.out.println("Primeiro get para user1: " + up.getNome());
		up.setNome("Carlinhos");
		System.out.println("Segundo get para user1: " + up.getNome());
		
		consutarId("User2 Teste", up);
		
<<<<<<< HEAD
		//System.out.println(up.getNome().);
*/

		
		try {
			System.out.println((String) r.invoke(0, "equals",
					new Object[]{"pão"}, 

					new String[] {Object.class.getName()}));
		} catch (RemoteError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
=======
		System.out.println("Primeiro get para user2: " + up.getNome());
		up.setNome("Marcos");
		System.out.println("Segundo get para user2: " + up.getNome());
>>>>>>> origin/core-dev
*/

	}
	
	
}

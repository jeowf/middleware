package application;

import java.io.IOException;

import java.util.ArrayList;

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
	public void teste() {
		System.out.println("Teste");
		// Construindo o interceptor para log
		InterceptorStrategy clientInterceptorLog = genderInterceptor( InterceptorType.INTERCEPTOR_LOG );
		clientInterceptorLog.buildInterceptor();
		
		// Registrando o interceptor na lista
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
		
		//Random rand = new Random();
		
		//ClientRequestHandler crh = new ClientRequestHandler();
		
		UserProxy up = new UserProxy();
		
		consutarId("User1 Teste", up);
		
		System.out.println("Primeiro get para user1: " + up.getNome());
		up.setNome("Carlinhos");
		System.out.println("Segundo get para user1: " + up.getNome());
		
		consutarId("User2 Teste", up);
	}
	
	public static void main(String[] args) {
		// Construindo o interceptor para log
		InterceptorStrategy clientInterceptorLog = genderInterceptor( InterceptorType.INTERCEPTOR_LOG );
		clientInterceptorLog.buildInterceptor();
		
		// Registrando o interceptor na lista
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
		
		//Random rand = new Random();
		
		//ClientRequestHandler crh = new ClientRequestHandler();
		
		UserProxy up = new UserProxy();
		
		consutarId("User1 Teste", up);
		
		System.out.println("Primeiro get para user1: " + up.getNome());
		up.setNome("Carlinhos");
		System.out.println("Segundo get para user1: " + up.getNome());
		
		consutarId("User2 Teste", up);

		
	}
	
	
}

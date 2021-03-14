package application;

import basic.RemoteError;
import basic.client.ClientRequestHandler;
import basic.client.InterceptorRegistry;
import basic.client.Requestor;
import general.enums.InterceptorType;
import patterns.strategy.ClientInterceptorStrategy;
import patterns.strategy.LogClientInterceptor;

public class Client {
	
	ClientInterceptorStrategy clientInterceptorStrategy;
	
	private static ClientInterceptorStrategy genderInterceptor( InterceptorType interceptorType ) {
		if( interceptorType.equals( InterceptorType.INTERCEPTOR_LOG ) ) {
			return new LogClientInterceptor();
			
		}
		
		return null;
		
	}

	public static void main(String[] args) {
		
		// Construindo o interceptor para log
		ClientInterceptorStrategy clientInterceptorLog = genderInterceptor( InterceptorType.INTERCEPTOR_LOG );
		clientInterceptorLog.buildInterceptor();
		
		// Registrando o interceptor na lista
		//InterceptorRegistry interceptorRegistry = new InterceptorRegistry();
		//interceptorRegistry.addInterceptor( clientInterceptorLog );
		InterceptorRegistry.addInterceptor( clientInterceptorLog );
		
		ClientRequestHandler crh = new ClientRequestHandler();
		
		Requestor r = new Requestor(crh, String.class);
		
		UserProxy up = new UserProxy("RC", "123", 150.0, 12345678);
		
		up.setNome("Kevin Corno");
		
		System.out.println("Nome: " + up.getNome());
		
		up.setNome("RC");
		
		System.out.println("Nome: " + up.getNome());
		
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

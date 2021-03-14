package patterns.strategy;

import java.util.Calendar;

import general.InvocationData;
import general.LogDTO;

public class LogClientInterceptor implements ClientInterceptorStrategy{
	
	private boolean ativo;
	private LogDTO log;
	
	private static volatile ClientInterceptorStrategy instance;
	private static Object mutex = new Object();
	
	public LogClientInterceptor() {
		this.ativo = false;
		//buildInterceptor();
		
	}
	
	/*
	public ClientInterceptorStrategy buildInterceptor( boolean ativo ) {
		ClientInterceptorStrategy result = instance;
		
		if (result == null) {
			synchronized ( mutex ) {
				result = instance;
				if (result == null)
					instance = result = new LogClientInterceptor( ativo );
			}
		}
		return result;
	}
	*/
	public boolean isAtivo() {
		return ativo;
	}

	public void buildInterceptor( String registryClient, String operationType ) {
		this.ativo = true;
		this.log = new LogDTO();
		this.log.setRegistryClient( registryClient );
		this.log.setOperationTipe( operationType );
	}

	public void prepareInterceptor( InvocationData invocationData ) {
		LogDTO log = new LogDTO();
		
		Calendar dateTime = Calendar.getInstance();
		log.setDateTime( dateTime );
		
		log.setOperationTipe( invocationData.getSomeMethod() );
		
		String idClient = String.valueOf( invocationData.getObjectID() );
		log.setRegistryClient( idClient );
		
	}
	
	

}

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
		this.log = new LogDTO();
		
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

	public void buildInterceptor() {
		this.ativo = true;
		
		Calendar dateTime = Calendar.getInstance();
		this.log.setDateTime( dateTime );
	}

	public void prepareInterceptor( InvocationData invocationData ) {
		this.ativo = true;
		
		this.log.setOperationTipe( invocationData.getSomeMethod() );
		
		String idClient = String.valueOf( invocationData.getObjectID() );
		this.log.setRegistryClient( idClient );
		
	}
	
	

}

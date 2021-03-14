package patterns.strategy;

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
	
	

}

package patterns.strategy;

import java.util.Calendar;

import general.InvocationData;
import general.LogDTO;

public class LogClientInterceptor extends InterceptorStrategy{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean ativo;
	private LogDTO log;
	
	public LogClientInterceptor() {
		this.ativo = false;
		this.log = new LogDTO();
		
	}
	
	
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

	public LogDTO getLog() {
		return log;
	}

	public void setLog(LogDTO log) {
		this.log = log;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
	

}

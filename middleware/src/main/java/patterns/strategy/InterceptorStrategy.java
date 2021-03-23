package patterns.strategy;

import java.io.Serializable;

import general.InvocationData;

public abstract class InterceptorStrategy implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract void buildInterceptor();
	
	public abstract void prepareInterceptor( InvocationData invocationData );

}

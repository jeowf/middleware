package patterns.strategy;

import java.io.Serializable;

import general.InvocationData;

public interface ClientInterceptorStrategy extends Serializable{
	
	public void buildInterceptor();
	
	public void prepareInterceptor( InvocationData invocationData );

}

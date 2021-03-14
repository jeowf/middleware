package patterns.strategy;

import general.InvocationData;

public interface ClientInterceptorStrategy {
	
	public void buildInterceptor( String registryClient, String operationType );
	
	public void prepareInterceptor( InvocationData invocationData );

}

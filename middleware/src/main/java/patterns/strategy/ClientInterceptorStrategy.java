package patterns.strategy;

public interface ClientInterceptorStrategy {
	
	public void buildInterceptor( String registryClient, String operationType );

}

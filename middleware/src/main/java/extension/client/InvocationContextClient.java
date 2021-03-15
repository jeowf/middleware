package extension.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import patterns.strategy.ClientInterceptorStrategy;

public class InvocationContext {
	
	Map<String, List<ClientInterceptorStrategy>> interceptors;
	
	public InvocationContext() {
		
		interceptors = new HashMap<String, List<ClientInterceptorStrategy>>();
		
	}

	public void setInterceptors(Map<String, List<ClientInterceptorStrategy>> interceptorsReady) {
		this.interceptors = interceptorsReady;
		
	}

}

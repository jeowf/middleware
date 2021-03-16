package extension.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import patterns.strategy.InterceptorStrategy;
import patterns.strategy.LogClientInterceptor;

public class InvocationContextClient implements Serializable{
	
	Map<String, List<LogClientInterceptor>> interceptors;
	
	public InvocationContextClient() {
		
		interceptors = new HashMap<String, List<LogClientInterceptor>>();
		
	}

	public void setInterceptors(Map<String, List<LogClientInterceptor>> interceptorsReady) {
		this.interceptors = interceptorsReady;
		
	}

	public Map<String, List<LogClientInterceptor>> getInterceptors() {
		return interceptors;
	}
	
	

}

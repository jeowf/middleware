package extension.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import patterns.strategy.InterceptorStrategy;
import patterns.strategy.LogServerInterceptor;

public class InvocationContextServer {
	
Map<String, List<LogServerInterceptor>> interceptors;
	
	public InvocationContextServer() {
		
		interceptors = new HashMap<String, List<LogServerInterceptor>>();
		
	}

	public void setInterceptors(Map<String, List<LogServerInterceptor>> interceptorsRegistry) {
		this.interceptors = interceptorsRegistry;
		
	}

	public Map<String, List<LogServerInterceptor>> getInterceptors() {
		return interceptors;
	}
	
	

}

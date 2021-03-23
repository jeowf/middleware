package extension.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import general.enums.InterceptorType;
import patterns.strategy.InterceptorStrategy;
import patterns.strategy.LogClientInterceptor;

public class InterceptorRegistryClient {
	
	static Map<String, List<LogClientInterceptor>> interceptors;
	
	public InterceptorRegistryClient() {
		
		interceptors = new HashMap<String, List<LogClientInterceptor>>();
		
	}

	public static void addInterceptor( LogClientInterceptor clientInterceptor ) {
		if( interceptors == null ) {
			interceptors = new HashMap<String, List<LogClientInterceptor>>();
			
		}
		
		if( clientInterceptor instanceof LogClientInterceptor ) {
			
			List<LogClientInterceptor> currentList = new ArrayList<LogClientInterceptor>();
			if( !interceptors.containsKey( InterceptorType.INTERCEPTOR_LOG.getDenominacao() ) ) {
				currentList.add( clientInterceptor );
				
				interceptors.put( InterceptorType.INTERCEPTOR_LOG.getDenominacao(), currentList );
				
			}else {
				currentList = interceptors.get( InterceptorType.INTERCEPTOR_LOG.getDenominacao() );
				currentList.add( clientInterceptor );
				
				interceptors.put( InterceptorType.INTERCEPTOR_LOG.getDenominacao(), currentList );
				
			}
			
		}
		
	}

	public static Map<String, List<LogClientInterceptor>> getInterceptors() {
		return interceptors;
	}
	
	

}

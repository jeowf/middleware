package basic.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import general.enums.InterceptorType;
import patterns.strategy.ClientInterceptorStrategy;
import patterns.strategy.LogClientInterceptor;

public class InterceptorRegistry {
	
	static Map<String, List<ClientInterceptorStrategy>> interceptors;
	
	public InterceptorRegistry() {
		
		interceptors = new HashMap<String, List<ClientInterceptorStrategy>>();
		
	}

	public static void addInterceptor( ClientInterceptorStrategy clientInterceptor ) {
		if( interceptors == null ) {
			interceptors = new HashMap<String, List<ClientInterceptorStrategy>>();
			
		}
		
		if( clientInterceptor instanceof LogClientInterceptor ) {
			
			List<ClientInterceptorStrategy> currentList = new ArrayList<ClientInterceptorStrategy>();
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

	public static Map<String, List<ClientInterceptorStrategy>> getInterceptors() {
		return interceptors;
	}
	
	

}

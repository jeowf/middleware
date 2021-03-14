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
	
	Map<String, List<ClientInterceptorStrategy>> interceptors;
	
	public InterceptorRegistry() {
		
		interceptors = new HashMap<String, List<ClientInterceptorStrategy>>();
		
	}

	public void addInterceptor( ClientInterceptorStrategy clientInterceptor ) {
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
	
	

}
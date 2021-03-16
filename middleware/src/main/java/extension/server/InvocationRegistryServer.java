package extension.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import general.LogDTO;
import general.enums.InterceptorType;
import patterns.strategy.InterceptorStrategy;
import patterns.strategy.LogClientInterceptor;
import patterns.strategy.LogServerInterceptor;

public class InvocationRegistryServer {

	static Map<String, List<LogServerInterceptor>> interceptors;
	
	public static void addInterceptors( Map<String, List<LogClientInterceptor>> interceptorsReady ) {
		if( interceptors == null ) {
			interceptors = new HashMap<String, List<LogServerInterceptor>>();
			
		}
		//interceptors = interceptorsReady;
		for( List<LogClientInterceptor> interceptorsDic : interceptorsReady.values() ) {
			
			List<LogServerInterceptor> currentList = new ArrayList<LogServerInterceptor>();
			
			for( InterceptorStrategy interceptor : interceptorsDic  ) {
				if( interceptor instanceof LogClientInterceptor ) {
					boolean ativo = ((LogClientInterceptor) interceptor).isAtivo();
					LogDTO log = ((LogClientInterceptor) interceptor).getLog();
					LogServerInterceptor serverInterceptor = new LogServerInterceptor( ativo, log );
					
					if( !interceptors.containsKey( InterceptorType.INTERCEPTOR_LOG.getDenominacao() ) ) {
						currentList.add( serverInterceptor );						
						
						interceptors.put( InterceptorType.INTERCEPTOR_LOG.getDenominacao(), currentList );
						
					}else {
						currentList = interceptors.get( InterceptorType.INTERCEPTOR_LOG.getDenominacao() );
						currentList.add( serverInterceptor );
						
						interceptors.put( InterceptorType.INTERCEPTOR_LOG.getDenominacao(), currentList );
						
					}
					
				}
				
			}
			
		}
		
		
	}

	public static Map<String, List<LogServerInterceptor>> getInterceptors() {
		return interceptors;
	}
	
	/*
	public static void setInterceptors(Map<String, List<InterceptorStrategy>> interceptorsReady) {
		if( interceptors == null ) {
			interceptors = new HashMap<String, List<InterceptorStrategy>>();
			
		}
		
		interceptors = interceptorsReady;
		
	}
	*/
	
	
	
	

}

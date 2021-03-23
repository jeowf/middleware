package basic.server;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import basic.Marshaller;
import basic.RemoteError;
import extension.server.InvocationContextServer;
import extension.server.InvocationRegistryServer;
import general.InvocationData;
import general.RequestorMessage;
import patterns.strategy.InterceptorStrategy;
import patterns.strategy.LogClientInterceptor;
import patterns.strategy.LogServerInterceptor;

public class Invoker {
	private long invokerID;
	private Marshaller marshaller;
	private InstanceList il;
	
	
	public Invoker(long invokerID) {
		super();
		this.invokerID = invokerID;
		this.marshaller = new Marshaller();
		this.il = InstanceList.getInstance();
	}

	public Object invoke(String message) throws RemoteError {
		
		RequestorMessage rm = (RequestorMessage) marshaller.unmarshal(message, RequestorMessage.class);
					
		try {
			//Class<?> cls = Class.forName(o);
			//System.out.println(cls.getName());
			String[] argsTypes = rm.getInvocationData().getArgsTypes();
			Object[] args = rm.getInvocationData().getArgs();
			
			Class<?>[] argsT = null;
			
			if(argsTypes != null) {
			
				argsT = new Class<?>[argsTypes.length]; 
				for(int i = 0; i < argsTypes.length; i++) {
					argsT[i] = Class.forName(argsTypes[i]);
				}
			}
			
			
			Class<?> objectClass = Class.forName(rm.getInvocationData().getObjectClass());
			
			// Atualizando InvocationRegistryServer
			Map<String, List<LogClientInterceptor>> interceptorsReady = rm.getInvocationData().getInvocationContext().getInterceptors();
			InvocationRegistryServer.addInterceptors( interceptorsReady );
			//InvocationRegistryServer.setInterceptors( interceptorsReady );
			
			// Cria InvocationContext
			Map<String, List<LogServerInterceptor>> interceptorsRegistry = InvocationRegistryServer.getInterceptors();
			
			InvocationContextServer invocationContextServer = new InvocationContextServer();
			invocationContextServer.setInterceptors( interceptorsRegistry );
			
			// Prepara intercetors
			prepareInvocation( invocationContextServer );
			
			
			//String s = "pão";
			try {
				//System.out.println(objectClass.getName());
				String methodName = rm.getInvocationData().getSomeMethod();
				if (methodName.equals("*constructor")) {
					Constructor constructor = objectClass.getConstructor(argsT);
					
					
					return createObject(args,constructor,objectClass);
					
				} else {
					Method method;
					method = objectClass.getMethod(methodName,	argsT);
					Object obj = il.getInstance(objectClass, rm.getInvocationData().getObjectID());
					
					return invokeMethod(args, method, obj);
				}
				
				
			} catch (SecurityException e) {
				System.out.println("c4");

			} catch (NoSuchMethodException e) {
				System.out.println("c5");	
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("c6");					
				e.printStackTrace();
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Tipo inválido nos argumentos do parametro
			e.printStackTrace();
		}
		System.out.println("Erro 1");
		
		return null;
	}

	private void prepareInvocation( InvocationContextServer invocationContextServer ) {
		for( List<LogServerInterceptor> interceptors : invocationContextServer.getInterceptors().values() ) {
			for( InterceptorStrategy interceptor : interceptors ) {
				LogServerInterceptor interceptorTemp = (LogServerInterceptor) interceptor;
				interceptorTemp.writeLog();
			}
			
		}
		
	}

	private Object invokeMethod(Object[] args, Method method, Object s) throws RemoteError{
		try {
			Object x = method.invoke(s, args);
			System.out.println("fim");
			System.out.println(x);
			return x;
		}catch (IllegalArgumentException e) { 
			System.out.println("c1");
		}catch (IllegalAccessException e) { 
			System.out.println("c2");
		}catch (InvocationTargetException e) { 
			System.out.println("c3");
		}
		System.out.println("Erro");
		return null;
	}
	
	private Long createObject(Object[] args, Constructor constructor, Class cls) throws RemoteError {
		
		try {
			Object obj = constructor.newInstance(args);
			return il.addInstance(cls, obj);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}

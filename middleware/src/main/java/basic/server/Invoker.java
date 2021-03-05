package basic.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import basic.Marshaller;
import basic.RemoteError;
import general.RequestorMessage;

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
			
			Method method;
			//String s = "pão";
			try {
				System.out.println(objectClass.getName());
				method = objectClass.getMethod(rm.getInvocationData().getSomeMethod(),	argsT);
				System.out.println();
				Object obj = il.getInstance(objectClass, rm.getInvocationData().getObjectID());
				
				return invokeMethod(args, method, obj);
				
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
}

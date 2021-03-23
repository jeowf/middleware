package basic.server;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import basic.Marshaller;
import basic.RemoteError;
import general.RequestorMessage;
import lifecycle.ClientDependentLifecycleManager;
import lifecycle.PerRequestLifecycleManager;

public class Invoker {
	private long invokerID;
	private Marshaller marshaller;
	private ClientDependentLifecycleManager cdlm;
	//private LifecycleManagerRegistry lr;
	
	
	public Invoker(long invokerID) {
		super();
		this.invokerID = invokerID;
		this.marshaller = new Marshaller();
		this.cdlm = ClientDependentLifecycleManager.getInstance();
		//this.lr = new LifecycleManagerRegistry() ; 
	}

	public Object invoke(String message) throws RemoteError {
		
		RequestorMessage rm = (RequestorMessage) marshaller.unmarshal(message, RequestorMessage.class);
					
		try {
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
			
			//String s = "pão";
			try {
				//System.out.println(objectClass.getName());
				String methodName = rm.getInvocationData().getSomeMethod();
				if (methodName.equals("*constructor")) {
					Constructor constructor = objectClass.getConstructor(argsT);
					
					
					return createObject(args,constructor,objectClass);
					
				}
				else if (methodName.equals("*destroy")) {
					System.out.println(methodName);
					return cdlm.invocationDone(objectClass);					
				} 
				else {
					Method method;
					method = objectClass.getMethod(methodName,	argsT);
					Object obj = cdlm.invocationArrived(objectClass, rm.getInvocationData().getObjectID());
					
					//Long id = rm.getInvocationData().getObjectID();
					//LifecycleManagerInterface pc = lr.getLifecycleManager(id); 
					//Object obj = il.getInstance(objectClass, rm.getInvocationData().getObjectID());
					//Object ro = pc.invocationArrived(id);
					return invokeMethod(args, method, obj);
					//Object obj = invokeMethod(args, method, ro);
					
					//pc.invocationDone(id);
					
					///return obj;
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
			return cdlm.addInstance(cls, obj);
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

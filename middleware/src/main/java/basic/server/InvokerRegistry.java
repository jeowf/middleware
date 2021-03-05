package basic.server;

import java.util.ArrayList;
import java.util.List;

import basic.RemoteError;

//Singleton
public class InvokerRegistry {
	
	private static volatile InvokerRegistry instance;
	private static Object mutex = new Object();
	
	private List<Invoker> invokers;	
	
	private InvokerRegistry() {
		this.invokers = new ArrayList<Invoker>();
	}

	public static InvokerRegistry getInstance() {
		InvokerRegistry result = instance;
		if (result == null) {
			synchronized (mutex) {
				result = instance;
				if (result == null)
					instance = result = new InvokerRegistry();
			}
		}
		return result;
	}

	public Invoker getInvoker(long id) throws RemoteError{
		
		if(id >= 0) {
			
			int castId = (int) id;
			
			try {
			
				return invokers.get(castId);
		
			}catch(IndexOutOfBoundsException e){
				
				throw new RemoteError();
			}
		}else {
			Invoker invoker = new Invoker(invokers.size());
			
			invokers.add(invoker);
			
			return invoker;
		}
		
	}
}

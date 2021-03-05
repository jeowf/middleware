package basic.server;

import java.util.ArrayList;
import java.util.List;

import basic.RemoteError;

//Singleton
public class InvokerRegistry {
	
	private List<Invoker> invokers;	
	
	public InvokerRegistry() {
		super();
		this.invokers = new ArrayList<Invoker>();
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

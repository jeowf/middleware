package basic.server;

import java.util.List;

import basic.RemoteError;

//Singleton
public class InvokerRegistry {
	private List<Invoker> invokers;
	
	public Invoker getInvoker(long id) throws RemoteError{
		return null;
	}
}

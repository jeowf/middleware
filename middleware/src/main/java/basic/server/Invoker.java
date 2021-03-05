package basic.server;

import basic.Marshaller;
import basic.RemoteError;

public class Invoker {
	private long invokerID;
	private Marshaller marshaller;
	
	
	public Invoker(long invokerID) {
		super();
		this.invokerID = invokerID;
		this.marshaller = new Marshaller();
	}

	public Object invoke(String message) throws RemoteError {
		
		
		
		return null;
	}
	
	private Object invokeMethod(Object obj) throws RemoteError {
		return null;
	}
	
	public long getRemove(Object obj) throws RemoteError {
		
		return 0;
	}
}

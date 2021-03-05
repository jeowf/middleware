package basic.server;

import basic.Marshaller;
import basic.RemoteError;
import general.RequestorMessage;

public class Invoker {
	private long invokerID;
	private Marshaller marshaller;
	
	
	public Invoker(long invokerID) {
		super();
		this.invokerID = invokerID;
		this.marshaller = new Marshaller();
	}

	public Object invoke(String message) throws RemoteError {
		
		RequestorMessage rm = (RequestorMessage) marshaller.unmarshal(message, RequestorMessage.class);
		
		for(Object o : rm.getInvocationData().getArgs()) {
			System.out.println(o.getClass().getName());
		}
		
		return null;
	}
	
	private Object invokeMethod(Object obj) throws RemoteError {
		return null;
	}
	
	public long getRemove(Object obj) throws RemoteError {
		
		return 0;
	}
}

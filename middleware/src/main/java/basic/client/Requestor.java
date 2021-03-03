package basic.client;

import basic.Marshaller;
import basic.RemoteError;

public class Requestor {
	private long requestorID;
	private Marshaller marshaller;
	private ClientRequestHandler clientRequestHandler;
	
	public Requestor() {
		
	}
	
	public Object invoke(Object target, String methodName, Object ... args) throws RemoteError {
		return null;
	}
}

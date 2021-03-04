package basic.client;

import basic.Marshaller;
import basic.RemoteError;
import general.InvocationData;
import general.Message;
import general.RequestorMessage;

public class Requestor {
	private static long requestorID;
	private static Marshaller marshaller;
	private static ClientRequestHandler clientRequestHandler;
	
	public Requestor() {
		
	}
	
	public static Object invoke(Object target, String methodName, Object ... args) throws RemoteError {
		
		InvocationData invocationData = new InvocationData(target, methodName);
		
		RequestorMessage m = new RequestorMessage(requestorID, invocationData);
		
		marshaller = new Marshaller();
		
		clientRequestHandler = new ClientRequestHandler();
		
		String dataToSend = marshaller.marshal(m);
		
		print(clientRequestHandler.send(dataToSend));
		print("Fim");

		
		return null;
	}
	
	public static void main(String[] args) {
		
		Object x = "teste";
		
		try {
			invoke(x, "someMethod", null);
		} catch (RemoteError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	public static void print(String msg) {
		System.out.println(msg);
	}
}

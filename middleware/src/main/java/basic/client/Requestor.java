package basic.client;

import basic.Marshaller;
import basic.RemoteError;
import general.InvocationData;
import general.Message;
import general.RequestorMessage;
import general.ServerResponseMessage;

public class Requestor {
	private long requestorID;
	private Marshaller marshaller;
	private ClientRequestHandler clientRequestHandler;
	private Class objectClass;
	
	public Requestor(ClientRequestHandler clientRequestHandler, Class objectClass) {
		marshaller = new Marshaller();
		this.requestorID = 0; // Substituir por um ID válido posteriormente
		this.clientRequestHandler = clientRequestHandler;
		this.objectClass = objectClass;
	}
	
	public Object invoke(long id, String methodName, Object ... args) throws RemoteError {
		
		System.out.println(methodName);
		InvocationData invocationData = new InvocationData(id, methodName, args, objectClass.getName());
		
		RequestorMessage m = new RequestorMessage(requestorID, invocationData);
		
		String message = marshaller.marshal(m);
		
		//System.out.println(message);
		
		//encodeMessage (adicionar o ID do invoker na string de mensagem)
		
		message = encode(message, 100);
		
		//System.out.println(message);
		
		String response = clientRequestHandler.send(message);
		
		ServerResponseMessage objResponse = (ServerResponseMessage) marshaller.unmarshal(response, ServerResponseMessage.class);
		
		return objResponse.getObject();
				
		/*
		try {
			Object x = Class.forName(objectClass.getName()).cast(objResponse);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//print(clientRequestHandler.send(dataToSend));
		//print("Fim");
	}
	
	public String encode(String message, long invokerId) {
		StringBuffer text = new StringBuffer(message);
		text.replace( 0 , 0 , "" + invokerId);
		return text.toString();
	}
	

}

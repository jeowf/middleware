package basic.client;

import basic.Marshaller;
import basic.RemoteError;
import general.InvocationData;
import general.LookUpMessage;
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
	
	public Object invoke(long id, String methodName, Object[] args, String[] argsTypes) throws RemoteError {
		
		InvocationData invocationData = new InvocationData(id, methodName, args, argsTypes, objectClass.getName());
		
		//RequestorMessage m = new RequestorMessage(requestorID, invocationData);
		
		//String message = marshaller.marshal(m);
		
		//System.out.println(message);
		
		String message;
				
		// Caso o objectID seja -2, significa que é uma mensagem de lookup, então inseriremos o código do mesmo
		if(id == -2) 
		{
			LookUpMessage m = new LookUpMessage(requestorID, methodName, invocationData);
			
			message = marshaller.marshal(m);
			
			message = encode(message, 28, 2);
			
			System.out.println("Mensagem do lookup: " + message);
		}
		else if(id == -3) 
		{
			LookUpMessage m = new LookUpMessage(requestorID, methodName, invocationData);
			
			message = marshaller.marshal(m);
			
			message = encode(message, 29, 2);
			
			System.out.println("Mensagem do lookup: " + message);
		}
		else 
		{
			RequestorMessage m = new RequestorMessage(requestorID, invocationData);
			
			 message = marshaller.marshal(m);
			//encodeMessage (adicionar o ID do invoker na string de mensagem)
			message = encode(message, 100, 1);
			
			System.out.println("Mensagem: " + message);
			
			
		}
		
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
	
	public String encode(String message, long invokerId, long messageType) {
		StringBuffer text = new StringBuffer(message);
		text.replace( 0 , 0 , "" + invokerId + "," + messageType);
		return text.toString();
	}
	

}

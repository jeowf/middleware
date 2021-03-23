package basic.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import basic.Marshaller;
import basic.RemoteError;
import extension.client.InterceptorRegistryClient;
import extension.client.InvocationContextClient;
import general.InvocationData;
import general.LogDTO;
import general.LookUpMessage;
import general.Message;
import general.RequestorMessage;
import general.ServerResponseMessage;
import patterns.strategy.InterceptorStrategy;
import patterns.strategy.LogClientInterceptor;

public class Requestor {
	private long requestorID;
	private Marshaller marshaller;
	private ClientRequestHandler clientRequestHandler;
	private Class objectClass;
	
	public Requestor(ClientRequestHandler clientRequestHandler, Class objectClass) {
		marshaller = new Marshaller();
		this.requestorID = 0; // Substituir por um ID v�lido posteriormente
		this.clientRequestHandler = clientRequestHandler;
		this.objectClass = objectClass;
	}
	
	public Object invoke(long id, String methodName, Object[] args, String[] argsTypes) throws RemoteError {
		
		InvocationData invocationData = new InvocationData(id, methodName, args, argsTypes, objectClass.getName());
		
		// Captura a lista de interceptors gerada anteriormente
		Map<String, List<LogClientInterceptor>> interceptorsReady = InterceptorRegistryClient.getInterceptors();
		
		
		//Preparando interceptors
		prepareInterceptor( interceptorsReady, invocationData );
		
		// Cria e adiciona interceptors a um novo Invocation Context
		InvocationContextClient invocationContext = new InvocationContextClient();		
		invocationContext.setInterceptors( interceptorsReady );
		
		// Adicionando dados de context de invocação aos dados a serem enviados
		invocationData.setInvocationContext( invocationContext );
		
		//RequestorMessage m = new RequestorMessage( requestorID, invocationData );
		//String message = marshaller.marshal(m);
		
		//System.out.println(message);
		
		String message;
				
		// Caso o objectID seja -2, significa que � uma mensagem de lookup, ent�o inseriremos o c�digo do mesmo
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
	
	private void prepareInterceptor(Map<String, List<LogClientInterceptor>> interceptorsReady, InvocationData invocationData) {
		for( List<LogClientInterceptor> interceptors : interceptorsReady.values() ) {
			for( LogClientInterceptor interceptor : interceptors ) {
				interceptor.prepareInterceptor( invocationData );
				
			}			
		}		
	}

	public String encode(String message, long invokerId, long messageType) {
		StringBuffer text = new StringBuffer(message);
		text.replace( 0 , 0 , "" + invokerId + "," + messageType);
		return text.toString();
	}
	

}

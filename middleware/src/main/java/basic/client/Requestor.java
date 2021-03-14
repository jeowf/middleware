package basic.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import basic.Marshaller;
import basic.RemoteError;
import general.InvocationContext;
import general.InvocationData;
import general.LogDTO;
import general.Message;
import general.RequestorMessage;
import general.ServerResponseMessage;
import patterns.strategy.ClientInterceptorStrategy;

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
		Map<String, List<ClientInterceptorStrategy>> interceptorsReady = InterceptorRegistry.getInterceptors();
		
		
		//Preparando interceptors
		prepareInterceptor( interceptorsReady, invocationData );
		
		// Cria e adiciona interceptors a um novo Invocation Context
		InvocationContext invocationContext = new InvocationContext();		
		invocationContext.setInterceptors( interceptorsReady );
		
		// Adicionando dados de context de invocação aos dados a serem enviados
		invocationData.setInvocationContext( invocationContext );
		
		RequestorMessage m = new RequestorMessage( requestorID, invocationData );
		
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
	
	private void prepareInterceptor(Map<String, List<ClientInterceptorStrategy>> interceptorsReady, InvocationData invocationData) {
		for( List<ClientInterceptorStrategy> interceptors : interceptorsReady.values() ) {
			for( ClientInterceptorStrategy interceptor : interceptors ) {
				interceptor.prepareInterceptor( invocationData );
				
			}			
		}		
	}

	public String encode(String message, long invokerId) {
		StringBuffer text = new StringBuffer(message);
		text.replace( 0 , 0 , "" + invokerId);
		return text.toString();
	}
	

}

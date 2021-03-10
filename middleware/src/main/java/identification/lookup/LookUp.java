package identification.lookup;

import java.util.HashMap;
import java.util.Set;

import basic.Marshaller;
import basic.RemoteError;
import basic.server.Invoker;
import basic.server.InvokerRegistry;
import general.LookUpMessage;
import general.RequestorMessage;

public class LookUp 
{
	private InvokerRegistry invokerRegistry;
	private HashMap<String, Long> ids;
	private Marshaller marshaller;
	
	public LookUp() 
	{
		this.ids = new HashMap<String, Long>();
		this.invokerRegistry = InvokerRegistry.getInstance();
		this.marshaller = new Marshaller();
	}
	
	private void printHash() 
	{
		Set<String> set = ids.keySet();
		
		System.out.println("---------- Mostrando as chaves contidas no lookup: ------------");
		for(String e : set) 
		{
			System.out.println("O objeto tipo: " + e + "\nestá associado ao id: " + ids.get(e));
		}
		System.out.println("----------------------------------------------------------------");
	}
	
	public Long lookup(String message) 
	{
		LookUpMessage lm = (LookUpMessage) marshaller.unmarshal(message, LookUpMessage.class);
		if(ids.containsKey(lm.getObjectType())) 
		{	
			//RequestorMessage rm = new RequestorMessage(lm.getId(), lm.getInvocationData());
			
			//invoker.invoke(marshaller.marshal(rm));
			
			System.out.println("Printando o código dentro da consulta com sucesso");
			printHash();
			
			return ids.get(lm.getObjectType());
		}
		else
		{
			Invoker invoker;
			Object idObject;
			
			try {
				invoker = invokerRegistry.getInvoker(-1);
				
				lm.getInvocationData().setSomeMethod("*constructor");
				
				RequestorMessage rm = new RequestorMessage(lm.getId(), lm.getInvocationData());
				
				idObject = invoker.invoke(marshaller.marshal(rm));
				
				Long id = (long) Math.floor((Long) idObject);
				
				ids.put(lm.getObjectType(), id);
				
				printHash();
				
				return id;
				
			} catch (RemoteError e) {
				e.printStackTrace();
			}	
		}
		
		return null;
	}
}

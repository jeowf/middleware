package identification.lookup;

import java.util.HashMap;
import java.util.Set;

import javax.management.ObjectName;

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
	
	public boolean bind(String objectName, Long objectId) 
	{
		if(!ids.containsKey(objectName)) 
		{
			ids.put(objectName, objectId);
			
			System.out.println("Objeto " + objectName + " cadastrado com sucesso!!!");
			
			return true;
		}
		
		System.out.println("O objeto fornecido já foi cadastrado!!!");
		
		return false;
	}
	
	public boolean unbind(String message) 
	{
		LookUpMessage lm = (LookUpMessage) marshaller.unmarshal(message, LookUpMessage.class);
		String objectName = lm.getObjectType();
		if(ids.containsKey(objectName)) 
		{
			ids.remove(objectName);

			Invoker invoker;
			try {
				invoker = invokerRegistry.getInvoker(-1);
			
				lm.getInvocationData().setSomeMethod("*destroy");
				
				RequestorMessage rm = new RequestorMessage(lm.getId(), lm.getInvocationData());
				
				invoker.invoke(marshaller.marshal(rm));
			} catch (RemoteError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			printHash();
			
			System.out.println("Objeto " + objectName + " removido com sucesso!!!");
			
			return true;
		}
		
		System.out.println("Não existe um objeto com o nome " + objectName + " cadastrado");
		
		return false;
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

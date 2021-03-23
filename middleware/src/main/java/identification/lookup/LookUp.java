package identification.lookup;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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
	private ConcurrentHashMap<String, Long> ids;
	private Marshaller marshaller;
	
	private static volatile LookUp instance;
	private static Object mutex = new Object();
		
	public LookUp() {
		this.ids = new ConcurrentHashMap<String, Long>();
		this.invokerRegistry = InvokerRegistry.getInstance();
		this.marshaller = new Marshaller();
	}
	
	public static LookUp getInstance() {
		LookUp result = instance;
		if (result == null) {
			synchronized (mutex) {
				result = instance;
				if (result == null)
					instance = result = new LookUp();
			}
		}
		return result;
	}
	
	public ConcurrentHashMap<String, Long> getIds(){
		return ids;
	}
	
	private void printHash() 
	{
		Set<String> set = ids.keySet();
		
		System.out.println("---------- Mostrando as chaves contidas no lookup: ------------");
		for(String e : set) 
		{
			System.out.println("O objeto tipo: " + e + "\nest� associado ao id: " + ids.get(e));
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
		
		System.out.println("O objeto fornecido j� foi cadastrado!!!");
		
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
		
		System.out.println("N�o existe um objeto com o nome " + objectName + " cadastrado");
		
		return false;
	}
	
	public Long lookup(String message) 
	{
		LookUpMessage lm = (LookUpMessage) marshaller.unmarshal(message, LookUpMessage.class);
		if(ids.containsKey(lm.getObjectType())) 
		{	

			System.out.println("Printando o c�digo dentro da consulta com sucesso");
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

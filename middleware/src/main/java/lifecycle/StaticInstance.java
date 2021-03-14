package lifecycle;


import java.util.HashMap;
import java.util.Set;

import basic.Marshaller;
import basic.RemoteError;
import basic.server.Invoker;
import basic.server.InvokerRegistry;
import general.InvocationData;
import general.LookUpMessage;
import general.RequestorMessage;
import identification.lookup.*;

public class StaticInstance {
	LookUp lookUp = new LookUp();
	Invoker invoker = new Invoker(30);

	Marshaller marshaller = new Marshaller();
	HashMap<String,Long> staticObjectsReferences = new HashMap<String, Long>(10);
	
	public HashMap<String,Long> getStaticObjectsReferences() {
		return staticObjectsReferences;
	}
	
	private void printHash() 
	{
		Set<String> set = staticObjectsReferences.keySet();
		
		System.out.println("---------- Mostrando as chaves contidas no Static: ------------");
		for(String e : set) 
		{
			System.out.println("O objetoStatic tipo: " + e + "\n esta associado ao id: " + staticObjectsReferences.get(e));
		}
		System.out.println("----------------------------------------------------------------");
	}
	
	public void create() throws RemoteError {
		long id = 0;
		for(int i = 0; i < 10 ; i++) {
			String nameObject = "objeto" + String.valueOf(i);
			print(nameObject);
			
			InvocationData staticInvocationData = new InvocationData(id,"*constructor",null,null,String.class.getName());
			id++;
			RequestorMessage rm = new RequestorMessage(0,staticInvocationData);
			String staticMessage = marshaller.marshal(rm);
			
			
			Long idObj = (Long)invoker.invoke(staticMessage);
			LookUpMessage lm = new LookUpMessage(idObj,String.class.getName(),staticInvocationData);
			staticMessage = marshaller.marshal(lm);
			lookUp.lookup(staticMessage);
			
			
			staticObjectsReferences.put(nameObject,idObj);
			
			
		}
		
		printHash();
		
	}
	
	public void print(String msg) {
		System.out.println(msg);
	}
	
	public void destroy(){
		
	}
	
}

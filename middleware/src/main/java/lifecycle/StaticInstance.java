package lifecycle;


import java.util.HashMap;
import java.util.Set;

import basic.RemoteError;

import identification.lookup.*;

public class StaticInstance {
	LookUp lookUp = new LookUp();
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
		for(int i = 0; i < 10 ; i++) {
			String nameObject = "objeto" + String.valueOf(i);
			print(nameObject);
			
			Long id = lookUp.lookup(nameObject);
			staticObjectsReferences.put(nameObject,id);
		}
		
		printHash();
		
	}
	
	public void print(String msg) {
		System.out.println(msg);
	}
	
	public void destroy(){
		
	}
	
}

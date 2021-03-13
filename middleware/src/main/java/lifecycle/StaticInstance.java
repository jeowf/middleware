package lifecycle;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import basic.Marshaller;
import basic.RemoteError;
import basic.server.InstanceList;
import basic.server.Invoker;
import basic.server.InvokerRegistry;
import general.LookUpMessage;
import general.RequestorMessage;
import identification.lookup.*;

public class StaticInstance {
	LookUp lookUp;
	List<Long> staticObjectsReferences = new ArrayList<Long>(10);
	
	public List<Long> getStaticObjectsReferences() {
		return staticObjectsReferences;
	}
	
	public void create() throws RemoteError {
		String nameObject = "objeto";
		for(int i = 0; i < staticObjectsReferences.size() ; i++) {
			Long id = lookUp.lookup(nameObject +  String.valueOf(i));
			staticObjectsReferences.add(id);
		}
		
	}
	
	public void destroy(long id) {
		
	}
	
}

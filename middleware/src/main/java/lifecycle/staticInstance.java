package lifecycle;


import basic.Marshaller;
import basic.RemoteError;
import basic.server.InstanceList;
import basic.server.Invoker;
import basic.server.InvokerRegistry;
import general.LookUpMessage;
import general.RequestorMessage;
import identification.lookup.*;

public class staticInstance {
	Invoker staticInvoker; 
	InstanceList instanceList; 
	
	public Invoker getStaticInvoker() {
		return staticInvoker;
	}
	
	public void createStaticInstance(Class<?> cls, Object obj) throws RemoteError {
		instanceList.addInstance(cls, obj);
	}
	
	public void destroyStaticInstance(long id) {
		
	}
	
}

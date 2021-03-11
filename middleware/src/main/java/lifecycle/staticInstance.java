package lifecycle;


import basic.Marshaller;
import basic.RemoteError;
import basic.server.Invoker;
import basic.server.InvokerRegistry;
import general.LookUpMessage;
import general.RequestorMessage;
import identification.lookup.*;

public class staticInstance {
	Invoker staticInvoker; 
	
	public Invoker getStaticInvoker() {
		return staticInvoker;
	}
	
	public void registerStaticInstance() {
		
	}
	
}

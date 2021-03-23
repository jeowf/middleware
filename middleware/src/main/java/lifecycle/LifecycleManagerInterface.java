package lifecycle;

import basic.RemoteError;
import basic.server.Invoker;
import basic.server.RemoteObject;

public interface LifecycleManagerInterface {

	public Object invocationArrived(Class<?> cls, long id) throws RemoteError;
	
	public Object invocationDone(Class<?> cls);

}

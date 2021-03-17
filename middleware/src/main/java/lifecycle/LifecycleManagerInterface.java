package lifecycle;

import basic.RemoteError;
import basic.server.Invoker;
import basic.server.RemoteObject;

public interface LifecycleManagerInterface {

	public Object invocationArrived(Long id) throws RemoteError;
	
	public void invocationDone(Long  serv);

}

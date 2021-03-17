package lifecycle;

import basic.RemoteError;
import basic.server.Invoker;

public interface InstancePoolInterface {
	
	public Object getFreeInstance() throws RemoteError;
	
	public void putBackToPool(Object pooledSrvt) throws RemoteError;

}

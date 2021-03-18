package lifecycle;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import basic.RemoteError;

public class PerRequestLifecycleManager implements LifecycleManagerInterface  {

	//private ConcurrentHashMap<Long, Object> instances;
	private ConcurrentHashMap<Class<?>, HashMap<Long,Object>> instances;
	

	InstancePool instancepool;

	public PerRequestLifecycleManager() throws RemoteError {
		this.instances = new ConcurrentHashMap<Class<?>, HashMap<Long,Object>>();
		this.instancepool =  InstancePool.getInstance();
	}

	public Object invocationArrived(Class<?> cls, long id) throws RemoteError {
		// TODO Auto-generated method stub
		return null;
	}

	public Object invocationDone(Class<?> cls) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

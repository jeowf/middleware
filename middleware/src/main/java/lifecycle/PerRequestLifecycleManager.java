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

	public void invocationDone(Long id) {	
		instances.remove(id);
	}

	public Object invocationArrived(Long id) throws RemoteError {
		return null;
	}
	
}

package lifecycle;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import application.User;
import basic.RemoteError;

// Singleton
public class StaticLifecycleManager {
	private static volatile StaticLifecycleManager instance;
	private static Object mutex = new Object();
	
	private ConcurrentHashMap<Long, Object> instances;
		
	private StaticLifecycleManager() {
		instances = new ConcurrentHashMap<Long, Object>();
		
		
	}

	public Object getInstance(long id) throws RemoteError{
		if (!instances.containsKey(id))
			throw new RemoteError(); // instancia nao encontrada
		
		return instances.get(id);
	}
	
	public void addInstance(Long id, Object obj) throws RemoteError{
		if (!instances.containsKey(id)) {
			instances.put(id,obj);
		}
		else {
			throw new RemoteError(); 
		}
	}
	
	public static StaticLifecycleManager getInstance() {
		StaticLifecycleManager result = instance;
		if (result == null) {
			synchronized (mutex) {
				result = instance;
				if (result == null)
					instance = result = new StaticLifecycleManager();
			}
		}
		return result;
	}
	
}

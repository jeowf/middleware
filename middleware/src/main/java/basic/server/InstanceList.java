package basic.server;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import basic.RemoteError;

// Singleton
public class InstanceList {
	private static volatile InstanceList instance;
	private static Object mutex = new Object();
	
	private ConcurrentHashMap<Class<?>, HashMap<Long,Object>> instances;
		
	private InstanceList() {
		instances = new ConcurrentHashMap<Class<?>, HashMap<Long, Object>>();
		
		instances.put(String.class, new HashMap<Long,Object>());
		instances.get(String.class).put((long) 0, "pão");
	}

	public Object getInstance(Class<?> cls, long id) throws RemoteError{
		if (!instances.containsKey(cls) || !instances.get(cls).containsKey(id))
			throw new RemoteError(); // instancia nao encontrada
		
		return instances.get(cls).get(id);
	}
	
	public void addInstance(Class<?> cls, Long id, Object obj) throws RemoteError{
		if (!instances.containsKey(cls)) {
			instances.put(cls,new HashMap<Long,Object>());
		}
		
		if (instances.get(cls).containsKey(id))
			throw new RemoteError(); // id em uso
		
		instances.get(cls).put(id, obj);
	}
	
	public static InstanceList getInstance() {
		InstanceList result = instance;
		if (result == null) {
			synchronized (mutex) {
				result = instance;
				if (result == null)
					instance = result = new InstanceList();
			}
		}
		return result;
	}
	
}

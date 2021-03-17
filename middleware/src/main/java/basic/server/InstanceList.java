package basic.server;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import application.User;
import basic.RemoteError;

// Singleton
public class InstanceList {
	private static volatile InstanceList instance;
	private static Object mutex = new Object();
	private Long contador;
	
	private ConcurrentHashMap<Class<?>, HashMap<Long,Object>> instances;
		
	private InstanceList() {
		instances = new ConcurrentHashMap<Class<?>, HashMap<Long, Object>>();
		long x = 0;
		this.contador = x;
	}

	public Object getInstance(Class<?> cls, long id) throws RemoteError{
		if (!instances.containsKey(cls) || !instances.get(cls).containsKey(id))
			throw new RemoteError(); // instancia nao encontrada
		
		return instances.get(cls).get(id);
	}
	
	public Object rmInstance(Class<?> cls) {
		return instances.remove(cls);
	}
	
	public Long addInstance(Class<?> cls, Object obj) throws RemoteError{
		if (!instances.containsKey(cls)) {
			instances.put(cls,new HashMap<Long,Object>());
		}
		/*
		if (instances.get(cls).containsKey(id))
			throw new RemoteError(); // id em uso
		*/
		//Long id = (long) instances.get(cls).size();
		Long id = contador++;
		System.out.println("ID : " + id);
		instances.get(cls).put(id, obj);
		
		return id;
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

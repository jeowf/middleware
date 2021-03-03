package basic.server;

import java.util.HashMap;
import java.util.List;

import basic.RemoteError;

// Singleton
public class InstanceList {

	private HashMap<Class, List<Object>> instances;
	
	public Object getInstance(Class cls, long id) throws RemoteError{
		return null;
	}
	
}

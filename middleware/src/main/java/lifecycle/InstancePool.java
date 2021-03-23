package lifecycle;

import java.util.concurrent.LinkedBlockingQueue;

import basic.RemoteError;
import basic.server.InstanceList;

public class InstancePool implements InstancePoolInterface{
	
	private static volatile InstancePool instance;
	private static Object mutex = new Object();
	
	
	private LinkedBlockingQueue<Object> pool = new LinkedBlockingQueue<Object>();

	public Object getFreeInstance() throws RemoteError {
		try {
			return pool.take();
		} catch (InterruptedException e) {
			throw new RemoteError(); 
		}
	}

	public void putBackToPool(Object pooledSrvt) throws RemoteError {
		pool.add(pooledSrvt);
	}

	public InstancePool(int size) throws RemoteError {
		try {
			for (int i = 0; i < size; i++) {
				pool.add(new Object());
			}
		}
		catch(Exception  e) {
			throw new RemoteError();
    	}
	}

	public static InstancePool getInstance() throws RemoteError {
		InstancePool result = instance;
		if (result == null) {
			synchronized (mutex) {
				result = instance;
				if (result == null)
					instance = result = new InstancePool(24);
			}
		}
		return result;
	}
	
	
	

}

package lifecycle;

import java.util.concurrent.ConcurrentHashMap;

public class LifecycleManagerRegistry {
	
	private ConcurrentHashMap<Long, LifecycleManagerInterface> track;
	
	public LifecycleManagerInterface getLifecycleManager(Long id) {
		return track.get(id);
	}
	
	public void registerID(Long newID, LifecycleManagerInterface lm ) {
		track.put(newID, lm);
	}

	public LifecycleManagerRegistry() {
		this.track = new ConcurrentHashMap<Long, LifecycleManagerInterface>();
	};
	
	public void removeObjectID(Long id) {
		track.remove(id);
	}
}

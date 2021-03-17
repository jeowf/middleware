package lifecycle;

import basic.server.RemoteObject;

public class Servant {
	private RemoteObject ro;
	
	public RemoteObject create(Long id) {
		this.ro = new RemoteObject();
		return this.ro;
	}
	
	public void activate(Long id) {
		
	}
	
	public void desactivate(Long id) {
		
	}

	public void delete() {
		this.ro = null;
		System.gc();
	}

}

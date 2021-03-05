package general;

public class ServerResponseMessage extends Message {

	private Object object;
	
	public ServerResponseMessage(){
		super();
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	
}

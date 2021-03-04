package general;

public class RequestorMessage extends Message{
	
	private long id;
	
	private InvocationData invocationData;

	public RequestorMessage(long id, InvocationData invocationData) {
		super();
		this.id = id;
		this.invocationData = invocationData;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public InvocationData getInvocationData() {
		return invocationData;
	}

	public void setInvocationData(InvocationData invocationData) {
		this.invocationData = invocationData;
	}
}

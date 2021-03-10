package general;

public class LookUpMessage extends Message
{
	private long id;
	private String objectType;
	private InvocationData invocationData;

	public LookUpMessage(long id, String objectType, InvocationData invocationData) {
		super();
		this.id = id;
		this.objectType = objectType;
		this.invocationData = invocationData;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public InvocationData getInvocationData() {
		return invocationData;
	}

	public void setInvocationData(InvocationData invocationData) {
		this.invocationData = invocationData;
	}
}

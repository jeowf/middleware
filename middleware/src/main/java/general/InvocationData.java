package general;

public class InvocationData {
	
	private Object object;
	
	private String someMethod;

	public InvocationData(Object object, String someMethod) {
		super();
		this.object = object;
		this.someMethod = someMethod;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getSomeMethod() {
		return someMethod;
	}

	public void setSomeMethod(String someMethod) {
		this.someMethod = someMethod;
	}

}

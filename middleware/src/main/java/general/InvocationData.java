package general;

import java.io.Serializable;

public class InvocationData implements Serializable{
	
	private long objectID;
	private String someMethod;
	private Object[] args;
	private String[] argsTypes;
	private String objectClass;

	public InvocationData(long objectID, String someMethod, Object[] args, String[] argsTypes, String objectClass) {
		this.objectID = objectID;
		this.someMethod = someMethod;
		this.args = args;
		this.objectClass = objectClass;	
		
		this.argsTypes =argsTypes;
	}

	public long getObjectID() {
		return objectID;
	}

	public void setObjectID(long objectID) {
		this.objectID = objectID;
	}

	public String getSomeMethod() {
		return someMethod;
	}

	public void setSomeMethod(String someMethod) {
		this.someMethod = someMethod;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public String getObjectClass() {
		return objectClass;
	}

	public void setObjectClass(String objectClass) {
		this.objectClass = objectClass;
	}

	public String[] getArgsTypes() {
		return argsTypes;
	}

	public void setArgsTypes(String[] argsType) {
		this.argsTypes = argsType;
	}
	
}

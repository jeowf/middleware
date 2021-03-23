package basic.client;

public class ClientProxy {
	protected long realID = -1;
	protected Requestor requestor;
	protected ClientRequestHandler clientRequestHandler;

	protected Class objectClass;
	
	public ClientProxy(Class objectClass) {
		this.clientRequestHandler = new ClientRequestHandler();
		this.requestor = new Requestor(clientRequestHandler, objectClass);
	}
	
	// Generated Methods
}

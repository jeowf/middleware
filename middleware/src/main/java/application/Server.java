package application;


import basic.RemoteError;
import basic.server.InstanceList;
import basic.server.ServerRequestHandler;
import identification.lookup.LookUp;
import lifecycle.StaticInstance;


public class Server {

	private static InstanceList instanceList = InstanceList.getInstance();
	private static LookUp lookup = LookUp.getInstance();
	
	private static void addObject(Class<?> cls, Object obj, String objName) 
	{
		 Long x;
		try {
			x = instanceList.addInstance(cls,obj);
			lookup.bind(objName,x);
		} catch (RemoteError e) {
			System.out.println("Erro ao criar o objeto!!!");
		} 
	}
	
	public static void main(String[] args) {
		ServerRequestHandler srh = new ServerRequestHandler();
		StaticInstance staticInstance = new StaticInstance();
		
		
		
		addObject(User.class, new User("Nome", "senha", 10.0, 100), "User1 Teste");
		addObject(User.class, new User("Nome2", "senha2", 12.0, 120), "User2 Teste");
		
		try {
			staticInstance.create();
			srh.receive();
		} catch (RemoteError e) {
			e.printStackTrace();
		}
		
		System.out.println("Server Ended");
	}
	
}

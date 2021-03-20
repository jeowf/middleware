package basic.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import basic.Marshaller;
import basic.RemoteError;
import extension.server.InvocationContextServer;
import general.ServerResponseMessage;
import identification.lookup.LookUp;

public class ServerRequestHandler {
	private InvokerRegistry invokerRegistry;
	private ServerConfig serverConfig;
	private LookUp lookup;
	
	private static final int BUFFER_SIZE = 1024;
	private Selector selector = null;
	private String dataDecode = null;
	
	public ServerRequestHandler() {
		invokerRegistry = InvokerRegistry.getInstance();
		lookup = LookUp.getInstance();
		serverConfig = ServerConfig.getInstance();
	}
	
	public void receive() throws RemoteError{
		
		try 
		{
			InetAddress hostIP= serverConfig.getHostIP();
			int port = serverConfig.getServerPort();
			logger(String.format("Trying to accept connections on %s:%d...",
					hostIP.getHostAddress(), port));
			selector = Selector.open();
			ServerSocketChannel mySocket = ServerSocketChannel.open();
			ServerSocket serverSocket = mySocket.socket();
			InetSocketAddress address = new InetSocketAddress(hostIP, port);
			serverSocket.bind(address);
			mySocket.configureBlocking(false);
			//int ops = mySocket.validOps();
			// mySocket.register(selector, ops, null);
			mySocket.register(selector,SelectionKey.OP_ACCEPT);
			while (true) 
			{
				selector.select();
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> i = selectedKeys.iterator();
				while (i.hasNext()) 
				{
					SelectionKey key = i.next();
					if (key.isAcceptable()) 
					{
						processAcceptEvent(mySocket, key);
					} 
					else if (key.isReadable()) 
					{
						processReadEvent(key);
					}
					i.remove();
				}
			}
		} 
		catch (IOException e) 
		{
			logger(e.getMessage());
			e.printStackTrace();
		}
		
		//return "";
	}
	
	private long[] decode(String message) throws RemoteError{
				
		String idInvoker = message.substring(0, message.indexOf("{"));
		
		long id;
		long code;
		
		if(idInvoker.length() > 0) {
			String[] codes = idInvoker.split(",");
			id = Long.parseLong(codes[0]);
			code = Long.parseLong(codes[1]);
		}else {
			id = -1;
			code = -1;
		}
		
		logger("" + id);
		//logger("" + code);
		
		long[] bufferRetorno = new long[2];
		bufferRetorno[0] = id;
		bufferRetorno[1] = code;
		
		return bufferRetorno;
	}
	
	private String removeIdOfInvoker(String message) throws RemoteError{
		
		StringBuffer text = new StringBuffer(message);
						
		text.replace( 0 , text.indexOf("{", 0), "");
		
		dataDecode = text.toString();
		
		return dataDecode;
		//usar string.replace
	}
	
	private void processAcceptEvent(ServerSocketChannel mySocket,
			SelectionKey key) throws IOException 
	{
		// Accept the connection and make it non-blocking
		SocketChannel myClient = mySocket.accept();
		myClient.configureBlocking(false);
		// Register interest in reading this channel
		myClient.register(selector, SelectionKey.OP_READ);
	}
			
	private void processReadEvent(SelectionKey key) throws IOException 
	{
		// create a ServerSocketChannel to read the request
		SocketChannel myClient = (SocketChannel) key.channel();
		// Set up out 1k buffer to read data into
		ByteBuffer myBuffer = ByteBuffer.allocate(BUFFER_SIZE);
		myClient.read(myBuffer);
		String data = new String(myBuffer.array()).trim();
		if (data.length() > 0) 
		{
			try {
			logger(String.format("Message Received.....: %s\n", data));
			
			long[] codes = decode(data);
			
			data = removeIdOfInvoker(data);
			
			
			if(codes[0] == 28) 
			{
				Long idObj = lookup.lookup(data);
				
				logger(String.format("Message Lookup.....: %s\n", data));
				
				ServerResponseMessage srm = new ServerResponseMessage();
				
				srm.setObject(idObj);
				
				Marshaller marshaller = new Marshaller();
				
				String message = marshaller.marshal(srm);	
				
				ByteBuffer myBuffer2 = ByteBuffer.allocate(BUFFER_SIZE);
				myBuffer2.put(message.getBytes());
				myBuffer2.flip();
				int bytesWritten = myClient.write(myBuffer2);
			}
			else 
			{
				Invoker invoker = invokerRegistry.getInvoker(-1);
				
				logger(String.format("Message Change.....: %s\n", data));
				
				Object obj = invoker.invoke(dataDecode);
				
				// enviar obj de volta para o client handler
				
				ServerResponseMessage srm = new ServerResponseMessage();
				
				srm.setObject(obj);
				
				Marshaller marshaller = new Marshaller();
				
				String message = marshaller.marshal(srm);	
				
				ByteBuffer myBuffer2 = ByteBuffer.allocate(BUFFER_SIZE);
				myBuffer2.put(message.getBytes());
				myBuffer2.flip();
				int bytesWritten = myClient.write(myBuffer2);
			}			
					
			}catch (Exception e) {
					e.printStackTrace();
					System.out.println("TCP Server Terminating");		
			}
			
			myClient.close();
		}
	
	}
	
	public void logger(String msg) {
			System.out.println(msg);
		}
	
}

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
import general.ServerResponseMessage;

public class ServerRequestHandler {
	private InvokerRegistry invokerRegistry;
	private ServerConfig serverConfig;
	
	private static final int BUFFER_SIZE = 1024;
	private Selector selector = null;
	
	public void receive() throws RemoteError{
		
		try 
		{
			InetAddress hostIP= InetAddress.getLocalHost();
			int port = 10000;
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
	
	private long decode(String message) throws RemoteError{
		return 0;
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
			
			ServerResponseMessage srm = new ServerResponseMessage();
			srm.setObject("Recebido");
			
			Marshaller marshaller = new Marshaller();
			
			
			String message = marshaller.marshal(srm);	
			
			ByteBuffer myBuffer2 = ByteBuffer.allocate(BUFFER_SIZE);
			myBuffer2.put(message.getBytes());
			myBuffer2.flip();
			int bytesWritten = myClient.write(myBuffer2);			
					
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

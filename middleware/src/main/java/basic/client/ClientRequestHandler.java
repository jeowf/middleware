package basic.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import basic.RemoteError;

public class ClientRequestHandler {
	private ClientConfig clientConfig;
	
	private static final int BUFFER_SIZE = 1024;
	
	public ClientRequestHandler() {
				
	}
	
	public String send(String message) throws RemoteError {
		try {
			
			InetAddress hostIP= InetAddress.getLocalHost();
			InetSocketAddress myAddress = new InetSocketAddress(hostIP, 10000);
			SocketChannel myClient = SocketChannel.open(myAddress);			
	
			ByteBuffer myBuffer = ByteBuffer.allocate(BUFFER_SIZE);
			myBuffer.put(message.getBytes());
			myBuffer.flip();
			//int bytesWritten= myClient2.write(myBuffer2);
			myClient.write(myBuffer);
				
			ByteBuffer myBuffer2 = ByteBuffer.allocate(BUFFER_SIZE);
			//myBuffer.flip();
			
			try {
				myClient.read(myBuffer2);
				//myBuffer.flip();
				return new String(myBuffer2.array()).trim();
								
			}catch (Exception e) {	
				e.printStackTrace();
			}
			
			myClient.close();
						
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}	
		
		return null;
	}
}

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
	
	
	
	public ClientRequestHandler() {
		clientConfig = ClientConfig.getInstance();
	}
	
	public String send(String message) throws RemoteError {
		try {
			
			//InetAddress hostIP= InetAddress.getLocalHost();
			InetSocketAddress myAddress = new InetSocketAddress(
					clientConfig.getHostIP(), 
					clientConfig.getServerPort());
			SocketChannel myClient = SocketChannel.open(myAddress);			
	
			ByteBuffer myBuffer = ByteBuffer.allocate(ClientConfig.BUFFER_SIZE);
			myBuffer.put(message.getBytes());
			myBuffer.flip();
			//int bytesWritten= myClient2.write(myBuffer2);
			myClient.write(myBuffer);
				
			ByteBuffer myBuffer2 = ByteBuffer.allocate(ClientConfig.BUFFER_SIZE);
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

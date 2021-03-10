package application;

import java.io.IOException;

import basic.RemoteError;
import basic.client.ClientProxy;
import basic.client.ClientRequestHandler;
import basic.client.Requestor;

public class UserProxy extends ClientProxy{

	private long realID;
	private Requestor requestor;
	private ClientRequestHandler clientRequestHandler;
	
	public UserProxy() {
		this.clientRequestHandler = new ClientRequestHandler();
		this.requestor = new Requestor(clientRequestHandler, User.class);
		
		//try {
			//System.out.println(requestor.invoke(-1, "*constructor" , null, null));
			
			//Long id = (long) Math.floor((Double) requestor.invoke(-1, "*constructor" , null, null));
			//System.out.println("ID DO MENINO " + id);
			//this.realID = id;
		//} catch (RemoteError e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
	}
	
	// Função que irá consultar um objeto na lookup e retornar seu ID
	public void requestAOR(String objType) throws IOException
	{
		try {
			// Enviamos a mensagem com o código -2 (lookup message) para o invoker, esperando receber o ID do objeto
			Long id = (long) Math.floor((Double) requestor.invoke(-2, objType , null, null));
			System.out.println("ID DO MENINO " + id);
			this.realID = id;
		} catch (RemoteError e) {
			throw new IOException("Erro ao obter o ID do objeto requisitado");
		}
	}
	
	// TODO fazer um requisitor genérico.
	
	public String getNome() {
		
		try {
			return (String) requestor.invoke(realID, "getNome" , null, null);
		} catch (RemoteError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void setNome(String nome) {
		try {
			requestor.invoke(realID, "setNome" ,
					new Object[]{nome} ,
					new String[]{nome.getClass().getName()});
		} catch (RemoteError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public String getSenha() {
//		return senha;
//	}
//	public void setSenha(String senha) {
//		this.senha = senha;
//	}
//	public Double getSaldo() {
//		return saldo;
//	}
//	public void setSaldo(Double saldo) {
//		this.saldo = saldo;
//	}
//	public Integer getNumAcc() {
//		return numAcc;
//	}
//	public void setNumAcc(Integer numAcc) {
//		this.numAcc = numAcc;
//	}
}

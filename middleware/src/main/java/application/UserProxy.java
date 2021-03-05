package application;

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
		
		try {
			//System.out.println(requestor.invoke(-1, "*constructor" , null, null));
			
			Long id = (long) Math.floor((Double) requestor.invoke(-1, "*constructor" , null, null));
			System.out.println("ID DO MENINO " + id);
			this.realID = id;
		} catch (RemoteError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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

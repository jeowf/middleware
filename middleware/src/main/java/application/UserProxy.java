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
		this.realID = 0;
		this.clientRequestHandler = new ClientRequestHandler();
		this.requestor = new Requestor(clientRequestHandler, User.class);
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

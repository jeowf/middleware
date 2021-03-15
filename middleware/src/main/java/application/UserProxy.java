package application;

import java.io.IOException;

import basic.RemoteError;
import basic.client.ClientProxy;
import basic.client.ClientRequestHandler;
import basic.client.Requestor;

public class UserProxy extends ClientProxy{

	//Lembrar de revisar se ainda vamos precisar no final
	public UserProxy(String nome, String senha, Double saldo, Integer numAcc) {
		super(User.class);
		
		try {
			System.out.println(requestor.invoke(-1, "*constructor" , null, null));
			
			Long id = (long) Math.floor((Double) requestor.invoke(-1, "*constructor" , 
					new Object[]{nome,
							senha, 
							saldo,
							numAcc},
					new String[]{nome.getClass().getName(),
							senha.getClass().getName(),
							saldo.getClass().getName(),
							numAcc.getClass().getName()}));
			
			System.out.println("ID DO MENINO " + id);
			this.realID = id;
		} catch (RemoteError e) {
		    // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public UserProxy() {
		super(User.class);

		this.clientRequestHandler = new ClientRequestHandler();
		this.requestor = new Requestor(clientRequestHandler, User.class);
		
		
		try {
			System.out.println(requestor.invoke(-1, "*constructor" , null, null));
			
			Long id = (long) Math.floor((Double) requestor.invoke(-1, "*constructor" , null, null));
			System.out.println("ID DO MENINO " + id);
			this.realID = id;
		} catch (RemoteError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// TODO fazer um requisitor gen�rico.
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
	
	public String getSenha() {
		
		try {
			return (String) requestor.invoke(realID, "getSenha" , null, null);
		} catch (RemoteError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void setSenha(String senha) {
		try {
			requestor.invoke(realID, "setSenha" ,
					new Object[]{senha} ,
					new String[]{senha.getClass().getName()});
		} catch (RemoteError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Double getSaldo() {
		try {
			return (Double) requestor.invoke(realID, "getSaldo" , null, null);
		} catch (RemoteError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void setSaldo(Double saldo) {
		try {
			requestor.invoke(realID, "setSaldo" ,
					new Object[]{saldo} ,
					new String[]{saldo.getClass().getName()});
		} catch (RemoteError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Integer getNumAcc() {
		try {
			return (Integer) requestor.invoke(realID, "getNumAcc" , null, null);
		} catch (RemoteError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void setNumAcc(Integer numAcc) {
		try {
			requestor.invoke(realID, "setNumAcc" ,
					new Object[]{numAcc} ,
					new String[]{numAcc.getClass().getName()});
		} catch (RemoteError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

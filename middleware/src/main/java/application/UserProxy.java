package application;

public class UserProxy extends basic.client.ClientProxy{
	public UserProxy(){
		super(User.class);
	}

	public void requestAOR(String objType) throws java.io.IOException {
		try{
			Long id = (long) Math.floor((Double) requestor.invoke(-2, objType , null, null));
			this.realID = id;
		} catch (basic.RemoteError e){
			throw new java.io.IOException("Erro ao obter o ID do objeto requisitado");
		}
	}

	public void setSaldo(java.lang.Double arg0){
		try{
			requestor.invoke(realID,"setSaldo",new Object[]{arg0},new String[]{arg0.getClass().getName()});
		} catch (basic.RemoteError e){
			e.printStackTrace();
		}
	}

	public void setSenha(java.lang.String arg0){
		try{
			requestor.invoke(realID,"setSenha",new Object[]{arg0},new String[]{arg0.getClass().getName()});
		} catch (basic.RemoteError e){
			e.printStackTrace();
		}
	}

	public void setNumAcc(java.lang.Integer arg0){
		try{
			requestor.invoke(realID,"setNumAcc",new Object[]{arg0},new String[]{arg0.getClass().getName()});
		} catch (basic.RemoteError e){
			e.printStackTrace();
		}
	}

	public void setNome(java.lang.String arg0){
		try{
			requestor.invoke(realID,"setNome",new Object[]{arg0},new String[]{arg0.getClass().getName()});
		} catch (basic.RemoteError e){
			e.printStackTrace();
		}
	}

	public java.lang.String getSenha(){
		try{
			return (java.lang.String) requestor.invoke(realID,"getSenha",null,null);
		} catch (basic.RemoteError e){
			e.printStackTrace();
			return null;
		}
	}

	public java.lang.Integer getNumAcc(){
		try{
			return (java.lang.Integer) requestor.invoke(realID,"getNumAcc",null,null);
		} catch (basic.RemoteError e){
			e.printStackTrace();
			return null;
		}
	}

	public java.lang.String getNome(){
		try{
			return (java.lang.String) requestor.invoke(realID,"getNome",null,null);
		} catch (basic.RemoteError e){
			e.printStackTrace();
			return null;
		}
	}

	public java.lang.Double getSaldo(){
		try{
			return (java.lang.Double) requestor.invoke(realID,"getSaldo",null,null);
		} catch (basic.RemoteError e){
			e.printStackTrace();
			return null;
		}
	}

}
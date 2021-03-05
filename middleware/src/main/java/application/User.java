package application;

public class User {
	
	private String nome;
	private String senha;
	private Double saldo;
	private Integer   numAcc;
	
	public User(String nome, String senha, Double saldo, Integer numAcc) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.saldo = saldo;
		this.numAcc = numAcc;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public Integer getNumAcc() {
		return numAcc;
	}
	public void setNumAcc(Integer numAcc) {
		this.numAcc = numAcc;
	}

}

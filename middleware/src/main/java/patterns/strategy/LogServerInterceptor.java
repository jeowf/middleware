package patterns.strategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import general.InvocationData;
import general.LogDTO;

public class LogServerInterceptor extends InterceptorStrategy {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean ativo;
	private LogDTO log;
	
	public LogServerInterceptor() {
		this.ativo = false;
		this.log = new LogDTO();
	}
	
	public LogServerInterceptor( boolean ativo, LogDTO log ) {
		this.ativo = ativo;
		this.log = log;
		
	}
	
	public void buildInterceptor() {
		// Nada Definido ainda

	}

	public void prepareInterceptor( InvocationData invocationData ) {
		this.ativo = true;
		
		this.log.setOperationTipe( invocationData.getSomeMethod() );
		
		String idClient = String.valueOf( invocationData.getObjectID() );
		this.log.setRegistryClient( idClient );

	}
	
	public void writeLog() {
		
		StringBuilder builder = new StringBuilder();
		/*for( Map.Entry<String, Double> dicionarioChaves : dicionario.entrySet() ) {
			builder.append( dicionarioChaves.getKey() );
			builder.append( "," );
			builder.append( dicionarioChaves.getValue() );
			builder.append( "\r\n" );
			
			
			
		}*/
		builder.append( "{" );
		builder.append( "IdClient: " + this.log.getRegistryClient() + "; " );
		builder.append( "Operation: " + this.log.getOperationTipe() + "; " );
		builder.append( "DataTime->Dia " + this.log.getDateTime().get( Calendar.DAY_OF_MONTH ) + "; " );
		builder.append( "DataTime->Mês " + (this.log.getDateTime().get( Calendar.MONTH )+1) + "; " );
		builder.append( "DataTime->Ano " + this.log.getDateTime().get( Calendar.YEAR ) + "; ");
		int hour = this.log.getDateTime().get( Calendar.HOUR );
		int minute = this.log.getDateTime().get( Calendar.MINUTE );
		int second = this.log.getDateTime().get( Calendar.SECOND );
		builder.append( "DataTime->Hora " + hour + ":" + minute + ":" + second );
		builder.append( "}" );
		
		String conteudo = builder.toString().trim();
		//String caminhoSaidaArquivo = "/home/bruno/Documentos/BTI/PROGRAMAÇÃO DISTRIBUIDA/projeto_middleware/middleware/middleware/resources/log";
		String caminhoSaidaArquivo = "./resources/log";
		
		File fArquivo = null;
		try {
			/*
			escrever = new BufferedWriter( new FileWriter( caminhoSaidaArquivo+"/log.txt"  ) );
			escrever.write( conteudo );
			*/
			fArquivo = new File( caminhoSaidaArquivo+"/log.txt" );
			FileWriter fwArquivo = null;
			if( fArquivo.exists() ) {
				fwArquivo = new FileWriter( fArquivo, true );
				
			}else {
				fwArquivo = new FileWriter( fArquivo );
				
			}
			
			BufferedWriter escrever = new BufferedWriter( fwArquivo );
			escrever.write( conteudo + "\n" );
			
			escrever.close();
			fwArquivo.close();
			
		} catch (IOException e) {
			System.err.println( "Erro ao inserir linhas no arquivo: " + fArquivo );
			e.printStackTrace();
		}
		
	}
	
	

}

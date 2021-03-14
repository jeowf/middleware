package general;

import java.util.Calendar;

public class LogDTO {
	
	private String registryClient;
	private Calendar dateTime;
	private String operationTipe;
	
	public LogDTO() {
		registryClient = "";
		dateTime = Calendar.getInstance();
		operationTipe = "";
		
	}

	public String getRegistryClient() {
		return registryClient;
	}

	public void setRegistryClient(String registryClient) {
		this.registryClient = registryClient;
	}

	public Calendar getDateTime() {
		return dateTime;
	}

	public void setDateTime(Calendar dateTime) {
		this.dateTime = dateTime;
	}

	public String getOperationTipe() {
		return operationTipe;
	}

	public void setOperationTipe(String operationTipe) {
		this.operationTipe = operationTipe;
	}
	
	

}

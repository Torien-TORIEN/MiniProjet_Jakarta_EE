package qcm.services;

public class ReponseCode {
	private int responseCode;
	private String errorMessage;
	private String successMessage;
	
	public ReponseCode() {
		this.successMessage="";
		this.errorMessage="";
	}
	
	public ReponseCode(int code) {
		this.responseCode=code;
		this.successMessage="";
		this.errorMessage="";
	}
	
	public ReponseCode(int code, String message) {
		super();
		this.responseCode = code;
		if(200<=code && code<300)
			this.successMessage=message;
		else
			this.errorMessage=message;
			
	}

	public ReponseCode(int responseCode, String errorMessage, String successMessage) {
		super();
		this.responseCode = responseCode;
		this.errorMessage = errorMessage;
		this.successMessage = successMessage;
	}
	
	public void setMessage(int code, String message) {
		this.responseCode = code;
		if(200<=code && code<300)
			this.successMessage=message;
		else
			this.errorMessage=message;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	
	
	
	
}

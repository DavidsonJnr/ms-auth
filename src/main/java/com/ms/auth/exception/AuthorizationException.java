package com.ms.auth.exception;

import com.ms.auth.model.constant.JsonMessageType;

public class AuthorizationException extends AppException {
	
	/*
	 * Classe de excessões que deve ser usada como quando for um recurso necessário.
	 * Geralmente quando precisamos passar parâmetros para o objeto JCommomMessage
	 * Idela é usar as excessões próprias do JAVA
	 */
	private static final long serialVersionUID = 1L;
	
	
	public AuthorizationException(){
	}
	  
	public AuthorizationException(String errorKey, Object...param){
		super(errorKey, param);
	}
	
	public AuthorizationException(JsonMessageType type, String errorKey, Object...param){
		super(errorKey, errorKey, param);
	}
	
	public AuthorizationException(String errorKey){
		super(errorKey);
	}

	public String toString(){
	    return "ValidationException["+getKeyMessage()+"]";
	}  
}

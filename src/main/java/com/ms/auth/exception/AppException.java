package com.ms.auth.exception;

import com.ms.auth.model.constant.JsonMessageType;

public class AppException extends RuntimeException {
	
	/*
	 * Classe de excessões que deve ser usada como quando for um recurso necessário.
	 * Geralmente quando precisamos passar parâmetros para o objeto JCommomMessage
	 * Idela é usar as excessões próprias do JAVA
	 */
	private static final long serialVersionUID = 1L;
	
	private Object[] _parameters;
	private String _key;
	private JsonMessageType _type = JsonMessageType.ERROR;
	
	public AppException(){
	}
	  
	public AppException(String errorKey, Object...param){
		this._key = errorKey;
		this._parameters = param;
	}
	
	public AppException(JsonMessageType type, String errorKey, Object...param){
		this._type = type;
		this._key = errorKey;
		this._parameters = param;
	}
	
	public AppException(String errorKey){
		this._key = errorKey;
	}

	public String toString(){
	    return "AppException["+_key+"]";
	}  
	
	public String getKeyMessage() {
		return this._key;
	}
	
	public Object[] getInfoMessage() {
		return this._parameters;
	}
	
	public JsonMessageType getType() {
		return this._type;
	}

}

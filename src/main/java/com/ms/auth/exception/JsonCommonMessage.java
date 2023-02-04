package com.ms.auth.exception;

import com.ms.auth.model.constant.JsonMessageType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonCommonMessage {
	private JsonMessageType type;
	private String messageKey;
	private String[] options;
	private String[] info;
	
	public JsonCommonMessage() {
	}
	
	public JsonCommonMessage(JsonMessageType type, String messageKey) {
		super();
		this.type = type;
		this.messageKey = messageKey;
	}
	
	public JsonCommonMessage(JsonMessageType type, String messageKey, String[] info) {
		super();
		this.type = type;
		this.messageKey = messageKey;
		this.info = info;
	}
	
	public JsonCommonMessage(JsonMessageType type, String messageKey, String[] info, String[] options) {
		super();
		this.type = type;
		this.messageKey = messageKey;
		this.options = options;
	}
	
	public JsonCommonMessage(AppException appException) {
		super();
		createJsonCommomMessageAppException(appException);
		
	}
	
	public JsonCommonMessage(Exception exception) {
		super();
		if(exception instanceof AppException)
			createJsonCommomMessageAppException((AppException) exception);
		else {
			this.type = JsonMessageType.ERROR;
			this.messageKey = exception.getMessage();
		}
	}
	
	private void createJsonCommomMessageAppException(AppException appException) {
        this.type = appException.getType() != null ? appException.getType() : JsonMessageType.ERROR;
		this.messageKey = appException.getKeyMessage();
		
		if (appException.getInfoMessage() != null) {
			
			String[] errorSoon = new String[appException.getInfoMessage().length];
			for (int i = 0; i < appException.getInfoMessage().length; i++) {
				errorSoon[i] = appException.getInfoMessage()[i].toString();
			}
			this.info = errorSoon;
		}
	}
	
}

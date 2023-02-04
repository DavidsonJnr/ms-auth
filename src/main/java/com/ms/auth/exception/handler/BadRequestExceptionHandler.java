package com.ms.auth.exception.handler;

import com.ms.auth.exception.JsonCommonMessage;
import com.ms.auth.exception.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class BadRequestExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { ValidationException.class })
	protected @ResponseBody ResponseEntity<JsonCommonMessage> handleConflict(ValidationException ex, WebRequest request) {
		return handleException(ex, ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	private @ResponseBody ResponseEntity<JsonCommonMessage> handleException(Exception ex, ValidationException body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (HttpStatus.BAD_REQUEST.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}
		ex.printStackTrace();
		return new ResponseEntity<>(new JsonCommonMessage(body), headers, status);
	}
}

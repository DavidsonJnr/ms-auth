package com.ms.auth.exception.handler;

import com.ms.auth.exception.AppException;
import com.ms.auth.exception.JsonCommonMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;


@ControllerAdvice
public class GenericAppExceptionEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { AppException.class })
	protected ResponseEntity<JsonCommonMessage> handleConflict(RuntimeException ex, WebRequest request) {
		return handleException(ex, (AppException) ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	protected ResponseEntity<JsonCommonMessage> handleException(Exception ex, AppException body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (HttpStatus.BAD_REQUEST.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}
		return new ResponseEntity<>(new JsonCommonMessage(body), headers, status);
	}
}

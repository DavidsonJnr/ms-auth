package com.ms.auth.exception.handler;

import com.ms.auth.exception.AuthorizationException;
import com.ms.auth.exception.JsonCommonMessage;
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
public class AuthorizationExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = { AuthorizationException.class })
	protected @ResponseBody ResponseEntity<JsonCommonMessage> handleConflict(AuthorizationException ex, WebRequest request) {
		return handleException(ex, ex, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
	}

	private @ResponseBody ResponseEntity<JsonCommonMessage> handleException(Exception ex, AuthorizationException body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (HttpStatus.BAD_REQUEST.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}
		return new ResponseEntity<>(new JsonCommonMessage(body), headers, status);
	}
}

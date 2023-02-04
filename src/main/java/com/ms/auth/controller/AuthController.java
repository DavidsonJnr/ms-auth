package com.ms.auth.controller;

import com.ms.auth.model.request.AuthenticationRequest;
import com.ms.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> auth(@RequestBody AuthenticationRequest authenticationRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(authService.auth(authenticationRequest));
	}
}

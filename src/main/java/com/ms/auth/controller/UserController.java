package com.ms.auth.controller;

import com.ms.auth.model.request.RegisterRequest;
import com.ms.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

	private final UserService userService;
	
	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(registerRequest));
	}

	// @PreAuthorize("hasRole('USER')")
	@GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

}
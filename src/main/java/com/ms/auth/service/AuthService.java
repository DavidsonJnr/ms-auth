package com.ms.auth.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ms.auth.config.auth.service.UserDetailsImpl;
import com.ms.auth.config.auth.util.JwtUtil;
import com.ms.auth.exception.AppException;
import com.ms.auth.model.constant.MsgConstants;
import com.ms.auth.model.request.AuthenticationRequest;
import com.ms.auth.model.response.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	public AuthenticationResponse auth(AuthenticationRequest authenticationRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token = jwtUtil.generateJwtToken(authentication);
			
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream()
					.map(GrantedAuthority::getAuthority)
					.collect(Collectors.toList());

			return AuthenticationResponse.builder()
					.token(token)
					.id(userDetails.getUser().getId())
					.email(userDetails.getUser().getEmail())
					.name(userDetails.getUser().getName())
					.roles(roles)
					.build();
		} catch (Exception e) {
			log.error("ERROR TO AUTHENTICATE: " + e.getMessage());
			throw new AppException(MsgConstants.ERROR.AUTENTICACAO);
		}
	}

}

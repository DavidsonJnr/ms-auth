package com.ms.auth.model.request;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.ms.auth.model.constant.TypeRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class RegisterRequest {

	private String name;
	private String email;
	private String lastName;
	private String password;
	private Set<TypeRole> role;
	
}

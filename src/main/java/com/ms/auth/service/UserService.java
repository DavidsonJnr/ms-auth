package com.ms.auth.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ms.auth.exception.AppException;
import com.ms.auth.model.constant.MsgConstants;
import com.ms.auth.model.constant.TypeRole;
import com.ms.auth.model.entity.Role;
import com.ms.auth.model.entity.User;
import com.ms.auth.model.request.RegisterRequest;
import com.ms.auth.repository.role.RoleReadRepository;
import com.ms.auth.repository.user.UserReadRepository;
import com.ms.auth.repository.user.UserWriteRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserReadRepository userReadRepository;
	
	private final RoleReadRepository roleReadRepository;
	
	private final UserWriteRepository userWriteRepository;
	
	private final PasswordEncoder encoder;
	
	public User register(RegisterRequest registerRequest) {
		if (BooleanUtils.isTrue(userReadRepository.existsByEmail(registerRequest.getEmail()))) {
			throw new AppException(MsgConstants.ERROR.EMAIL_EXISTENTE);
		}

		User user = User.builder()
				.email(registerRequest.getEmail())
				.active(Boolean.TRUE)
				.name(registerRequest.getName())
				.lastName(registerRequest.getLastName())
				.password(encoder.encode(registerRequest.getPassword()))
				.build();

		Set<TypeRole> strRoles = registerRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (CollectionUtils.isEmpty(strRoles)) {
			Role userRole = roleReadRepository.findByTypeRole(TypeRole.ROLE_USER)
					.orElseThrow(() -> new AppException(MsgConstants.ERROR.ROLE_NAO_ENCONTRADA));
			roles.add(userRole);
		} 
		else {
			strRoles.forEach(role -> {
				Role userRole = roleReadRepository.findByTypeRole(role)
						.orElseThrow(() -> new AppException(MsgConstants.ERROR.ROLE_NAO_ENCONTRADA));
				roles.add(userRole);
			});
		}

		user.setRoles(roles);
		return userWriteRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userReadRepository.findAll();
	}

}
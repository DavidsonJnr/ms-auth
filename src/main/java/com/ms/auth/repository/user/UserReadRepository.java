package com.ms.auth.repository.user;


import com.ms.auth.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserReadRepository extends JpaRepository<User, Long>, UserReadRepositoryCustom {
	Optional<User> findByEmail(String email);

	Boolean existsByEmail(String email);
}

package com.ms.auth.repository.role;


import com.ms.auth.model.constant.TypeRole;
import com.ms.auth.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleReadRepository extends JpaRepository<Role, Long>, RoleReadRepositoryCustom {
	Optional<Role> findByTypeRole(TypeRole role);
}

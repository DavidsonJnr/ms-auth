package com.ms.auth.repository.user;

import com.ms.auth.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserWriteRepository extends JpaRepository<User, Long>, UserWriteRepositoryCustom {

}

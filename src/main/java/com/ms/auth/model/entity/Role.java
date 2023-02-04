package com.ms.auth.model.entity;

import java.io.Serializable;

import com.ms.auth.model.constant.TypeRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_role")
@Getter
@Setter
public class Role extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false, length = 50, unique = true)
	private TypeRole typeRole;

}

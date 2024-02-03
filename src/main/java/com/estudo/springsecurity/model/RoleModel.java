package com.estudo.springsecurity.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.estudo.springsecurity.enums.RoleName;

@Entity
@Table(name="TB_ROLE")
public class RoleModel implements GrantedAuthority, Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long roleId;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, unique = true)
	private RoleName roleName;
	
	
	@Override
	public String getAuthority() {
		return this.roleName.toString();
	}

}

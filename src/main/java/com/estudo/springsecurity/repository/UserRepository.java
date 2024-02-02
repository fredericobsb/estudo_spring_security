package com.estudo.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import com.estudo.springsecurity.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID>{

	Optional<UserModel>findByUsername(String username);
}

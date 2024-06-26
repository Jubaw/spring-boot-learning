package com.tpe.repository;

import com.tpe.domain.User;
import com.tpe.exceptions.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User>  findByUserName(String username) throws ResourceNotFoundException;

}

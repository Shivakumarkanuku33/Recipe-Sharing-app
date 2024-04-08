package com.shiva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shiva.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{


	public User findByEmail(String string);
}

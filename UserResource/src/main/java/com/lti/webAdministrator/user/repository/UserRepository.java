package com.lti.webAdministrator.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.webAdministrator.user.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
	public Users findByEmailId(String email);
	public Users findByUsername(String username);
}

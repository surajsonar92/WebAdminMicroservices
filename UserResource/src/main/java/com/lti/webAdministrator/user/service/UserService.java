package com.lti.webAdministrator.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lti.webAdministrator.user.entity.Users;

@Service
public interface UserService {
	
	public List<Users> getAllUser();
	public Users getUserById(Long id);	
	public Users saveUser(Users user);
	public void updateUser(Users user);
	public void deleteUser(Long id);
	public boolean checkUserExists(Long id);
	public boolean checkUsernameExists(String username);
	public Users getByUserName(String username);

}

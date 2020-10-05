package com.lti.webAdministrator.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lti.webAdministrator.user.entity.Users;
import com.lti.webAdministrator.user.exceptions.UserNotFoundException;
import com.lti.webAdministrator.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<Users> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public Users getUserById(Long id) {
		Optional<Users> userData=userRepository.findById(id);
		if(!userData.isPresent()) {
			throw new UserNotFoundException("User Not Found with id :"+id);
		}
		return userData.get();
	}

	@Override
	public Users saveUser(Users user) {
		Users userInfo=new Users();
		userInfo.setName(user.getName());
		userInfo.setEmailId(user.getEmailId());
		userInfo.setCreatedAt(user.getCreatedAt());
		userInfo.setUpdatedAt(user.getUpdatedAt());
		userInfo.setUsername(user.getUsername());
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userInfo.setPassword(passwordEncoder.encode(user.getPassword()));
		System.out.println("Password : "+userInfo.getPassword());
		return userRepository.save(userInfo);
	}

	@Override
	public void updateUser(Users user) {
		Users userInfo=new Users();
		userInfo.setName(user.getName());
		userInfo.setEmailId(user.getEmailId());
		userInfo.setCreatedAt(user.getCreatedAt());
		userInfo.setUpdatedAt(user.getUpdatedAt());
		userInfo.setUsername(user.getUsername());
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userInfo.setPassword(passwordEncoder.encode(user.getPassword()));
		
		userRepository.save(userInfo);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
		
	}

	@Override
	public boolean checkUserExists(Long id) {
		return userRepository.existsById(id);
	}

	@Override
	public Users getByUserName(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public boolean checkUsernameExists(String username) {
		Users userData=userRepository.findByUsername(username);
		return userData!=null;
	}

}

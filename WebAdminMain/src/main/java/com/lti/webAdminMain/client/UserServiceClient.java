package com.lti.webAdminMain.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lti.webAdminMain.dto.UserDto;

@FeignClient(url="${user.service.endpoint}",name="USERSERVICE")
public interface UserServiceClient {

	@GetMapping(path = "/auth/{username}")
	public UserDto getUserByUsername(@PathVariable("username") String username);
	
	@GetMapping(path = "/all")
	public  List<UserDto> getUsers();
}

package com.lti.webAdminMain.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lti.webAdminMain.dto.UserDto;
import com.lti.webAdminMain.models.AuthenticationRequest;

@FeignClient(url="${user.auth.endpoint}",name="USERAUTH")
public interface UserAuthClient {

	@GetMapping(path = "/auth/{username}")
	public UserDto getUserByUsername(@PathVariable("username") String username);
	
	@PostMapping(path = "/authenticate")
	public ResponseEntity<?> auth(@RequestBody AuthenticationRequest authenticationRequest);
}

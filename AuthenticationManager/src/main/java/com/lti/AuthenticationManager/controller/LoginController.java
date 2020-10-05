package com.lti.AuthenticationManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lti.AuthenticationManager.client.UserServiceClient;
import com.lti.AuthenticationManager.dto.UserDto;
import com.lti.AuthenticationManager.models.AuthenticationRequest;
import com.lti.AuthenticationManager.models.AuthenticationResponse;
import com.lti.AuthenticationManager.service.MyUserDetailsService;
import com.lti.AuthenticationManager.util.JwtUtil;

@RestController
@EnableFeignClients(basePackages = "com.lti.AuthenticationManager.client")
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private UserServiceClient userServiceClient;

	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}

	@GetMapping("/auth/{username}")
	public UserDto getGroupById(@PathVariable("username") String username) {
		return userServiceClient.getUserByUsername(username);
		
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}

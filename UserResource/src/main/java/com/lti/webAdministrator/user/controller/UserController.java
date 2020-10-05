package com.lti.webAdministrator.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.webAdministrator.client.UserGroupClient;
import com.lti.webAdministrator.dto.UserGroupDto;
import com.lti.webAdministrator.user.entity.Users;
import com.lti.webAdministrator.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController

@CrossOrigin
@RequestMapping(path = "v1/users")

@Api(value="Web Administrator", description="Web Administrator system to manage web users")
public class UserController {

	@Autowired
	private UserService userService;
	
	

	@GetMapping("/all")
	@ApiOperation(value = "View a list of users",response = Users.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
	public ResponseEntity<Users> getUsers() {
		List<Users> userInfo =userService.getAllUser();
		return new ResponseEntity(userInfo, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Fetch user data")
	public ResponseEntity<Users> getUserById(@PathVariable("id") Long userId) {
		if (userService.checkUserExists(userId)) {
			Users userData = userService.getUserById(userId);
			return new ResponseEntity(userData, HttpStatus.OK);
		} else {
			return new ResponseEntity("User not found with id : " + userId, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(path = "/auth/{username}")
	@ApiOperation(value = "Fetch user data")
	public ResponseEntity<Users> getUserByUsername(@PathVariable("username") String username) {
		if (userService.checkUsernameExists(username)) {
			Users userData = userService.getByUserName(username);
			return new ResponseEntity(userData, HttpStatus.OK);
		} else {
			return new ResponseEntity("User not found with username : " + username, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(path = "/create", consumes = "application/json",produces = "application/json")
	@ApiOperation(value = "Add user")
	public ResponseEntity<Users> createUser(@RequestBody Users user) {
		userService.saveUser(user);
		return new ResponseEntity("User created successfully!", HttpStatus.CREATED);
	}

	@DeleteMapping(path = "delete/{id}")
	@ApiOperation(value = "Delete a user")
	public ResponseEntity<Users> delete(@PathVariable("id") Long userId) {
		if (userService.checkUserExists(userId)) {
			userService.deleteUser(userId);
			return new ResponseEntity("User deleted successfully!", HttpStatus.OK);
		} else {
			return new ResponseEntity("User not found with id : " + userId, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path = "update/{id}")
	@ApiOperation(value = "Update a product")
	public ResponseEntity<Users> update(@PathVariable("id") Long userId, @RequestBody Users user) {

		if (userService.checkUserExists(userId)) {
			userService.saveUser(user);
			return new ResponseEntity("User updated successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity("User not found with id : " + userId, HttpStatus.NOT_FOUND);
		}
	}
	
	

}

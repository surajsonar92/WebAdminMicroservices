package com.lti.userGroupService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.lti.userGroupService.entity.UserGroup;
import com.lti.userGroupService.service.UserGroupServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController

@CrossOrigin
@RequestMapping(path = "v1/usergroup")
@Api(value="Web Administrator- User Group Service", description="User Group Service to manage users group")

public class UserGroupController {
	
	@Autowired
	private UserGroupServices userGroupServices;
	
	@GetMapping("/all")
	@ApiOperation(value = "View a list of user groups",response = UserGroup.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user groups"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
	public ResponseEntity<UserGroup> getUserGroups() {
		List<UserGroup> groups =userGroupServices.getAllGroup();
		return new ResponseEntity(groups, HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Fetch user group")
	public ResponseEntity<UserGroup> getUserGroupById(@PathVariable("id") Long userId) {
		if (userGroupServices.checkGroupExists(userId)) {
			UserGroup userGroupData = userGroupServices.getGroupById(userId);
			return new ResponseEntity(userGroupData, HttpStatus.OK);
		} else {
			return new ResponseEntity("Group not found with id : " + userId, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(path = "/create", consumes = "application/json",produces = "application/json")
	@ApiOperation(value = "Add user Group")
	public ResponseEntity<UserGroup> createUserGroup(@RequestBody UserGroup user) {
		userGroupServices.saveGroup(user);
		return new ResponseEntity("Group created successfully!", HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "delete/{id}")
	@ApiOperation(value = "Delete a user")
	public ResponseEntity<UserGroup> deleteGroup(@PathVariable("id") Long userId) {
		if (userGroupServices.checkGroupExists(userId)) {
			userGroupServices.deleteGroup(userId);
			return new ResponseEntity("Group deleted successfully!", HttpStatus.OK);
		} else {
			return new ResponseEntity("Group not found with id : " + userId, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path = "update/{id}")
	@ApiOperation(value = "Update a product")
	public ResponseEntity<UserGroup> updateGroup(@PathVariable("id") Long userId, @RequestBody UserGroup group) {

		if (userGroupServices.checkGroupExists(userId)) {
			userGroupServices.saveGroup(group);
			return new ResponseEntity("Group updated successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity("Group not found with id : " + userId, HttpStatus.NOT_FOUND);
		}
	}

}

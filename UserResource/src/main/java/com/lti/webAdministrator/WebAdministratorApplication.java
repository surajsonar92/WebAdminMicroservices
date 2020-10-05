package com.lti.webAdministrator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lti.webAdministrator.client.UserGroupClient;
import com.lti.webAdministrator.dto.UserGroupDto;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableFeignClients
@RestController
public class WebAdministratorApplication {

	@Autowired
	private UserGroupClient userGroupClient;

	@GetMapping("/groups")
	public ResponseEntity<UserGroupDto> getUserGroups() {
		List<UserGroupDto> userGroups = userGroupClient.getAllGroups();
		return new ResponseEntity(userGroups, HttpStatus.OK);
	}
	@GetMapping("groups/{id}")
	public ResponseEntity<UserGroupDto> getGroupById(@PathVariable("id") Long groupId) {
		UserGroupDto group = userGroupClient.getUserGroupById(groupId);
		return new ResponseEntity(group, HttpStatus.OK);
	}

	public static void main(String[] args) {
		SpringApplication.run(WebAdministratorApplication.class, args);
	}

}

package com.lti.webAdministrator.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lti.webAdministrator.dto.UserGroupDto;


@FeignClient(url="{$usergroup.service.endpoint}",name="USERGROUP")
public interface UserGroupClient {

	@GetMapping("/all")
	public List<UserGroupDto> getAllGroups();
	@GetMapping(path = "/{id}")
	public UserGroupDto getUserGroupById(@PathVariable("id") Long groupId);
}

package com.lti.userGroupService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lti.userGroupService.entity.UserGroup;

@Service
public interface UserGroupServices {
	
	public List<UserGroup> getAllGroup();
	public UserGroup getGroupById(Long id);	
	public UserGroup saveGroup(UserGroup user);
	public void updateGroup(UserGroup user);
	public void deleteGroup(Long id);
	public boolean checkGroupExists(Long id);

}

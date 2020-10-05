package com.lti.userGroupService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.userGroupService.entity.UserGroup;
import com.lti.userGroupService.exception.UserGroupNotFoundException;
import com.lti.userGroupService.repository.UserGroupRepository;

@Service
public class UserGroupServicesImpl implements UserGroupServices {

	@Autowired
	private UserGroupRepository userGroupRepository;

	@Override
	public List<UserGroup> getAllGroup() {

		return userGroupRepository.findAll();
	}

	@Override
	public UserGroup getGroupById(Long id) {
		Optional<UserGroup> groupData = userGroupRepository.findById(id);
		if (!groupData.isPresent()) {
			throw new UserGroupNotFoundException("UserGroup Not Found with id :" + id);
		}
		return groupData.get();
	}

	@Override
	public UserGroup saveGroup(UserGroup userGroup) {
		return userGroupRepository.save(userGroup);
	}

	@Override
	public void updateGroup(UserGroup userGroup) {
		userGroupRepository.save(userGroup);

	}

	@Override
	public void deleteGroup(Long id) {
		userGroupRepository.deleteById(id);

	}

	@Override
	public boolean checkGroupExists(Long id) {
		return userGroupRepository.existsById(id);
	}

}

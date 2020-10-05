package com.lti.userGroupService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lti.userGroupService.entity.UserGroup;



@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

}

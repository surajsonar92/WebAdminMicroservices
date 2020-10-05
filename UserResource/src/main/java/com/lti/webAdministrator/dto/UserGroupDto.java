package com.lti.webAdministrator.dto;

import org.springframework.stereotype.Component;

@Component
public class UserGroupDto {

	private Long id;

	private String name;

	private String privileges;
	
	public UserGroupDto() {}

	public UserGroupDto(Long id, String name, String privileges) {
		super();
		this.id = id;
		this.name = name;
		this.privileges = privileges;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrivileges() {
		return privileges;
	}

	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}
	
	

}

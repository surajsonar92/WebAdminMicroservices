package com.lti.userGroupService.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class UserGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	private String name;
	
	private String privileges;
	
	public UserGroup() {}
	
	public UserGroup(Long id, String name, String privileges, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.privileges = privileges;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;
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
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	

}

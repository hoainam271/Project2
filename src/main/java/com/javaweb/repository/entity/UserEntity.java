package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username", nullable = false, unique = true)
	private String userName;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "fullname")
	private String fullName;

	
//	public List<UserRoleEntity> getUserRoleEntities() {
//		return userRoleEntities;
//	}
//
//	public void setUserRoleEntities(List<UserRoleEntity> userRoleEntities) {
//		this.userRoleEntities = userRoleEntities;
//	}
	
	// dùng 1 entity phụ để kết nối 2 bảng khác khi nó có quan hệ many to many
//	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
//	private List<UserRoleEntity> userRoleEntities = new ArrayList<>();
	
	// liên kết thẳng luôn mà không qua bảng thứ 3 khi quan hệ là many to many
		@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(name="user_role", // cái này là tên bảng thứ 3 mà khi liên kết 2 bảng có quan hệ manytomany
		joinColumns = @JoinColumn(name="userid",nullable=false), // khóa ngoại thứ 1, trỏ tới class hiện tại
		inverseJoinColumns = @JoinColumn(name="roleid",nullable = false) //khóa ngoại thứ 2, trỏ tới class bên dưới
		)
		private List<RoleEntity> roles = new ArrayList<>(); // trả thẳng về 1 list RoleEntity
	
	public List<RoleEntity> getRoles() {
			return roles;
		}

		public void setRoles(List<RoleEntity> roles) {
			this.roles = roles;
		}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "status", nullable = false)
	private Integer status;

	@Column(name = "email")
	private String email;
}

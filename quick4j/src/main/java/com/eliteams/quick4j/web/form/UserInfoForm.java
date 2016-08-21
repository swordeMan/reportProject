package com.eliteams.quick4j.web.form;

import com.eliteams.quick4j.web.model.User;
import com.eliteams.quick4j.web.model.UserInfo;

public class UserInfoForm {
	
	private User user;
	
	private UserInfo userInfo;
	
	private long[] roleIds;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(long[] roleIds) {
		this.roleIds = roleIds;
	}

}

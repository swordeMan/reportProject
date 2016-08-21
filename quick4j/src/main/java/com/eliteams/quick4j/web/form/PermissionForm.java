package com.eliteams.quick4j.web.form;

import com.eliteams.quick4j.web.model.Role;

public class PermissionForm {
	
	Role role;
	
	long[] permissions;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public long[] getPermissions() {
		return permissions;
	}

	public void setPermissions(long[] permissions) {
		this.permissions = permissions;
	}


	
}

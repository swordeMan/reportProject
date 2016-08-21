package com.eliteams.quick4j.web.model;

public class UserRole {
    private Long id;

    private Long userId;

    private Long roleId;
    
    public UserRole(long userId,long roleId){
    	this.userId = userId;
    	this.roleId = roleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
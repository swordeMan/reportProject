package com.eliteams.quick4j.web.model;

public class RolePermission {
    private Long id;

    private Long roleId;

    private Long permissionId;
    
    public RolePermission(){
    	
    }
    
    public RolePermission(Long roleId,Long permissionId){
    	this.roleId = roleId;
    	this.permissionId = permissionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}
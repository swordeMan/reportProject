package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.web.model.RolePermission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RolePermission record);

    RolePermission selectByPrimaryKey(Long id);

    List<RolePermission> selectAll();

    int updateByPrimaryKey(RolePermission record);
    
    List<RolePermission> selectPermissionListByRoleId(long roleId);
    
    int deletePermissionByRoleId(long roleId);
}
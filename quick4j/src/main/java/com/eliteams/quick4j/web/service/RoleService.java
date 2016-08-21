package com.eliteams.quick4j.web.service;

import java.util.List;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.core.generic.GenericService;
import com.eliteams.quick4j.web.form.PermissionForm;
import com.eliteams.quick4j.web.model.Role;
import com.eliteams.quick4j.web.model.User;
import com.eliteams.quick4j.web.model.UserInfo;

/**
 * 角色 业务接口
 * 
 * @author StarZou
 * @since 2014年6月10日 下午4:15:01
 **/
public interface RoleService extends GenericService<Role, Long> {
    /**
     * 通过用户id 查询用户 拥有的角色
     * 
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(Long userId);
    /**
     * 
     * @param page
     * @param keywords
     * @return
     */
    List<Role> getRoleByPage(Page page,String keywords);
    
    void addRole(PermissionForm permissionForm);
    
    void updateRole(PermissionForm permissionForm);
    
    String relateRolesForUser(long userId);
    
    void setRolesToPage(Page<UserInfo> page);
    
    void deleteRole(long roleId);
}

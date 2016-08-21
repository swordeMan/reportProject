package com.eliteams.quick4j.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.core.generic.GenericDao;
import com.eliteams.quick4j.core.generic.GenericServiceImpl;
import com.eliteams.quick4j.web.dao.RoleMapper;
import com.eliteams.quick4j.web.dao.RolePermissionMapper;
import com.eliteams.quick4j.web.form.PermissionForm;
import com.eliteams.quick4j.web.model.Role;
import com.eliteams.quick4j.web.model.RolePermission;
import com.eliteams.quick4j.web.model.User;
import com.eliteams.quick4j.web.model.UserInfo;
import com.eliteams.quick4j.web.service.RoleService;

/**
 * 角色Service实现类
 *
 * @author StarZou
 * @since 2014年6月10日 下午4:16:33
 */
@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, Long> implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public GenericDao<Role, Long> getDao() {
        return roleMapper;
    }

    @Override
    public List<Role> selectRolesByUserId(Long userId) {
        return roleMapper.selectRolesByUserId(userId);
    }

	@Override
	public List<Role> getRoleByPage(Page page, String keywords) {
		return roleMapper.getRoleByPageAndKeywords(page, keywords);
	}

	@Override
	public void addRole(PermissionForm permissionForm) {
		Role role = permissionForm.getRole();
		roleMapper.insert(role);
		long id = role.getId();
		long[] permissions = permissionForm.getPermissions();
		insertRolePermission(id,permissions);
	}
	/**
	 * 插入数据，关联表update=delete+insert
	 * @param id
	 * @param permissions
	 */
	private void insertRolePermission(long id,long[] permissions){
		for(long permission:permissions){
			RolePermission rolePermission = new RolePermission(id,permission);
			rolePermissionMapper.insert(rolePermission);
		}
	}

	@Override
	public String relateRolesForUser(long userId) {
		List<Role> roleInfos = selectRolesByUserId(userId);
		StringBuffer result = new StringBuffer();
		for(Role role:roleInfos){
			result.append(role.getRoleName()+"  ");
		}
		return result.toString();
		
	}

	@Override
	public void setRolesToPage(Page<UserInfo> page) {
		List<UserInfo> userInfoList = page.getResult();
		for(UserInfo ui:userInfoList){
			ui.setRoles(relateRolesForUser(ui.getUserId()));
		}
	}

	@Override
	public void updateRole(PermissionForm permissionForm) {
		Role role = permissionForm.getRole();
		roleMapper.updateByPrimaryKey(role);
		rolePermissionMapper.deletePermissionByRoleId(role.getId());
		long[] permissions = permissionForm.getPermissions();
		insertRolePermission(role.getId(),permissions);
	}

	@Override
	public void deleteRole(long roleId) {
		roleMapper.deleteByPrimaryKey(roleId);
		rolePermissionMapper.deletePermissionByRoleId(roleId);
	}
	
	

}

package com.eliteams.quick4j.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eliteams.quick4j.core.entity.Json;
import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.core.generic.GenericController;
import com.eliteams.quick4j.web.dao.RoleMapper;
import com.eliteams.quick4j.web.dao.RolePermissionMapper;
import com.eliteams.quick4j.web.form.PermissionForm;
import com.eliteams.quick4j.web.form.UserInfoForm;
import com.eliteams.quick4j.web.model.Role;
import com.eliteams.quick4j.web.model.RolePermission;
import com.eliteams.quick4j.web.model.User;
import com.eliteams.quick4j.web.model.UserInfo;
import com.eliteams.quick4j.web.security.PermissionSign;
import com.eliteams.quick4j.web.service.RoleService;

/**
 * 用户控制器
 * 
 * @author zhangsh
 * @since 2014年5月28日 下午3:54:00
 **/
@Controller
@RequestMapping(value = "/role")
public class RoleController extends GenericController {
	
	private static Logger log = Logger.getLogger(RoleController.class); // 初始化日志对象

	@Resource
    private RoleService roleService;
	
	@Resource
    private RoleMapper roleMapper;
	
	@Resource
    private RolePermissionMapper rolePermissionMapper;
	
	/**
     * 角色列表查看
     */
    @RequestMapping(value = "/list")
    @RequiresPermissions(value = PermissionSign.ROLE_VIEW)
    public String list(Model model,Page<UserInfo> page,HttpServletRequest request) {
    	try {
    		String keywords = request.getParameter("keywords");
    		roleService.getRoleByPage(page, keywords);
		} catch (Exception e) {
			log.error("角色列表展示错误"+e);
		}
    	model.addAttribute(page);
        return "gernal/role/list";
    }
    
    @RequestMapping(value = "/add")
    @RequiresPermissions(value = PermissionSign.USER_CREATE)
    public String add() {
        return "gernal/role/add";
    }
    
    @RequestMapping(value = "/insert")
    @ResponseBody
    public Json insert(@ModelAttribute("permissionForm")PermissionForm permissionForm,Json json) {
    	try {
    		roleService.addRole(permissionForm);
		} catch (Exception e) {
			log.error("角色新增出错"+e);
			return json.ajaxDoneError();
		}
    	
    	return json.ajaxDoneSuccess();
    }
    
    @RequestMapping(value = "/edit/{roleId}")
    @RequiresPermissions(value = PermissionSign.USER_UPDATE)
    public String edit(@PathVariable("roleId") long roleId, Model model) {
    	Role role = roleMapper.selectByPrimaryKey(roleId);
    	model.addAttribute("role",role);
        return "gernal/role/edit";
    }
    
    @RequestMapping(value = "/permissionCheckBox")
    @ResponseBody
    public List<RolePermission> getRoleCheckBox(long roleId){
    	List<RolePermission> rolePermissionList = rolePermissionMapper.selectPermissionListByRoleId(roleId);
		return rolePermissionList;
    }
    
    @RequestMapping(value = "/update")
    @ResponseBody
    public Json update(@ModelAttribute("permissionForm")PermissionForm permissionForm, Json json) {
    	try {
    		roleService.updateRole(permissionForm);
		} catch (Exception e) {
			log.error("角色更新出错"+e);
			return json.ajaxDoneError();
		}
    	
    	return json.ajaxDoneSuccess();
    }
    
    @RequestMapping("/delete/{roleId}")
    @ResponseBody
    @RequiresPermissions(value = PermissionSign.ROLE_DELETE)
	public Json delete(@PathVariable("roleId") long roleId ,Json json) {
    	try {
    		roleService.deleteRole(roleId);
		} catch (Exception e) {
			log.error("角色删除出错"+e);
			return json.ajaxDoneError();
		}
		return json.ajaxDoneSuccess();
	}
}

package com.eliteams.quick4j.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eliteams.quick4j.core.entity.Json;
import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.core.generic.GenericController;
import com.eliteams.quick4j.web.dao.RoleMapper;
import com.eliteams.quick4j.web.form.PasswordForm;
import com.eliteams.quick4j.web.form.UserInfoForm;
import com.eliteams.quick4j.web.model.Role;
import com.eliteams.quick4j.web.model.User;
import com.eliteams.quick4j.web.model.UserExample;
import com.eliteams.quick4j.web.model.UserInfo;
import com.eliteams.quick4j.web.security.PermissionSign;
import com.eliteams.quick4j.web.security.RoleSign;
import com.eliteams.quick4j.web.service.RoleService;
import com.eliteams.quick4j.web.service.UserInfoService;
import com.eliteams.quick4j.web.service.UserService;

/**
 * 用户控制器
 * 
 * @author zhangsh
 * @since 2014年5月28日 下午3:54:00
 **/
@Controller
@RequestMapping(value = "/user")
public class UserController extends GenericController {
	
	private static Logger log = Logger.getLogger(UserController.class); // 初始化日志对象

    @Resource
    private UserService userService;
    
    @Resource
    private UserInfoService userInfoService;
    
    @Resource
    private RoleService roleService;
    
    @Resource
    private RoleMapper roleMapper;

    /**
     * 用户登录
     * 
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid User user, BindingResult result, Model model, HttpServletRequest request) {
        try {
        	log.info("user.login");
            Subject subject = SecurityUtils.getSubject();
            // 已登陆则 跳到首页
            if (subject.isAuthenticated()) {
                return "redirect:/";
            }
            if (result.hasErrors()) {
                model.addAttribute("error", "参数错误！");
                return "login";
            }
            // 身份验证
            subject.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
            // 验证成功在Session中保存用户信息
            final User authUserInfo = userService.selectByUsername(user.getUsername());
            request.getSession().setAttribute("userInfo", authUserInfo);
            
        } catch (AuthenticationException e) {
            // 身份验证失败
            model.addAttribute("error", "用户名或密码错误 ！");
            log.error("登陆失败，账号密码错误");
            return "login";
        }
        return "redirect:/";
    }

    /**
     * 用户登出
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
    	log.info("用户登出--user.logout");
        session.removeAttribute("userInfo");
        // 登出操作
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    /**
     * 基于角色 标识的权限控制案例
     */
    @RequestMapping(value = "/admin")
    @ResponseBody
    @RequiresRoles(value = RoleSign.ADMIN)
    public String admin() {
    	Subject subject = SecurityUtils.getSubject();
        return "拥有admin角色,能访问";
    }

    /**
     * 基于权限标识的权限控制案例
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    @RequiresPermissions(value = PermissionSign.USER_CREATE)
    public String create() {
        return "拥有user:create权限,能访问";
    }
    
    /**
     * 用户列表查看
     */
    @RequestMapping(value = "/list")
    @RequiresPermissions(value = PermissionSign.USER_VIEW)
    public String list(Model model,Page<UserInfo> page,HttpServletRequest request) {
    	String keywords = request.getParameter("keywords");
//		String orderField = request.getParameter("orderField");
//		String orderDirection = request.getParameter("orderDirection");
    	try {
    		userInfoService.getUserInfoByPage(page, keywords);
    		roleService.setRolesToPage(page);
		} catch (Exception e) {
			log.error("用户列表展示错误"+e);
		}
//    	model.addAttribute("orderField",orderField);
//    	model.addAttribute("orderDirection",orderDirection);
    	model.addAttribute(page);
        return "gernal/user/list";
    }
    
    /**
     * 用户添加
     */
    @RequestMapping(value = "/add")
    @RequiresPermissions(value = PermissionSign.USER_CREATE)
    public String add(Model model) {
    	List<Role> roleList = roleMapper.selectByExample(null);
    	model.addAttribute(roleList);
        return "gernal/user/add";
    }
    /**
     * 用户编辑
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping("/edit/{userId}")
    @RequiresPermissions(value = PermissionSign.USER_UPDATE)
	public String edit(@PathVariable("userId") long userId, Model model) {
		try {
			UserInfo userInfo = userInfoService.getUserInfoByUserId(userId);
			List<Role> roleList = roleMapper.selectByExample(null);
	    	model.addAttribute(roleList);
			model.addAttribute("userInfo",userInfo);
		} catch (Exception e) {
			log.error("用户编辑出错，用户id找不到"+e);
		}
		return "gernal/user/edit";
	}
    
    @RequestMapping(value = "/roleCheckBox")
    @ResponseBody
    public List<Role> getRoleCheckBox(long userId){
    	List<Role> roleList = roleMapper.selectRolesByUserId(userId);
		return roleList;
    }
    
    /**
     * 用户新增保存
     * @param json
     * @return
     */
    @RequestMapping(value = "/insert")
    @ResponseBody
    public Json insert(@ModelAttribute("userInfoForm")UserInfoForm userInfoForm,Json json) {
    	try {
    		User user = userInfoForm.getUser();
    		UserInfo userInfo = userInfoForm.getUserInfo();
    		long[] roleIds = userInfoForm.getRoleIds();
			userInfoService.insertUserInfo(user,userInfo,roleIds);
		} catch (Exception e) {
			log.error("用户新增出错"+e);
			return json.ajaxDoneError("用户新增出错");
		}
    	
    	return json.ajaxDoneSuccess();
    }
    
    /**
     * 用户更新
     * @param user
     * @param userInfo
     * @param json
     * @return
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Json update(@ModelAttribute("userInfoForm")UserInfoForm userInfoForm, Json json) {
    	try {
    		User user = userInfoForm.getUser();
    		UserInfo userInfo = userInfoForm.getUserInfo();
    		long[] roleIds = userInfoForm.getRoleIds();
			userInfoService.updateUserInfo(user, userInfo);
			userInfoService.updateRoleByUserId(user.getId(), roleIds);
		} catch (Exception e) {
			log.error("用户更新出错"+e);
			return json.ajaxDoneError();
		}
    	
    	return json.ajaxDoneSuccess();
    }
    
    /**
     * 用户删除
     */
    @RequestMapping("/delete/{userId}")
    @ResponseBody
    @RequiresPermissions(value = PermissionSign.USER_DELETE)
	public Json delete(@PathVariable("userId") long userId ,Json json) {

    	try {
			userInfoService.deleteUserInfo(userId);
		} catch (Exception e) {
			log.error("用户删除出错"+e);
			return json.ajaxDoneError();
		}
		return json.ajaxDoneSuccess();
	}
  /**
   * 跳转到修改密码界面
   * @return
   */
    @RequestMapping(value ="/toUpdatePassword")
    public String toUpdatePassword(){
		return "gernal/user/updatePassword";
    }
    /**
     * 修改密码
     * @param passwordForm
     * @param model
     * @param json
     * @return
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public Json updatePassword (PasswordForm passwordForm ,Model model,Json json){
    	try {
    		Subject subject = SecurityUtils.getSubject();
    		Subject currentUser = SecurityUtils.getSubject();
    		String username = currentUser.getPrincipal().toString();
    		User user=new User();
    		user.setUsername(username);
    	    user.setPassword(passwordForm.getOldPassword());
    	    //进行密码验证
    	    subject.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
    	    user.setPassword(passwordForm.getNewPassword());
    	    userService.updatePassword(user);
    	   }
    	catch (AuthenticationException e) {
            // 身份验证失败
         //   model.addAttribute("error", "用户名或密码错误 ！");
            log.error("原账号密码错误");
            return json.ajaxDoneError();
        }
    	return json.ajaxDoneSuccess();
    }
    
   
}

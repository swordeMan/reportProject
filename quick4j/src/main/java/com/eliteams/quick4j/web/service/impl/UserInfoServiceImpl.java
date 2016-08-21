package com.eliteams.quick4j.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.dao.UserInfoMapper;
import com.eliteams.quick4j.web.dao.UserMapper;
import com.eliteams.quick4j.web.dao.UserRoleMapper;
import com.eliteams.quick4j.web.model.User;
import com.eliteams.quick4j.web.model.UserInfo;
import com.eliteams.quick4j.web.model.UserRole;
import com.eliteams.quick4j.web.service.UserInfoService;

/**
 * 用户Service实现类
 *
 * @author StarZou
 * @since 2014年7月5日 上午11:54:24
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

	private static Logger log = Logger.getLogger(UserInfoServiceImpl.class); // 初始化日志对象

	@Resource
	private UserInfoMapper userInfoMapper;

	@Resource
	private UserMapper userMapper;

	@Resource
	private UserRoleMapper userRoleMapper;

	@Override
	public List<UserInfo> getAllUserInfo() {
		log.info("UserInfoServiceImpl.getAllUserInfo");
		List<UserInfo> userList = new ArrayList<UserInfo>();
		userList = userInfoMapper.selectAllUserInfo();
		return userList;
	}

	@Override
	public long insertUserInfo(User user, UserInfo userInfo, long[] roleIds) {
		log.info("UserInfoServiceImpl.insertUserInfo");
		userMapper.insert(user);
		long userId = user.getId();
		userInfo.setUserId(userId);
		userInfoMapper.insert(userInfo);
		insertUserRole(userId, roleIds);
		return userId;
	}

	@Override
	public UserInfo getUserInfoByUserId(long userId) {
		log.info("UserInfoServiceImpl.getUserInfoByUserId");
		User user = userMapper.selectByPrimaryKey(userId);
		UserInfo userInfo = userInfoMapper.selectByUserId(userId);
		userInfo.setUser(user);
		return userInfo;
	}

	@Override
	public List<UserInfo> getUserInfoByPage(Page page) {
		log.info("UserInfoServiceImpl.getUserInfoByPage");
		return userInfoMapper.getUserInfoByPage(page);
	}

	@Override
	public List<UserInfo> getUserInfoByPage(Page page, String keywords) {
		log.info("UserInfoServiceImpl.getUserInfoByPage");
		return userInfoMapper.getUserInfoByPageAndKeywords(page, keywords);
	}

	@Override
	public long deleteUserInfo(long userId) {
		log.info("UserInfoServiceImpl.deleteUserInfo");
		userInfoMapper.deleteByUserId(userId);
		userMapper.deleteByPrimaryKey(userId);// user表的主键就是userId
		userRoleMapper.deleteByUserId(userId);// 删除该用户的角色
		return userId;
	}

	@Override
	public void updateUserInfo(User user, UserInfo userInfo) {
		log.info("UserInfoServiceImpl.updateUserInfo");
		userInfo.setUserId(user.getId());
		userInfoMapper.updateByUserId(userInfo);
		userMapper.updateByPrimaryKey(user);
	}

	private void insertUserRole(long userId, long[] roleIds) {
		for (long roleId : roleIds) {
			UserRole ur = new UserRole(userId, roleId);
			userRoleMapper.insert(ur);
		}
	}

	@Override
	public void updateRoleByUserId(long userId, long[] roleIds) {
		userRoleMapper.deleteByUserId(userId);
		insertUserRole(userId, roleIds);
	}

	@Override
	public String workNoToUserName(String workNo) {
		UserInfo ui = userInfoMapper.selectUserInfoByWorkNo(workNo);
		if (ui != null) {
			return ui.getUser().getUsername();
		}
		return null;
	}
}

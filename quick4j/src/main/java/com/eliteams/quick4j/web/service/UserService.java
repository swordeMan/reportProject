package com.eliteams.quick4j.web.service;

import java.util.List;

import com.eliteams.quick4j.core.generic.GenericService;
import com.eliteams.quick4j.web.model.User;
import com.eliteams.quick4j.web.model.UserInfo;

/**
 * 用户 业务 接口
 * 
 * @author StarZou
 * @since 2014年7月5日 上午11:53:33
 **/
public interface UserService extends GenericService<User, Long> {

    /**
     * 用户验证
     * 
     * @param user
     * @return
     */
    User authentication(User user);

    /**
     * 根据用户名查询用户
     * 
     * @param username
     * @return
     */
    User selectByUsername(String username);
    /**
     * 修改密码
     * @param list
     */
    void updatePassword(User user);
    
    
}

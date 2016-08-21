package com.eliteams.quick4j.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.model.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMapper {
    int insert(UserInfo record);

    UserInfo selectByUserId(Long id);

    List<UserInfo> selectAll();

    int updateByUserId(UserInfo record);
    
    List<UserInfo> selectAllUserInfo();
    
    /*
     * Page用户信息
     */
    List<UserInfo> getUserInfoByPage(Page<UserInfo> page);
    
    List<UserInfo> getUserInfoByPageAndKeywords(Page<UserInfo> page,@Param("keywords")String keywords);
    
    int deleteByUserId(Long userId);
    
    UserInfo selectUserInfoByWorkNo(@Param("workNo")String workNo);
}
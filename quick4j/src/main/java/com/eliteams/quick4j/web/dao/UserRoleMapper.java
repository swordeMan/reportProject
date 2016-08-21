package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.web.model.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    UserRole selectByPrimaryKey(Long id);

    List<UserRole> selectAll();

    int updateByPrimaryKey(UserRole record);
    
    int deleteByUserId(long userId);
}
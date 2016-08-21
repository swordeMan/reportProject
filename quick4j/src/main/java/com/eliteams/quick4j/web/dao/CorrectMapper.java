package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.model.Correct;
import com.eliteams.quick4j.web.model.ReworkReason;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrectMapper {
   // List<Correct> selectByCorrectId (String correctId );
    Correct selectByCorrectId(String correctId);
	int deleteByPrimaryKey(Long id);

    int insert(Correct record);

    Correct selectByPrimaryKey(Long id);

    List<Correct> selectAll();
    
    List<Correct> getCorrectByPage(Page<Correct> page);
    List<Correct> getCorrectByPageAndKeywords(Page<Correct> page,@Param("orderByClause")String orderByClause,@Param("keywords")String keywords);
}
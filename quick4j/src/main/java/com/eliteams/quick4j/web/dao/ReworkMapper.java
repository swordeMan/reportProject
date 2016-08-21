package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.model.Rework;
import com.eliteams.quick4j.web.model.ReworkReason;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReworkMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Rework record);

    Rework selectByPrimaryKey(Long id);

    List<Rework> selectAll();

    int updateByPrimaryKey(Rework record);
/**
 * 根据返修单ID查询返修单
 * @param reworkId
 * @return
 */
	Rework selectByReworkId(String reworkId);

    int insertRework(Rework rework);
   
    List<Rework> getReworkByPage(Page page);
    
    List<Rework> getReworkByPageAndKeywords(Page<Rework> page,@Param("orderByClause")String orderByClause,@Param("keywords")String keywords);
}
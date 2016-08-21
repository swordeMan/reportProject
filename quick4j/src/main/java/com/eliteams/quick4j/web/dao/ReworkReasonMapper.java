package com.eliteams.quick4j.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.model.ReworkReason;
import org.springframework.stereotype.Repository;

@Repository
public interface ReworkReasonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReworkReason record);
    
    List<ReworkReason> getReworkReasonByPage(Page<ReworkReason> page);
    
    List<ReworkReason> getReworkReasonByPageAndKeywords(Page<ReworkReason> page,@Param("orderByClause")String orderByClause,@Param("keywords")String keywords);

    ReworkReason selectByPrimaryKey(Long id);

    List<ReworkReason> selectAll();

    int updateByPrimaryKey(ReworkReason record);
    
    //根据名称查询返修原因
	List<ReworkReason> selectByMaterialName(String materialName);

}


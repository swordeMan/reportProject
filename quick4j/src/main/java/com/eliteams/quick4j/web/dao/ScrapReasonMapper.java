package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.model.ScrapReason;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapReasonMapper {
    int deleteByPrimaryKey(Long id);
   
    int insert(ScrapReason record);

    ScrapReason selectByPrimaryKey(Long id);

    List<ScrapReason> selectAll();

    int updateByPrimaryKey(ScrapReason record);
    
     
    List<ScrapReason> selectByMaterialName(String materialName);
    
    List<ScrapReason> getScrapReasonByPage(Page<ScrapReason> page);
    
    List<ScrapReason> getScrapReasonByPageAndKeywords(Page<ScrapReason> page,@Param("orderByClause")String orderByClause,@Param("keywords")String keywords);
}
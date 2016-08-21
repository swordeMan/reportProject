package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.model.Scrap;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Scrap record);

    Scrap selectByPrimaryKey(Long id);

    List<Scrap> selectAll();
    
    Scrap selectByscrapId(String scrapId);

    int updateByPrimaryKey(Scrap record);
    
    List<Scrap> selectScrapListByProductId(String productOrderId);
    
    List<Scrap> getUserInfoByPageAndKeywords(Page<Scrap> page,@Param("orderByClause")String orderByClause,@Param("keywords")String keywords);
    
    List<Scrap> getExceptionScrapByPageAndKeywords(Page<Scrap> page,@Param("orderByClause")String orderByClause,@Param("keywords")String keywords);

	void updateByScrap(Scrap scrap);
    
}
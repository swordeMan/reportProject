package com.eliteams.quick4j.web.dao;


import java.util.List;
import java.util.Map;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.model.Correct;
import com.eliteams.quick4j.web.model.Rework;
import com.eliteams.quick4j.web.model.Stock;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface StockMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Stock record);

    Stock selectByPrimaryKey(Long id);

    List<Stock> selectAll();

    int updateByStockMaterialId(Stock record);
    
    Stock selectByMaterialId(String materialId);
    
    int updateByMaterialId(Correct correct);

    List<Stock> getStockByPage(Page<Stock> page);
    
    List<Stock> getStockByPageAndKeywords(Page<Stock> page,@Param("keywords")String keywords);

  //采集点报工更新
    int updateByMaterialIdMap(Map map);
    //返修进行更新
    int updateByReworkId(Rework rework);

	List<Stock> selectByMaterialName(String material);

	List<Stock> selectByMaterialNameOne(String material);
}
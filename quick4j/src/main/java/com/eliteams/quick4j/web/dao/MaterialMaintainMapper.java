package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.model.MaterialMaintain;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MaterialMaintainMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MaterialMaintain record);

    MaterialMaintain selectByPrimaryKey(Long id);

    List<MaterialMaintain> selectAll();

    int updateByPrimaryKey(MaterialMaintain record);
    
    List<MaterialMaintain> getMaterialInfoByPageAndKeywords(Page<MaterialMaintain> page,@Param("orderByClause")String orderByClause,@Param("keywords")String keywords);
    
    List<MaterialMaintain> getMaterialInfoBydeviceId(Integer deviceId);
    
    String selectMaterialDescribe(MaterialMaintain record);
    
}
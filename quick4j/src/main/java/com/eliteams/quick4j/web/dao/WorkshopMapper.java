package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.model.Workshop;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Workshop record);

    Workshop selectByPrimaryKey(Integer id);

    List<Workshop> selectAll();

    int updateByPrimaryKey(Workshop record);

	List<Workshop> getWorkshopByPage(Page<Workshop> page);

	List<Workshop> getWorkshopByPageAndKeywords(Page<Workshop> page,@Param("orderByClause")String orderByClause, @Param("keywords")String keywords);
    //通过产区进行查询
	Workshop selectByWorkshop(String workshop);
}
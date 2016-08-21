package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.model.AssignmentView;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentViewMapper {
    int insert(AssignmentView record);

    List<AssignmentView> selectAll();

	List<AssignmentView> getSapOrderByPageAndKeywords(Page<AssignmentView> page,@Param("orderByClause")String orderByClause,@Param("keywords")String keywords,@Param("deviceId")Integer deviceId);
	
	//根据工序查出对应的物料描述和物料编码，以供报废时查询生产订单使用
	List<AssignmentView> getMaterialIdAndDescribeByDeviceDes(Integer deviceId);
		
	//根据工序查出对应的客户简称，以供报废时查询生产订单使用
	List<String> getUserSimpleNameByDeviceDes(Integer deviceId);
	
	//根据id查询任务视图中的单条记录
	AssignmentView selectAssignmentViewById(Long id);
	
}
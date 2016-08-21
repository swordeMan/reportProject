package com.eliteams.quick4j.web.service;

import java.util.List;

import com.eliteams.quick4j.web.model.AssignmentView;

public interface AssignmentViewService {
	
	//根据工序查出对应的物料描述和物料编码，以供报废时查询生产订单使用
	List<AssignmentView> getMaterialIdAndDescribeByDeviceDes(Integer deviceId);
	
	//根据工序查出对应的客户简称，以供报废时查询生产订单使用
	List<String> getUserSimpleNameByDeviceDes(Integer deviceId);
	
	//根据id查询任务视图中的单条记录
	AssignmentView selectAssignmentViewById(Long id);
}

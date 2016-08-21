package com.eliteams.quick4j.web.service;

import java.util.List;

import com.eliteams.quick4j.web.form.CreateAssignmentForm;
import com.eliteams.quick4j.web.model.Assignment;
import com.eliteams.quick4j.web.model.ReportYield;

public interface AssignmentService {
	
	//向任务表中添加纪录（单条生产订单）
	void insertIntoAssignment(CreateAssignmentForm createAssignmentForm);
	
	//根据生产订单号在assignment中查询记录
	Assignment getAssignmentInfoByProductOrderId(String productOrderId);
	
	//查询所有销售订单号
	List<String> getAllSaleOrderId();
	
	//根据销售订单号查询对应的物料描述列表
	List<String> getAllMaterialDesBySaleOrderId(String saleOrderId);
	
	//根据销售订单号查询所有的启动任务
	List<Assignment> getAssignmentListBySaleOrderId(String saleOrderId);
	
	//向任务表中添加纪录（批量生产订单）
	void insertAssignment(CreateAssignmentForm createAssignmentForm);
	
	//查询启动任务中物料描述
	List<String> selectAllMaterialDescribe();
	
	//查询启动任务中客户简称
	List<String> selectAllUserSimpleName();
	
	/**
	 * 根据用户输入的三个查询条件查询对应的报工（报废日期、物料描述和客户简称）
	 * @param reportYield
	 * @return
	 */
	List<Assignment> obtainAssignmentByCriteriaQuery(Assignment assignment);
	
	//循环多条件查询的已启动任务任务列表，根据生产订单号去sapOrder中查询对应的生产订单，
	//获得报废量，完成量数据，组装成新的list
	List<Assignment> obtainAssignmentList(Assignment assignment);
}

package com.eliteams.quick4j.web.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.eliteams.quick4j.web.dao.AssignmentMapper;
import com.eliteams.quick4j.web.form.CreateAssignmentForm;
import com.eliteams.quick4j.web.model.Assignment;
import com.eliteams.quick4j.web.model.SapOrder;
import com.eliteams.quick4j.web.service.AssignmentService;
import com.eliteams.quick4j.web.service.SapOrderService;
/**
 * 任务下达类
 * @author qianjun
 *
 */
@Service
public class AssignmentServiceImpl implements AssignmentService {
	@Resource
	private AssignmentMapper assignmentMapper;
	
	@Resource
	private SapOrderService sapOrderService;
	
	//任务安排表中插入多条记录（单条生产订单）
	@Override
	public void insertIntoAssignment(CreateAssignmentForm createAssignmentForm) {
		
		Assignment assignment = createAssignmentForm.getAssignment();
		Assignment[] assignments = createAssignmentForm.getAssignments();
		
		for(Assignment ast:assignments){
			ast.setProductOrderId(assignment.getProductOrderId());
			ast.setMaterialId(assignment.getMaterialId());
			ast.setMaterialDescribe(assignment.getMaterialDescribe());
			ast.setCmdTime(assignment.getCmdTime());
			ast.setCmdPerson(assignment.getCmdPerson());
			ast.setSaleOrderId(assignment.getSaleOrderId());
			ast.setUserSimpleName(assignment.getUserSimpleName());
			
			assignmentMapper.insert(ast);
		}
		
	}
	
	//任务安排表中插入多条记录（批量生产订单）
		@Override
		public void insertAssignment(CreateAssignmentForm createAssignmentForm) {
			
			Assignment assignment = createAssignmentForm.getAssignment();
			Assignment[] assignments = createAssignmentForm.getAssignments();
			
			for(Assignment ast:assignments){
				ast.setDeviceId(assignment.getDeviceId());
				ast.setMaterialId(assignment.getMaterialId());
				ast.setCmdTime(assignment.getCmdTime());
				ast.setCmdPerson(assignment.getCmdPerson());
				
				assignmentMapper.insert(ast);
				//更新生产订单的下达状态为1
				String productOrderId = ast.getProductOrderId();
	    		sapOrderService.updateSapOrderStateMarkToAllocate(productOrderId);
			}
			
		}
	
	//根据生产订单号在assignment中查询记录
	@Override
	public Assignment getAssignmentInfoByProductOrderId(String productOrderId) {
		return assignmentMapper.selectByProductOrderId(productOrderId);
	}
	
	//查询所有任务
	@Override
	public List<String> getAllSaleOrderId() {
		return assignmentMapper.selectAllSaleOrderId();
	}
	//根据销售订单号查询对应的物料描述列表
	@Override
	public List<String> getAllMaterialDesBySaleOrderId(String saleOrderId) {
		return assignmentMapper.getAllMaterialDesBySaleOrderId(saleOrderId);
	}
	
	//根据销售订单号查询所有的启动任务
	@Override
	public List<Assignment> getAssignmentListBySaleOrderId(String saleOrderId) {
		return assignmentMapper.getAssignmentListBySaleOrderId(saleOrderId);
	}
	
	//查询启动任务中物料描述
	@Override
	public List<String> selectAllMaterialDescribe() {
		return assignmentMapper.selectAllMaterialDescribe();
	}
	
	//查询启动任务中客户简称
	@Override
	public List<String> selectAllUserSimpleName() {
		return assignmentMapper.selectAllUserSimpleName();
	}
	
	//根据用户输入的三个查询条件查询对应的已启动任务（报废日期、物料描述和客户简称）
	@Override
	public List<Assignment> obtainAssignmentByCriteriaQuery(Assignment assignment) {
		/*Date createTime = assignment.getStartTime();
		Calendar cal=Calendar.getInstance(); 
		cal.setTime(createTime);
		cal.add(Calendar.DAY_OF_MONTH, -2);
		createTime=cal.getTime();
		assignment.setStartTime(createTime);*/
		return assignmentMapper.obtainAssignmentByCriteriaQuery(assignment);
	}
	
	//循环多条件查询的已启动任务任务列表，根据生产订单号去sapOrder中查询对应的生产订单，
	//获得报废量，完成量数据，组装成新的list
	@Override
	public List<Assignment> obtainAssignmentList(Assignment assignment){
		List<Assignment> assignmentList = obtainAssignmentByCriteriaQuery(assignment);
		List<Assignment> newAssignmentList = new ArrayList<Assignment>();
		for(Assignment ass:assignmentList){
			String productOrderId = ass.getProductOrderId();
			SapOrder sapOrder = sapOrderService.getSapOrderInfoById(productOrderId);
			ass.setFinishedTotal(sapOrder.getFinishedTotal());
			ass.setWasteTotal(sapOrder.getWasteTotal());
			newAssignmentList.add(ass);
		}
		return newAssignmentList;
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return assignmentMapper.deleteByPrimaryKey(id);
	}
}

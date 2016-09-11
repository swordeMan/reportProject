package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.model.Assignment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Assignment record);

    Assignment selectByPrimaryKey(Long id);

    List<Assignment> selectAll();

    int updateByPrimaryKey(Long id);
    
    //优先执行，设置启动时间提前一年
    int updateStartTimeByPrimaryKey(Long id);
    
    //根据生产订单号在assignment中查询记录
	Assignment selectByProductOrderId(String productOrderId);
	
    //根据工序查找物料编码
	Assignment selectByDeviceIdAndTime(Assignment record);
	
	//查询所有的销售订单号
	List<String> selectAllSaleOrderId();
	
	//根据销售订单号查询对应的物料描述列表
	List<String> getAllMaterialDesBySaleOrderId(String saleOrderId);
	
	//根据销售订单号查询所有的启动任务
	List<Assignment> getAssignmentListBySaleOrderId(String saleOrderId);
	
	List<Assignment> getSapOrderByPageAndKeywords(Page<Assignment> page,@Param("orderByClause")String orderByClause,@Param("keywords")String keywords);
	
	//查询启动任务中物料描述
	List<String> selectAllMaterialDescribe();
		
	//查询启动任务中客户简称
	List<String> selectAllUserSimpleName();
	
	//根据用户输入的三个查询条件查询对应的报工（报废日期、物料描述和客户简称）
    List<Assignment> obtainAssignmentByCriteriaQuery(Assignment assignment);

	void firstReport(long id);
}
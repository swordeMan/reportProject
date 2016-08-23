package com.eliteams.quick4j.web.service;

import java.util.Date;
import java.util.List;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.model.ReportTime;
import com.eliteams.quick4j.web.model.SapOrder;

/**
 * sap 订单交互接口
 * @author zhang
 *
 */
public interface SapOrderService{
	/**
	 * sap获取生产订单
	 * @param factory 
	 * @param alterDate
	 * @param alterTime
	 */
	List<SapOrder> getProductOrderInfo(String factory, Date alterDate, Date alterTime) throws Exception;
	
	/**
	 * 
	 * @param page
	 * @return
	 */
	List<SapOrder> selectAllSapOrder(Page page);//查询所有生产订单(分页查看)
	
	List<SapOrder> getUserInfoByPage(Page page,String orderByClause,String keywords);
	
	/**
	 * 根据生产订单号查单条生产订单所有信息
	 * @param productOrderId
	 * @return
	 */
	SapOrder getSapOrderInfoById(String productOrderId);
	
	/**
	 * 根据销售订单获取对应的生成订单列表
	 * @param saleOrderId
	 * @return
	 */
    List<SapOrder> getSapOrderListBySaleOrderId(String saleOrderId);
    
    /**
	 * 根据销售订单与物料简称获取对应的生成订单
	 * @param saleOrderId
	 * @param simpleDescribe 物料描述简称
	 * @return
	 */
    String getBySaleOrderIdAndSimpleDescribe(String saleOrderId,String saleOrderRow,String simpleDescribe);
    
    /**
     * 查询所有未启动的生产订单信息
     * @return
     */
    List<SapOrder> selectSapOrderList();
    
    /**
     * 更新生产订单的启动状态为已启动
     * @param productOrderId
     */
    void updateSapOrderStateMark(String productOrderId);
    
    /**
     * 任务删除后，更新生产订单的启动状态为未启动
     * @param productOrderId
     */
    void updateSapOrderStateMarkToUnAllocate(String productOrderId);
    
    /**
     * 更新生产订单的下达状态
     * @param productOrderId
     */
    void updateSapOrderStateMarkToAllocate(String productOrderId);
    
    //查询所有未启动的生产订单中的物料编码
    List<String> getMaterialIdFromUnstart();
    
}

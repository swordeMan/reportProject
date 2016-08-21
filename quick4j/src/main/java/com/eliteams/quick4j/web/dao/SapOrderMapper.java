package com.eliteams.quick4j.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.model.OrderNode;
import com.eliteams.quick4j.web.model.SapOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface SapOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SapOrder record);

    SapOrder selectByPrimaryKey(Long id);

    List<SapOrder> selectAll();

    int updateByPrimaryKey(SapOrder record);
    
    //分页查询所有生产订单信息
    List<SapOrder> getSapOrderByPage(Page page);
    
    List<SapOrder> getSapOrderByPageAndKeywords(Page<SapOrder> page,@Param("orderByClause")String orderByClause,@Param("keywords")String keywords);
    
    //根据生产订单号查单条生产订单所有信息
    SapOrder selectByProductOrderId(String productOrderId);
    
    //根据销售订单获取对应的生成订单列表(不现实del标记为X的记录)
    List<SapOrder> getSapOrderListBySaleOrderId(String saleOrderId);
    
    //根据销售订单号、销售订单行查询对应的生产订单列表
    List<SapOrder> getSapOrderListBySaleOrderIdAndSaleOrderRow(@Param("saleOrderId")String saleOrderId,@Param("saleOrderRow")String saleOrderRow);
    
    //根据销售订单和物料简称获得生产订单
    SapOrder getBySaleOrderIdAndSimpleDescribe(@Param("saleOrderId")String saleOrderId,@Param("saleOrderRow") String saleOrderRow,@Param("simpleDescribe")String simpleDescribe);
    
    //根据销售订单获取对应的生成订单列表(显示全部记录)
	List<OrderNode> getSapOrderBySaleOrderId(String saleOrderId);
	
	//查询所有未启动的生产订单信息
	List<SapOrder> selectSapOrderList();
	
	//更新生产订单的启动状态
	void updateSapOrderStateMark(String productOrderId);
	
	//更新生产订单的下达状态
	void updateSapOrderStateMarkToAllocate(String productOrderId);
	
	//查询所有未启动的生产订单中的物料编码
	List<String> getMaterialIdFromUnstart();
}
package com.eliteams.quick4j.core.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.eliteams.quick4j.web.model.SapOrder;
import com.eliteams.quick4j.web.service.SapOrderService;

@Component
public class OrderUtil {
	
	/*
	 * 轮辐
	 */
	public static final String SPORK = "轮辐";
	
	public static final String RIM = "轮辋";
	
	public static final String SYNTHESIS = "合成";
	
	public static final String WHEEL = "钢圈";
	
	public static final String SPIN = "旋压";
	
	private static SapOrderService sapOrderService;
	
	public static List<String> getChildProductOrderIds(String productOrderId){
		
		List<String> childProductIds = new ArrayList<>(2);
		SapOrder thisOrder = sapOrderService.getSapOrderInfoById(productOrderId);
		Assert.notNull(thisOrder, "没有该条生产订单");
		String simpleDescribe = simplifyMaterialDescribe(thisOrder.getMaterialDescribe());
		if(simpleDescribe==null){
			return null;
		}
		String saleId = thisOrder.getSaleOrderId();
		String saleOrderRow = thisOrder.getSaleOrderRow();
		//如果是轮辐、轮辋、旋压中的任意一个返回null
		if(simpleDescribe.equals(WHEEL)){
			String productId = sapOrderService.getBySaleOrderIdAndSimpleDescribe(saleId, saleOrderRow,SYNTHESIS);
			childProductIds.add(productId);
			return childProductIds;
		}else if(simpleDescribe.equals(SYNTHESIS)){
			String sporkProductId = sapOrderService.getBySaleOrderIdAndSimpleDescribe(saleId, saleOrderRow,SPORK);
			String rimProductId = sapOrderService.getBySaleOrderIdAndSimpleDescribe(saleId,saleOrderRow, RIM);
			childProductIds.add(sporkProductId);
			childProductIds.add(rimProductId);
			return childProductIds;
		}else{
			return null;
		}
	}
	
	@Autowired
	public void setSapOrderService(SapOrderService sapOrderService) {
		OrderUtil.sapOrderService = sapOrderService;
	}

	public static String simplifyMaterialDescribe(String materialDescribe){
		return materialDescribe==null?null:materialDescribe.substring(0, 2);
	}
}

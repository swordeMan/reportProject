package com.eliteams.quick4j.web.enums;

public enum FieldNameEnum {
	
	productOrderId("生产订单号"),
	saleOrderId("销售订单"),
	saleOrderRow("销售订单行"),
	userSimpleName("客户简称"),
	manufactureVersion("生产版本"),
	materialId("物料ID"),
	materialDescribe("物料描述"),
	targetSum("目标数量"),
	finishedTotal("完成数"),
	wasteTotal("确认废品数"),
	relateScarp("关联报废数"),
	planStartDate("计划开始日期"),
	planEndDate("计划完成日期"),
	createTime("新建时间"),
	messageId("消息"),
	currentYield("报工量"),
	currentWaste("报废量"),
	reportUsername("报工人"),
	accountDate("记账日期"),
	message("SAP接口状态"),
	scrapId("报废单编号"),
	scrapTime("报废日期"),
	classes("班次"),
	scrapNum("报废数量"),
	productionProcess("工序"),
	inspector("填单人"),
	auditor("审核人"),
	auditingTime("审核时间"),
	auditingContents("审核内容");
	
	
	String title;
	
	FieldNameEnum(String title){
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
	
	
	
	
	
}

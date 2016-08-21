package com.eliteams.quick4j.web.model;

/**
 * 报废追溯中，构造树结构，定义一个包含生产订单、物料编码和目标数量的类
 * @author qianjun
 *
 */
public class OrderNode {
	
	private String productOrderId;
	private String materialId;
	private Integer targetSum;
	
	public String getProductOrderId() {
		return productOrderId;
	}
	public void setProductOrderId(String productOrderId) {
		this.productOrderId = productOrderId;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public Integer getTargetSum() {
		return targetSum;
	}
	public void setTargetSum(Integer targetSum) {
		this.targetSum = targetSum;
	}
}

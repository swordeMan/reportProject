package com.eliteams.quick4j.web.model;

import java.util.Date;

public class StockAssignmentView {
    private String materialId;

    private Long stockNum;

    private Long totalProduction;

    private String materialDescribe;

    private String productOrderId;

    private Date startTime;

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId == null ? null : materialId.trim();
    }

    public Long getStockNum() {
        return stockNum;
    }

    public void setStockNum(Long stockNum) {
        this.stockNum = stockNum;
    }

    public Long getTotalProduction() {
        return totalProduction;
    }

    public void setTotalProduction(Long totalProduction) {
        this.totalProduction = totalProduction;
    }

    public String getMaterialDescribe() {
        return materialDescribe;
    }

    public void setMaterialDescribe(String materialDescribe) {
        this.materialDescribe = materialDescribe == null ? null : materialDescribe.trim();
    }

    public String getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(String productOrderId) {
        this.productOrderId = productOrderId == null ? null : productOrderId.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public String toString(){
    	return "订单"+productOrderId+"物料id"+materialId+"物料描述"+materialDescribe+"原有待分配量"+stockNum;
    }
}
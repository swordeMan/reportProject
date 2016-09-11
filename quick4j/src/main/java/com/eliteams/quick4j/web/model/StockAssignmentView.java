package com.eliteams.quick4j.web.model;

import java.util.Date;

public class StockAssignmentView {
    private String materialId;

    private Long stockNum;

    private Long totalProduction;

    private String materialDescribe;

    private String productOrderId;

    private Date startTime;

    private Date firstReportTime;

    private Integer targetSum;

    private Integer finishedTotal;

    private Integer wasteTotal;

    private Integer relateScarp;

    private Long assignmentId;

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

    public Date getFirstReportTime() {
        return firstReportTime;
    }

    public void setFirstReportTime(Date firstReportTime) {
        this.firstReportTime = firstReportTime;
    }

    public Integer getTargetSum() {
        return targetSum;
    }

    public void setTargetSum(Integer targetSum) {
        this.targetSum = targetSum;
    }

    public Integer getFinishedTotal() {
        return finishedTotal;
    }

    public void setFinishedTotal(Integer finishedTotal) {
        this.finishedTotal = finishedTotal;
    }

    public Integer getWasteTotal() {
        return wasteTotal;
    }

    public void setWasteTotal(Integer wasteTotal) {
        this.wasteTotal = wasteTotal;
    }

    public Integer getRelateScarp() {
        return relateScarp;
    }

    public void setRelateScarp(Integer relateScarp) {
        this.relateScarp = relateScarp;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }
}
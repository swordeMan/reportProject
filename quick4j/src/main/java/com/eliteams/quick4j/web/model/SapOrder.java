package com.eliteams.quick4j.web.model;

import java.math.BigDecimal;
import java.util.Date;

public class SapOrder {
    private Long id;

    private String productOrderId;

    private String productOrderType;

    private String saleOrderId;

    private String saleOrderRow;

    private String userSimpleName;

    private String manufactureVersion;

    private String materialId;

    private String materialDescribe;

    private Integer targetSum;

    private Integer finishedTotal;

    private Integer wasteTotal;
    
    private Integer relateScarp;
    
    private Integer stateMark;

    private String unit;

    private Date planStartDate;

    private Date planEndDate;

    private String delRemark;

    private String state;

    private Date createTime;
    
    private double rate;

    public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	
	public void setRate(Integer finishedTotal,Integer targetSum) {
		if(targetSum==0){
			throw new ArithmeticException("目标数为0");
		}
		BigDecimal bg = new BigDecimal((double)finishedTotal/targetSum);
		this.rate = 100*bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(String productOrderId) {
        this.productOrderId = productOrderId == null ? null : productOrderId.trim();
    }

    public String getProductOrderType() {
        return productOrderType;
    }

    public void setProductOrderType(String productOrderType) {
        this.productOrderType = productOrderType == null ? null : productOrderType.trim();
    }

    public String getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(String saleOrderId) {
        this.saleOrderId = saleOrderId == null ? null : saleOrderId.trim();
    }

    public String getSaleOrderRow() {
        return saleOrderRow;
    }

    public void setSaleOrderRow(String saleOrderRow) {
        this.saleOrderRow = saleOrderRow == null ? null : saleOrderRow.trim();
    }

    public String getUserSimpleName() {
        return userSimpleName;
    }

    public void setUserSimpleName(String userSimpleName) {
        this.userSimpleName = userSimpleName == null ? null : userSimpleName.trim();
    }

    public String getManufactureVersion() {
        return manufactureVersion;
    }

    public void setManufactureVersion(String manufactureVersion) {
        this.manufactureVersion = manufactureVersion == null ? null : manufactureVersion.trim();
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId == null ? null : materialId.trim();
    }

    public String getMaterialDescribe() {
        return materialDescribe;
    }

    public void setMaterialDescribe(String materialDescribe) {
        this.materialDescribe = materialDescribe == null ? null : materialDescribe.trim();
    }

    public Integer getTargetSum() {
        return targetSum;
    }

    public void setTargetSum(Integer targetSum) {
        this.targetSum = targetSum;
    }

    public Integer getFinishedTotal() {
        return finishedTotal==null?0:finishedTotal;
    }

    public void setFinishedTotal(Integer finishedTotal) {
        this.finishedTotal = finishedTotal;
    }

    public Integer getWasteTotal() {
        return wasteTotal==null?0:wasteTotal;
    }

    public void setWasteTotal(Integer wasteTotal) {
        this.wasteTotal = wasteTotal;
    }

    public Integer getRelateScarp() {
		return relateScarp==null?0:relateScarp;
	}

	public void setRelateScarp(Integer relateScarp) {
		this.relateScarp = relateScarp;
	}

	public Integer getStateMark() {
		return stateMark;
	}

	public void setStateMark(Integer stateMark) {
		this.stateMark = stateMark;
	}

	public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Date getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(Date planStartDate) {
        this.planStartDate = planStartDate;
    }

    public Date getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(Date planEndDate) {
        this.planEndDate = planEndDate;
    }

    public String getDelRemark() {
        return delRemark;
    }

    public void setDelRemark(String delRemark) {
        this.delRemark = delRemark == null ? null : delRemark.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
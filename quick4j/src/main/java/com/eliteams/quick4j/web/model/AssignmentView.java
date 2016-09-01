package com.eliteams.quick4j.web.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AssignmentView {
    private Long id;

    private String productOrderId;

    private String materialId;

    private String materialDescribe;

    private Integer assignmentNum;

    private Date planTime;

    private Integer classes;

    private Date cmdTime;

    private String cmdPerson;

    private Date startTime;

    private String saleOrderId;

    private String userSimpleName;

    private String deiviceDescribe;

    private Integer deviceId;
    
    private String saleOrderRow;
    
    private Integer targetSum;

    private Integer finishedTotal;
    
    //增加起始时间和终止时间用于高级搜索
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date beginTime;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date stopTime;
    
    //增加关键字、搜索字段、搜索方向等字段
    private String orderField;
    private String orderDirection;
    private String keywords;
    private String orderByClause;

    public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
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

    public Integer getAssignmentNum() {
        return assignmentNum;
    }

    public void setAssignmentNum(Integer assignmentNum) {
        this.assignmentNum = assignmentNum;
    }

    public Date getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Date planTime) {
        this.planTime = planTime;
    }

    public Integer getClasses() {
        return classes;
    }

    public void setClasses(Integer classes) {
        this.classes = classes;
    }

    public Date getCmdTime() {
        return cmdTime;
    }

    public void setCmdTime(Date cmdTime) {
        this.cmdTime = cmdTime;
    }

    public String getCmdPerson() {
        return cmdPerson;
    }

    public void setCmdPerson(String cmdPerson) {
        this.cmdPerson = cmdPerson == null ? null : cmdPerson.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(String saleOrderId) {
        this.saleOrderId = saleOrderId == null ? null : saleOrderId.trim();
    }

    public String getUserSimpleName() {
        return userSimpleName;
    }

    public void setUserSimpleName(String userSimpleName) {
        this.userSimpleName = userSimpleName == null ? null : userSimpleName.trim();
    }

    public String getDeiviceDescribe() {
        return deiviceDescribe;
    }

    public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public void setDeiviceDescribe(String deiviceDescribe) {
        this.deiviceDescribe = deiviceDescribe == null ? null : deiviceDescribe.trim();
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

	public String getSaleOrderRow() {
		return saleOrderRow;
	}

	public void setSaleOrderRow(String saleOrderRow) {
		this.saleOrderRow = saleOrderRow;
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
}
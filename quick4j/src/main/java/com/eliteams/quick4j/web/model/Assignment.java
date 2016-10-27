package com.eliteams.quick4j.web.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Assignment {
    private Long id;

    private String productOrderId;

    private String materialId;

    private String materialDescribe;

    private Integer deviceId;

    private Integer assignmentNum;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date planTime;

    private Integer classes;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date cmdTime;

    private String cmdPerson;
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    
    //增加了销售订单号
    private String saleOrderId;
    
    //增加个客户
    private String userSimpleName;
    
    //增加完成数，数据库表中没增加
    private Integer finishedTotal;
    
    //增加报废数，数据库表中没增加
    private Integer wasteTotal;
    
    //增加首次报工时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date firstReportTime;
    
    //增加任务状态：暂停和重启
    private Integer state;
    
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

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
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
		this.saleOrderId = saleOrderId;
	}

	public String getUserSimpleName() {
		return userSimpleName;
	}

	public void setUserSimpleName(String userSimpleName) {
		this.userSimpleName = userSimpleName;
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

	public Date getFirstReportTime() {
		return firstReportTime;
	}

	public void setFirstReportTime(Date firstReportTime) {
		this.firstReportTime = firstReportTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
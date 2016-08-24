package com.eliteams.quick4j.web.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ReworkView {
    private Long id;

    private String reworkId;

    private String productionProcedure;
    
    @DateTimeFormat(pattern="yyyy-MM")
    private Date reworkTime;

    private Long reworkTotal;

    private String reason;

    private Integer reworkNum;
    
    private String state;
    
    //增加变量time,为了通配返修日期
    private String time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReworkId() {
        return reworkId;
    }

    public void setReworkId(String reworkId) {
        this.reworkId = reworkId == null ? null : reworkId.trim();
    }

    public String getProductionProcedure() {
        return productionProcedure;
    }

    public void setProductionProcedure(String productionProcedure) {
        this.productionProcedure = productionProcedure == null ? null : productionProcedure.trim();
    }

    public Date getReworkTime() {
        return reworkTime;
    }

    public void setReworkTime(Date reworkTime) {
        this.reworkTime = reworkTime;
    }

    public Long getReworkTotal() {
        return reworkTotal;
    }

    public void setReworkTotal(Long reworkTotal) {
        this.reworkTotal = reworkTotal;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getReworkNum() {
        return reworkNum;
    }

    public void setReworkNum(Integer reworkNum) {
        this.reworkNum = reworkNum;
    }

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
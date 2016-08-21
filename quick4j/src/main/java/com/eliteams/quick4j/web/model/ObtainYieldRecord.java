package com.eliteams.quick4j.web.model;

import java.util.Date;

public class ObtainYieldRecord {
    private Long id;

    private String dSoId;

    private Integer eqptId;

    private Integer shiftId;

    private Date outputDate;

    private Long reportNum;

    private Date lastUpdateTime;

    private Date insertTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getdSoId() {
        return dSoId;
    }

    public void setdSoId(String dSoId) {
        this.dSoId = dSoId == null ? null : dSoId.trim();
    }

    public Integer getEqptId() {
        return eqptId;
    }

    public void setEqptId(Integer eqptId) {
        this.eqptId = eqptId;
    }

    public Integer getShiftId() {
        return shiftId;
    }

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public Date getOutputDate() {
        return outputDate;
    }

    public void setOutputDate(Date outputDate) {
        this.outputDate = outputDate;
    }

    public Long getReportNum() {
        return reportNum;
    }

    public void setReportNum(Long reportNum) {
        this.reportNum = reportNum;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
}
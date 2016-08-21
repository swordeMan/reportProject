package com.eliteams.quick4j.web.model;

import java.math.BigDecimal;
import java.util.Date;

public class DShiftOutput {
    private String dSoId;

    private Integer eqptId;

    private Integer shiftId;

    private Date outputDate;

    private String woCode;

    private BigDecimal daNum;

    private Long reportNum;

    private BigDecimal preDaNum;

    private BigDecimal preReportNum;

    private Date lastUpdateTime;

    private BigDecimal beatReal;

    private BigDecimal beatTotal;

    private Integer beatCount;

	public String getdSoId() {
		return dSoId;
	}

	public void setdSoId(String dSoId) {
		this.dSoId = dSoId;
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

	public String getWoCode() {
		return woCode;
	}

	public void setWoCode(String woCode) {
		this.woCode = woCode;
	}

	public BigDecimal getDaNum() {
		return daNum;
	}

	public void setDaNum(BigDecimal daNum) {
		this.daNum = daNum;
	}

	public Long getReportNum() {
		return reportNum;
	}

	public void setReportNum(Long reportNum) {
		this.reportNum = reportNum;
	}

	public BigDecimal getPreDaNum() {
		return preDaNum;
	}

	public void setPreDaNum(BigDecimal preDaNum) {
		this.preDaNum = preDaNum;
	}

	public BigDecimal getPreReportNum() {
		return preReportNum;
	}

	public void setPreReportNum(BigDecimal preReportNum) {
		this.preReportNum = preReportNum;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public BigDecimal getBeatReal() {
		return beatReal;
	}

	public void setBeatReal(BigDecimal beatReal) {
		this.beatReal = beatReal;
	}

	public BigDecimal getBeatTotal() {
		return beatTotal;
	}

	public void setBeatTotal(BigDecimal beatTotal) {
		this.beatTotal = beatTotal;
	}

	public Integer getBeatCount() {
		return beatCount;
	}

	public void setBeatCount(Integer beatCount) {
		this.beatCount = beatCount;
	}
    
    

}
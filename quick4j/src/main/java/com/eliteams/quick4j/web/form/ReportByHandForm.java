package com.eliteams.quick4j.web.form;

import com.eliteams.quick4j.web.model.ReportYield;

public class ReportByHandForm {
	
	private String userSimpleName;
	
	private String saleOrderId;
	
    private ReportYield[] reportYield;
    

	public String getUserSimpleName() {
		return userSimpleName;
	}

	public void setUserSimpleName(String userSimpleName) {
		this.userSimpleName = userSimpleName;
	}

	public String getSaleOrderId() {
		return saleOrderId;
	}

	public void setSaleOrderId(String saleOrderId) {
		this.saleOrderId = saleOrderId;
	}

	public ReportYield[] getReportYield() {
		return reportYield;
	}

	public void setReportYield(ReportYield[] reportYield) {
		this.reportYield = reportYield;
	}

}

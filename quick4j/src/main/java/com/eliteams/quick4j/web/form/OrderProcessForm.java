package com.eliteams.quick4j.web.form;

import com.eliteams.quick4j.web.model.ReportYield;
import com.eliteams.quick4j.web.model.SapOrder;
import com.eliteams.quick4j.web.model.Scrap;

public class OrderProcessForm {
	
	private SapOrder sapOrder;
	
	private ReportYield[] reportUpYields;
	
	private Scrap[] scraps;
	
	private ReportYield[] reportDownYields;

	public SapOrder getSapOrder() {
		return sapOrder;
	}

	public void setSapOrder(SapOrder sapOrder) {
		this.sapOrder = sapOrder;
	}

	public ReportYield[] getReportUpYields() {
		return reportUpYields;
	}

	public void setReportUpYields(ReportYield[] reportUpYields) {
		this.reportUpYields = reportUpYields;
	}

	public Scrap[] getScraps() {
		return scraps;
	}

	public void setScraps(Scrap[] scraps) {
		this.scraps = scraps;
	}

	public ReportYield[] getReportDownYields() {
		return reportDownYields;
	}

	public void setReportDownYields(ReportYield[] reportDownYields) {
		this.reportDownYields = reportDownYields;
	}
}

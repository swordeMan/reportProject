package com.eliteams.quick4j.web.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ScrapView {
    private String sReason;

    private String illustration;

    private Integer scrapNum;

    private Long id;

    private String productOrderId;

    private String scrapId;

    private String materialId;

    private String materialDescribe;
    
    @DateTimeFormat(pattern="yyyy-MM")
    private Date scrapTime;

    private String classes;

    private String productionProcess;

    private String inspector;

    private Date createTime;

    private String auditor;

    private Date auditingTime;

    private String auditingContents;

    private Integer scrapAuditing;

    private String file;

    private String state;
    
    private String saleOrderId;
    
    private Integer wasteTotal;
    
    //增加个客户
    private String userSimpleName;
    
    //增加报废分类
    private String classification;
    
    //增加变量time,为了通配报废日期
    private String time;
    
    //增加变量年份
    @DateTimeFormat(pattern="yyyy")
    private Date year;
    
    //增加销售订单行
    private String saleOrderRow;
    
    public String getsReason() {
        return sReason;
    }

    public void setsReason(String sReason) {
        this.sReason = sReason == null ? null : sReason.trim();
    }

    public String getIllustration() {
        return illustration;
    }

    public void setIllustration(String illustration) {
        this.illustration = illustration == null ? null : illustration.trim();
    }

    public Integer getScrapNum() {
        return scrapNum;
    }

    public void setScrapNum(Integer scrapNum) {
        this.scrapNum = scrapNum;
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

    public String getScrapId() {
        return scrapId;
    }

    public void setScrapId(String scrapId) {
        this.scrapId = scrapId == null ? null : scrapId.trim();
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

    public Date getScrapTime() {
        return scrapTime;
    }

    public void setScrapTime(Date scrapTime) {
        this.scrapTime = scrapTime;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes == null ? null : classes.trim();
    }

    public String getProductionProcess() {
        return productionProcess;
    }

    public void setProductionProcess(String productionProcess) {
        this.productionProcess = productionProcess == null ? null : productionProcess.trim();
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector == null ? null : inspector.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor == null ? null : auditor.trim();
    }

    public Date getAuditingTime() {
        return auditingTime;
    }

    public void setAuditingTime(Date auditingTime) {
        this.auditingTime = auditingTime;
    }

    public String getAuditingContents() {
        return auditingContents;
    }

    public void setAuditingContents(String auditingContents) {
        this.auditingContents = auditingContents == null ? null : auditingContents.trim();
    }

    public Integer getScrapAuditing() {
        return scrapAuditing;
    }

    public void setScrapAuditing(Integer scrapAuditing) {
        this.scrapAuditing = scrapAuditing;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file == null ? null : file.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

	public String getSaleOrderId() {
		return saleOrderId;
	}

	public void setSaleOrderId(String saleOrderId) {
		this.saleOrderId = saleOrderId;
	}

	public Integer getWasteTotal() {
		return wasteTotal;
	}

	public void setWasteTotal(Integer wasteTotal) {
		this.wasteTotal = wasteTotal;
	}

	public String getUserSimpleName() {
		return userSimpleName;
	}

	public void setUserSimpleName(String userSimpleName) {
		this.userSimpleName = userSimpleName;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public String getSaleOrderRow() {
		return saleOrderRow;
	}

	public void setSaleOrderRow(String saleOrderRow) {
		this.saleOrderRow = saleOrderRow;
	}
}
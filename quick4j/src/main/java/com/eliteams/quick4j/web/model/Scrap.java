package com.eliteams.quick4j.web.model;

import java.util.Date;

public class Scrap {
    private Long id;

    private String productOrderId;

    private String scrapId;

    private String materialId;

    private String materialDescribe;

    private Date scrapTime;

    private String classes;

    private Integer scrapNum;

    private String productionProcess;

    private String inspector;

    private Date createTime;

    private String auditor;

    private Date auditingTime;

    private String auditingContents;

    private Integer scrapAuditing;

    private String file;

    private String state;
    
    private Integer relateScrap;
    
	//增加个销售订单号
    private String saleOrderId;
    
    //增加个客户
    private String userSimpleName;
    
    //增加报废分类
    private String classification;
    
    //增加消息类型
    private String messageType;
    
    //增加消息文本
    private String message;
    
    //增加销售订单行
    private String saleOrderRow;

    public String getSaleOrderId() {
		return saleOrderId;
	}

	public void setSaleOrderId(String saleOrderId) {
		this.saleOrderId = saleOrderId;
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

    public Integer getScrapNum() {
        return scrapNum;
    }

    public void setScrapNum(Integer scrapNum) {
        this.scrapNum = scrapNum;
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
    public Integer getRelateScrap() {
		return relateScrap;
	}

	public void setRelateScrap(Integer relateScrap) {
		this.relateScrap = relateScrap;
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

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSaleOrderRow() {
		return saleOrderRow;
	}

	public void setSaleOrderRow(String saleOrderRow) {
		this.saleOrderRow = saleOrderRow;
	}
}
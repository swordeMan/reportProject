package com.eliteams.quick4j.web.model;

import java.util.Date;

import javax.annotation.Resource;

import com.eliteams.quick4j.web.service.ReportYieldService;

public class ReportYield {
	
	/*@Resource
	private ReportYieldService reportYieldService;*/
	
    private Long id;

    private String messageId;

    private String operation;

    private Integer operationFinishNo;

    private Integer confirmCount;

    private String productOrderId;

    private String saleOrderId;

    private String saleOrderRow;
    
    private String materialId;
    
    private String materialDescribe;

    private String processDescribe;

    private Integer currentYield;

    private Integer currentWaste;

    private String reportUsername;

    private Date manufactureDate;

    private String classes;

    private Date accountDate;

    private String messageType;

    private String message;

    private Date createTime;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public Integer getOperationFinishNo() {
        return operationFinishNo;
    }

    public void setOperationFinishNo(Integer operationFinishNo) {
        this.operationFinishNo = operationFinishNo;
    }

    public Integer getConfirmCount() {
        return confirmCount;
    }

    public void setConfirmCount(Integer confirmCount) {
        this.confirmCount = confirmCount;
    }

    public String getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(String productOrderId) {
        this.productOrderId = productOrderId == null ? null : productOrderId.trim();
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

    public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getMaterialDescribe() {
		return materialDescribe;
	}

	public void setMaterialDescribe(String materialDescribe) {
		this.processDescribe = selectProcessByMaterialDescribe(materialDescribe);
		this.materialDescribe = materialDescribe;
	}

	public String getProcessDescribe() {
        return processDescribe;
    }

    public void setProcessDescribe(String processDescribe) {
        this.processDescribe = processDescribe == null ? null : processDescribe.trim();
    }

    public Integer getCurrentYield() {
        return currentYield==null?0:currentYield;
    }

    public void setCurrentYield(Integer currentYield) {
        this.currentYield = currentYield;
    }

    public Integer getCurrentWaste() {
        return currentWaste==null?0:currentWaste;
    }

    public void setCurrentWaste(Integer currentWaste) {
        this.currentWaste = currentWaste;
    }

    public String getReportUsername() {
        return reportUsername;
    }

    public void setReportUsername(String reportUsername) {
        this.reportUsername = reportUsername == null ? null : reportUsername.trim();
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes == null ? null : classes.trim();
    }

    public Date getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(Date accountDate) {
        this.accountDate = accountDate;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType == null ? null : messageType.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    
    /**
	 * 根据前两个字符串（物料名）匹配对应的工序
	 * @param materialDescribe
	 * @return
	 */
    public String selectProcessByMaterialDescribe(String materialDescribe) {
		String s = null;
		if(null!=materialDescribe&&!"".equals(materialDescribe)){
			s = materialDescribe.substring(0, 2);
			if(s.equals("轮辐")||s.equals("轮辋")||s.equals("合成")){
				s = "入库";
			}else if(s.equals("钢圈")){
				s = "成品检验";
			}else{
				s = "旋压";
			}
		}
		return s;
    }
}
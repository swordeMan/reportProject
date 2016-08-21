package com.eliteams.quick4j.web.model;

import java.util.Date;

public class ReworkView {
    private String reworkId;

    private String materialId;

    private String materialDescribe;

    private String productionProcedure;

    private Date reworkTime;

    private String classes;

    private String createPersonnel;

    private Long wasteTotal;

    private Date createTime;

    private String auditor;

    private Date auditingTime;

    private String auditingIllustration;

    private String state;

    private String income;

    private String file;

    private Long reworkReasonId;

    private String illustration;

    private Integer reworkNum;

    private String reason;

    public String getReworkId() {
        return reworkId;
    }

    public void setReworkId(String reworkId) {
        this.reworkId = reworkId == null ? null : reworkId.trim();
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

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes == null ? null : classes.trim();
    }

    public String getCreatePersonnel() {
        return createPersonnel;
    }

    public void setCreatePersonnel(String createPersonnel) {
        this.createPersonnel = createPersonnel == null ? null : createPersonnel.trim();
    }

    public Long getWasteTotal() {
        return wasteTotal;
    }

    public void setWasteTotal(Long wasteTotal) {
        this.wasteTotal = wasteTotal;
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

    public String getAuditingIllustration() {
        return auditingIllustration;
    }

    public void setAuditingIllustration(String auditingIllustration) {
        this.auditingIllustration = auditingIllustration == null ? null : auditingIllustration.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income == null ? null : income.trim();
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file == null ? null : file.trim();
    }

    public Long getReworkReasonId() {
        return reworkReasonId;
    }

    public void setReworkReasonId(Long reworkReasonId) {
        this.reworkReasonId = reworkReasonId;
    }

    public String getIllustration() {
        return illustration;
    }

    public void setIllustration(String illustration) {
        this.illustration = illustration == null ? null : illustration.trim();
    }

    public Integer getReworkNum() {
        return reworkNum;
    }

    public void setReworkNum(Integer reworkNum) {
        this.reworkNum = reworkNum;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }
}
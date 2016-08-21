package com.eliteams.quick4j.web.model;

import java.util.Date;

public class Rework {
    private Long id;

    private String reworkId;

    private String materialId;

    private String materialDescribe;

    private String productionProcedure;

    private String factory;

    private Date reworkTime;

    private String classes;

    private Long reworkNum;

    private String createPersonnel;

    private Date createTime;

    private String auditor;

    private Date auditingTime;

    private String auditingIllustration;

    private String file;

    private String state;

    private String income;

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

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory == null ? null : factory.trim();
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

    public Long getReworkNum() {
        return reworkNum;
    }

    public void setReworkNum(Long reworkNum) {
        this.reworkNum = reworkNum;
    }

    public String getCreatePersonnel() {
        return createPersonnel;
    }

    public void setCreatePersonnel(String createPersonnel) {
        this.createPersonnel = createPersonnel == null ? null : createPersonnel.trim();
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

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income == null ? null : income.trim();
    }
}
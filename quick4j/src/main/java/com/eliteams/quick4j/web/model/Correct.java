package com.eliteams.quick4j.web.model;

import java.sql.Timestamp;
import java.util.Date;

public class Correct {
    private Long id;

    private String correctId;

    private String materialId;

    private String materialDescribe;

    private Integer correctNum;

    private String revokePersonnel;

    private Date createTime;

    private String createReason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorrectId() {
        return correctId;
    }

    public void setCorrectId(String correctId) {
        this.correctId = correctId == null ? null : correctId.trim();
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

    public Integer getCorrectNum() {
        return correctNum;
    }

    public void setCorrectNum(Integer correctNum) {
        this.correctNum = correctNum;
    }

    public String getRevokePersonnel() {
        return revokePersonnel;
    }

    public void setRevokePersonnel(String revokePersonnel) {
        this.revokePersonnel = revokePersonnel == null ? null : revokePersonnel.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateReason() {
        return createReason;
    }

    public void setCreateReason(String createReason) {
        this.createReason = createReason == null ? null : createReason.trim();
    }
}
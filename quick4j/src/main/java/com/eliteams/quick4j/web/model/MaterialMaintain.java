package com.eliteams.quick4j.web.model;

public class MaterialMaintain {
    private Long id;

    private String productNickname;

    private String materialDescribe;

    private String materialId;

    private String deiviceDescribe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductNickname() {
        return productNickname;
    }

    public void setProductNickname(String productNickname) {
        this.productNickname = productNickname == null ? null : productNickname.trim();
    }

    public String getMaterialDescribe() {
        return materialDescribe;
    }

    public void setMaterialDescribe(String materialDescribe) {
        this.materialDescribe = materialDescribe == null ? null : materialDescribe.trim();
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId == null ? null : materialId.trim();
    }

    public String getDeiviceDescribe() {
        return deiviceDescribe;
    }

    public void setDeiviceDescribe(String deiviceDescribe) {
        this.deiviceDescribe = deiviceDescribe == null ? null : deiviceDescribe.trim();
    }
}
package com.eliteams.quick4j.web.model;

public class BomOrder {
    private Long id;

    private String productOrderId;

    private String preserveId;

    private String preserveProject;

    private String materialId;

    private Double targetSum;

    private String delRemark;

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

    public String getPreserveId() {
        return preserveId;
    }

    public void setPreserveId(String preserveId) {
        this.preserveId = preserveId == null ? null : preserveId.trim();
    }

    public String getPreserveProject() {
        return preserveProject;
    }

    public void setPreserveProject(String preserveProject) {
        this.preserveProject = preserveProject == null ? null : preserveProject.trim();
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId == null ? null : materialId.trim();
    }

    public Double getTargetSum() {
        return targetSum;
    }

    public void setTargetSum(Double targetSum) {
        this.targetSum = targetSum;
    }

    public String getDelRemark() {
        return delRemark;
    }

    public void setDelRemark(String delRemark) {
        this.delRemark = delRemark == null ? null : delRemark.trim();
    }
}
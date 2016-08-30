package com.eliteams.quick4j.web.model;

public class MaterialMaintain {
    private Long id;

    private String productNickname;

    private String materialDescribe;

    private String materialId;

    private String deviceDescribe;
    
    private Integer deviceId;
    
    private Integer state;

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

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceDescribe() {
		return deviceDescribe;
	}

	public void setDeviceDescribe(String deviceDescribe) {
		this.deviceDescribe = deviceDescribe;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
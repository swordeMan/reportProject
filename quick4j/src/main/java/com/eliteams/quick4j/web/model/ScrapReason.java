package com.eliteams.quick4j.web.model;

public class ScrapReason {
    private Long id;

    private String materialName;

    private String sReason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName == null ? null : materialName.trim();  //trim()方法-去掉字符串左右空格
    }

    public String getsReason() {
        return sReason;
    }

    public void setsReason(String sReason) {
        this.sReason = sReason == null ? null : sReason.trim();
    }
}
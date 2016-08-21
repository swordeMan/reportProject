package com.eliteams.quick4j.web.model;

import java.util.Date;

public class SysSerialNumber {
    private Long id;

    private String moduleCode;

    private String moduleName;

    private Long maxSerial;

    private Date createTime;
    
    public SysSerialNumber(){
    	
    }
    
    public SysSerialNumber(String moduleCode,Long maxSerial){
    	this.moduleCode = moduleCode;
    	this.maxSerial = maxSerial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode == null ? null : moduleCode.trim();
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public Long getMaxSerial() {
        return maxSerial;
    }

    public void setMaxSerial(Long maxSerial) {
        this.maxSerial = maxSerial;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
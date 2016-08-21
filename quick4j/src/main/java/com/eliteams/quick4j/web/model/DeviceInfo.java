package com.eliteams.quick4j.web.model;

public class DeviceInfo {
    private Integer id;

    private Integer deviceId;

    private String deiviceDescribe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeiviceDescribe() {
        return deiviceDescribe;
    }

    public void setDeiviceDescribe(String deiviceDescribe) {
        this.deiviceDescribe = deiviceDescribe == null ? null : deiviceDescribe.trim();
    }
}
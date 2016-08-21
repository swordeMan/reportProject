package com.eliteams.quick4j.web.model;

import java.util.Date;

public class DeviceFinishedView {
    private String deiviceDescribe;

    private Long reportNum;

    private Date outputDate;

    private Integer deviceId;

    private Integer processId;
    
    //增加变量time,为了通配报废日期
    private String time;

    public String getDeiviceDescribe() {
        return deiviceDescribe;
    }

    public void setDeiviceDescribe(String deiviceDescribe) {
        this.deiviceDescribe = deiviceDescribe == null ? null : deiviceDescribe.trim();
    }

    public Long getReportNum() {
        return reportNum;
    }

    public void setReportNum(Long reportNum) {
        this.reportNum = reportNum;
    }

    public Date getOutputDate() {
        return outputDate;
    }

    public void setOutputDate(Date outputDate) {
        this.outputDate = outputDate;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
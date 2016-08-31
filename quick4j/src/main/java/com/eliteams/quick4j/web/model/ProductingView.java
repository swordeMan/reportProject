package com.eliteams.quick4j.web.model;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.eliteams.quick4j.web.service.UserInfoService;

@Component
public class ProductingView {
    private String productNickname;

    private String materialDescribe;

    private String materialId;

    private Integer deviceId;

    private String deviceDescribe;

    private Date changeTime;

    private String workNum;
    
    private String username;
    
    private static UserInfoService userInfoService;
    
    @Resource
    public void setUserInfoService(UserInfoService userInfoService) {
		ProductingView.userInfoService = userInfoService;
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
        this.deviceDescribe = deviceDescribe == null ? null : deviceDescribe.trim();
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getWorkNum() {
        return workNum;
    }

    public void setWorkNum(String workNum) {
    	if(workNum!=null){
    		this.workNum = workNum.trim();
    		this.username = userInfoService.workNoToUserName(workNum);
    	}else{
    		this.workNum = null;
    	}
    	/*this.workNum = workNum == null ? null : workNum.trim();*/
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
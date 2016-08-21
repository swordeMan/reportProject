package com.eliteams.quick4j.web.service;

import java.util.List;

import com.eliteams.quick4j.web.model.DeviceInfo;

public interface DeviceService {
	
	//查询所有设备信息
	List<DeviceInfo> selectAllDeviceInfo();
	
	//根据工序id查询工序描述
	DeviceInfo selectDeiviceDescribeByDeviceId(Integer deviceId);

}

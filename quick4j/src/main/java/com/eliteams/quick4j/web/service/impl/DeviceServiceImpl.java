package com.eliteams.quick4j.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eliteams.quick4j.web.dao.DeviceInfoMapper;
import com.eliteams.quick4j.web.model.DeviceInfo;
import com.eliteams.quick4j.web.service.DeviceService;

@Service
public class DeviceServiceImpl implements DeviceService {
	
	@Resource
	private DeviceInfoMapper deviceInfoMapper;
	
	@Override
	public List<DeviceInfo> selectAllDeviceInfo() {
		
		return deviceInfoMapper.selectAll();
	}

	@Override
	public DeviceInfo selectDeiviceDescribeByDeviceId(Integer deviceId) {
		return deviceInfoMapper.selectDeiviceDescribeByDeviceId(deviceId);
	}

}

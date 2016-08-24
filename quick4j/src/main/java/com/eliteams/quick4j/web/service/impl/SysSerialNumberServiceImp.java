package com.eliteams.quick4j.web.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.eliteams.quick4j.core.util.DateUtil;
import com.eliteams.quick4j.web.dao.SysSerialNumberMapper;
import com.eliteams.quick4j.web.model.SysSerialNumber;
import com.eliteams.quick4j.web.service.SysSerialNumberService;


@Service
public class SysSerialNumberServiceImp implements SysSerialNumberService {

	private static Logger log = Logger.getLogger(SysSerialNumberServiceImp.class); // 初始化日志对象
	
	@Resource
	SysSerialNumberMapper sysSerialNumberMapper;

	@Override
	public String generateSerialNumberByModelCode(String moduleCode) {
		
		
		SysSerialNumber ssn = new SysSerialNumber();
		long maxSerial = 0l;
		try {
			ssn.setModuleCode(moduleCode);
			maxSerial = sysSerialNumberMapper.getMaxSerialByModuleCode(moduleCode);
		} catch (Exception e) {
			String newDateStr = DateUtil.currentTimestamp2String("yyyyMMdd");
			ssn.setMaxSerial(Long.parseLong(newDateStr+"0001"));
			sysSerialNumberMapper.insert(ssn);
			return moduleCode+newDateStr+"0001";
		}
			ssn.setMaxSerial(Long.parseLong(getSerialNumberByMaxSerial(maxSerial)));
			sysSerialNumberMapper.insert(ssn);
			return moduleCode+getSerialNumberByMaxSerial(maxSerial);
	}
	
	private String getSerialNumberByMaxSerial(Long maxSerial) {
		
		String newDateStr = DateUtil.currentTimestamp2String("yyyyMMdd");
		
		String lastDateStr = maxSerial.toString().substring(0,8);//前8个是日期
		String serialNumberStr = maxSerial.toString().substring(8);//从第九个开始之后的数字
		try {
			if(lastDateStr.equals(newDateStr)){
				int serialNumber = Integer.parseInt(serialNumberStr);
				return newDateStr+IntTo4DigitString(serialNumber+1);
			}
		} catch (NumberFormatException e) {
			log.error("SysSerialNumberServiceImp.getSerialNumberByMaxSerial",e);
		}
		return newDateStr+"0001";
	}

	private String IntTo4DigitString(int value){
		String result = "";
		if(value<10){
			result = "000"+value;
		}else if(value<100){
			result = "00"+value;
		}else if(value<1000){
			result = "0"+value;
		}else{
			result = value+"";
		}
		return result;
	}

}

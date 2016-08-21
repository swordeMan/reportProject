package com.eliteams.quick4j.web.service;

/**
 * 自动产生序列号 接口
 * 
 * @author zhangsh
 **/
public interface SysSerialNumberService {

	/**
	 * 通过moduleCode来找到流水号
	 * @param moduleCode
	 * @return
	 */
	String generateSerialNumberByModelCode(String moduleCode);
	
}

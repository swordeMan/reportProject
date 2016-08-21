package com.eliteams.quick4j.web.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eliteams.quick4j.core.generic.GenericController;
import com.eliteams.quick4j.web.model.DeviceInfo;
import com.eliteams.quick4j.web.service.DeviceService;

/**
 * 设备控制器
 * @author qianjun
 *
 */
@Controller
@RequestMapping(value = "/device")
public class DeviceController extends GenericController{
	
	private static Logger log = Logger.getLogger(DeviceController.class); // 初始化日志对象
	
	@Resource
	DeviceService deviceService;
	
	//动态添加设备信息
	@RequestMapping(value = "/page")
	public String getDeviceInfo(Model model){
		try {
			List<DeviceInfo> deviceInfoList = deviceService.selectAllDeviceInfo();
			model.addAttribute("deviceInfoList", deviceInfoList);
		} catch (Exception e) {
			log.error("设备信息查询出错--getDeviceInfo"+e);
		}
   		return "include/deviceSelect";
	}
	
}

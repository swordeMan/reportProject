package com.eliteams.quick4j.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eliteams.quick4j.web.model.DeviceInfo;
import com.eliteams.quick4j.web.model.ReworkView;
import com.eliteams.quick4j.web.service.DeviceService;
import com.eliteams.quick4j.web.service.ReworkAnalysisService;

/**
 * 返修原因分析模块
 * @author qianjun
 *
 */
@Controller
@RequestMapping(value = "/reworkAnalysis")
public class ReworkAnalysisController {
	
	private static Logger log = Logger.getLogger(ReworkAnalysisController.class); // 初始化日志对象
	
	@Resource
	private DeviceService deviceService;
	
	@Resource
	private ReworkAnalysisService reworkAnalysisService;
	
	@RequestMapping(value = "/reasonAnalysis")
	public String switchTo(Model model,ReworkView reworkView) {
		try {
			String productionProcedure = reworkView.getProductionProcedure();
			if(productionProcedure!=null && !"".equals(productionProcedure)){
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				Date date  = reworkView.getReworkTime();
	    		String time = sdf.format(date);
	    		reworkView.setTime(time);
	    		
				List<ReworkView> reworkViewList = reworkAnalysisService.getReworkDetailsByReworkView(reworkView);
				Integer reworkSum = reworkAnalysisService.getReworkTotal(reworkViewList);
				List<Double> ratioList = reworkAnalysisService.getRatio(reworkViewList, reworkSum);
				List<Double> ratioAddList = reworkAnalysisService.getAddRatio(ratioList);
				model.addAttribute("reworkViewList", reworkViewList);
				model.addAttribute("reworkSum", reworkSum);
				model.addAttribute("ratioList", ratioList);
				model.addAttribute("ratioAddList", ratioAddList);
				model.addAttribute("reworkView", reworkView);
			}
			List<DeviceInfo> deviceInfoList = deviceService.selectAllDeviceInfo();
			model.addAttribute("deviceInfoList", deviceInfoList);
		} catch (Exception e) {
			log.error("返修原因分析页面跳转+返修原因分组查询出错"+e);
		}
		
		return "analysis/reworkReasonAnalysis";
	}
	
}

package com.eliteams.quick4j.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eliteams.quick4j.web.model.DeviceFinishedView;
import com.eliteams.quick4j.web.model.DeviceInfo;
import com.eliteams.quick4j.web.model.ScrapView;
import com.eliteams.quick4j.web.service.DeviceService;
import com.eliteams.quick4j.web.service.ScrapAnalysisService;

/**
 * 分析决策模块——报废原因分析
 * @author qianjun
 *
 */
@Controller
@RequestMapping(value = "/scrapAnalysis")
public class ScrapAnalysisController {
	
	private static Logger log = Logger.getLogger(ScrapReasonController.class); // 初始化日志对象
	
	@Resource
	private DeviceService deviceService;
	
	@Resource
	private ScrapAnalysisService scrapAnalysisService;
	/**
	 * 报废原因分析
	 * @param model
	 * @param scrapView
	 * @return
	 */
	@RequestMapping(value = "/reasonAnalysis")
	public String switchTo(Model model,ScrapView scrapView) {
		try {
			String productionProcess = scrapView.getProductionProcess();
			if(productionProcess!=null && !"".equals(productionProcess)){
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				Date date  = scrapView.getScrapTime();
	    		String time = sdf.format(date);
	    		scrapView.setTime(time);
	    		
				List<ScrapView> scrapViewList = scrapAnalysisService.getScrapDetailsByScrapView(scrapView);
				Integer wasteSum = scrapAnalysisService.getScrapTotal(scrapViewList);
				List<Double> ratioList = scrapAnalysisService.getRatio(scrapViewList, wasteSum);
				List<Double> ratioAddList = scrapAnalysisService.getAddRatio(ratioList);
				model.addAttribute("scrapViewList", scrapViewList);
				model.addAttribute("wasteSum", wasteSum);
				model.addAttribute("ratioList", ratioList);
				model.addAttribute("ratioAddList", ratioAddList);
				model.addAttribute("scrapView", scrapView);
			}
			List<DeviceInfo> deviceInfoList = deviceService.selectAllDeviceInfo();
			model.addAttribute("deviceInfoList", deviceInfoList);
		} catch (Exception e) {
			log.error("报废原因分析页面跳转+报废原因分组查询出错"+e);
		}
		
		return "analysis/scrapAnalysis/scrapReasonAnalysis";
	}
	/**
	 * 合格率分析
	 * @param model
	 * @param scrapView
	 * @return
	 */
	@RequestMapping(value = "/passRateAnalysis")
	public String ratio(Model model,ScrapView scrapView) {
		try {
			String productionProcess = scrapView.getProductionProcess();
			if(productionProcess!=null && !"".equals(productionProcess)){
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
				Date date  = scrapView.getYear();
	    		String time = sdf.format(date);
	    		scrapView.setTime(time);
	    		
	    		DeviceFinishedView deviceFinishedView = new DeviceFinishedView();
	    		deviceFinishedView.setDeiviceDescribe(productionProcess);
	    		deviceFinishedView.setTime(time);
	    		
	    		Double objective = scrapAnalysisService.getObjective(productionProcess);
	    		List<Integer> scrapSumList = scrapAnalysisService.getScrapTotalByMonth(scrapView);
	    		List<Integer> productSumList = scrapAnalysisService.getFinishedTotalByMonth(deviceFinishedView);
				List<Double> scrapRatioList = scrapAnalysisService.getScrapRatio(scrapSumList, productSumList);
				List<Double> passRateList = scrapAnalysisService.getPassRatio(scrapRatioList);
				
				model.addAttribute("scrapRatioList", scrapRatioList);
				model.addAttribute("passRateList", passRateList);
				model.addAttribute("scrapSumList", scrapSumList);
				model.addAttribute("productSumList", productSumList);
				model.addAttribute("scrapView", scrapView);
				model.addAttribute("objective", objective);
			}
			List<DeviceInfo> deviceInfoList = deviceService.selectAllDeviceInfo();
			model.addAttribute("deviceInfoList", deviceInfoList);
		} catch (Exception e) {
			log.error("报废原因分析页面跳转+报废原因分组查询出错",e);
		}
		return "analysis/scrapAnalysis/passRateAnalysis";
	}
	
}

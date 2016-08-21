package com.eliteams.quick4j.web.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eliteams.quick4j.core.entity.Json;
import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.dao.ReportTimeMapper;
import com.eliteams.quick4j.web.dao.SapOrderMapper;
import com.eliteams.quick4j.web.form.ExcleView;
import com.eliteams.quick4j.web.model.DeviceInfo;
import com.eliteams.quick4j.web.model.ReportTime;
import com.eliteams.quick4j.web.model.ReportYield;
import com.eliteams.quick4j.web.model.SapOrder;
import com.eliteams.quick4j.web.model.Scrap;
import com.eliteams.quick4j.web.model.ScrapReason;
import com.eliteams.quick4j.web.service.AssignmentService;
import com.eliteams.quick4j.web.service.DeviceService;
import com.eliteams.quick4j.web.service.ReportYieldService;
import com.eliteams.quick4j.web.service.SapOrderService;
import com.eliteams.quick4j.web.service.ScrapReasonService;
import com.eliteams.quick4j.web.service.ScrapService;
import com.sap.conn.jco.JCoException;

@Controller
@RequestMapping(value = "/sapOrder")
public class SapOrderController {
	
	private static Logger log = Logger.getLogger(SapOrderController.class); // 初始化日志对象
	
	@Resource
	SapOrderService sapOrderService;
	
	@Resource
	ReportYieldService reportYieldService;
	
	@Resource
	ScrapService scrapService;
	
	@Resource
	ScrapReasonService scrapReasonService;
	
	@Resource
	ReportTimeMapper reportTimeMapper;
	
	@Resource
	DeviceService deviceService;
	
	@Resource
	AssignmentService assignmentService;
	
	@Resource
	SapOrderMapper sapOrderMapper;
	
	@RequestMapping(value = "/testInsert")
	@ResponseBody
	public Json testInsert(Json json){
		String factory = "1001";
		ReportTime reportTime = reportTimeMapper.selectAll().get(0);
		Date alterTime = reportTime.getReportTime();
		
		Calendar cal=Calendar.getInstance();  
		cal.setTime(alterTime);
		cal.add(Calendar.DAY_OF_MONTH, -2);
		alterTime=cal.getTime();
		try {
			sapOrderService.getProductOrderInfo(factory,alterTime,alterTime);
			reportTimeMapper.updateByPrimaryKey(reportTime);
		} catch (Exception e) {
			log.error("订单获取失败"+e.getMessage());
			return json.ajaxDoneError("获取失败");
		}
		
		return json.ajaxDoneSuccess("获取成功");
		
	}
	
	
	@RequestMapping(value = "/testReport")
	public void testReport(){
		ReportYield ry = new ReportYield();
		ry.setMessageId("300000013");
		ry.setOperation("A");
		ry.setProductOrderId("000010043494");
		ry.setProcessDescribe("旋压");
		ry.setCurrentYield(150);
		ry.setCurrentWaste(20);
		ry.setManufactureDate(new Date(2016-1900,3,4));
		ry.setClasses("早班");
		try {
			reportYieldService.reportCurrentYield(ry);
		} catch (JCoException e) {
			log.error("测试报工失败--"+e);
		}
	}
	
	/**
     * 生产订单分页列表查看
     */
    @RequestMapping(value = "/list")
    public String list(Model model,Page<SapOrder> page,HttpServletRequest request) {
    	String orderField = request.getParameter("orderField");
		String orderDirection = request.getParameter("orderDirection");
		String keywords = request.getParameter("keywords");
		model.addAttribute("orderField",orderField);
    	model.addAttribute("orderDirection",orderDirection);
    	model.addAttribute("keywords",keywords);
    	String orderByClause = null;
    	if(orderField!=null&&!"".equals(orderField)){
    		orderByClause = orderField+" "+orderDirection;
    	}
    	try {
    		sapOrderService.getUserInfoByPage(page, orderByClause, keywords);
		} catch (Exception e) {
			log.error("生产订单分页列表查看出错--sapOrder.list"+e);
		}
    	model.addAttribute(page);
        return "plan/sapOrder/list";
    }
    
    /**
     * 
     * 根据生产订单号查单条生产订单所有信息
     * 根据物料描述（截取字符串前两个）查询报废原因
     */
    
    @RequestMapping("/query/{productOrderId}")
   	public String query(@PathVariable("productOrderId") String productOrderId, Model model,HttpServletRequest request) {
    	try {
    		//String startTime = request.getParameter("startTime");
			SapOrder sapOrder = sapOrderService.getSapOrderInfoById(productOrderId);
			String materialName = sapOrder.getMaterialDescribe();
			List<ScrapReason> scrapReasonList = scrapReasonService.selectScrapReasonByMaterialDescribe(materialName);
			List<DeviceInfo> deviceInfoList = deviceService.selectAllDeviceInfo();
			Date now = new Date();
			model.addAttribute("deviceInfoList", deviceInfoList);
			model.addAttribute("now", now);
			model.addAttribute(scrapReasonList);
			model.addAttribute(sapOrder);
			
			/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    		Date date = sdf.parse(startTime); 
			model.addAttribute("date", date);*/
		} catch (Exception e) {
			log.error("生产订单查询，关联原因出错--sapOrder.query"+e);
		}
   		return "plan/sapOrder/newScrap1";
   	}
    /**
     * 根据生产订单号查单条生产订单所有信息
     * 查询所有的设备信息
     * @param productOrderId
     * @param model
     * @return
     */
    @RequestMapping("/getSapOrderInfo/{productOrderId}")
   	public String getSapOrder(@PathVariable("productOrderId") String productOrderId, Model model) {
    	
    	try {
			SapOrder sapOrder = sapOrderService.getSapOrderInfoById(productOrderId);
			List<DeviceInfo> deviceInfoList = deviceService.selectAllDeviceInfo();
			Date now = new Date();
			model.addAttribute("deviceInfoList", deviceInfoList);
			model.addAttribute(sapOrder);
			model.addAttribute("now",now);
		} catch (Exception e) {
			log.error("生产订单查询出错--sapOrder.getSapOrder"+e);
		}
   		return "plan/sapOrder/newAssignment";
   	}
    
    /*@ResponseBody
    public Json getAssignment(String productOrderId,Json json){
    	Assignment assignment = assignmentService.getAssignmentInfoByProductOrderId(productOrderId);
    	if(assignment!=null){
    		return json.ajaxDoneError("该生产订单已下达任务");
    	}
		return json;
    }*/
    
    @RequestMapping("/orderProcess/{productOrderId}")
   	public String orderProcess(@PathVariable("productOrderId") String productOrderId, Model model) {
    	SapOrder sapOrder = sapOrderService.getSapOrderInfoById(productOrderId);
    	List<ReportYield> reportList = reportYieldService.getAReportYieldByProductOrderId(productOrderId);
    	List<ReportYield> cancelList = reportYieldService.getBReportYieldByProductOrderId(productOrderId);
    	List<Scrap> scrapList = scrapService.getScrapListByProductOrderId(productOrderId);
    	
    	sapOrder.setRate(sapOrder.getFinishedTotal(), sapOrder.getTargetSum());
    	
    	model.addAttribute("sapOrder",sapOrder);					
    	model.addAttribute("reportList",reportList);
    	model.addAttribute("scrapList",scrapList);
    	model.addAttribute("cancelList",cancelList);
    	
   		return "execute/orderProcess";
   	}
    
    @RequestMapping(value = "/exportExcle")
    public ModelAndView exportExcle(Map<String, Object> model,Page<SapOrder> page,HttpServletRequest request, HttpServletResponse response) {
    	String orderField = request.getParameter("orderField");
		String orderDirection = request.getParameter("orderDirection");
		String keywords = request.getParameter("keywords");
    	String orderByClause = null;
    	if(orderField!=null&&!"".equals(orderField)){
    		orderByClause = orderField+" "+orderDirection;
    	}
		page.setPageSize(65000);
    	try {
    		sapOrderService.getUserInfoByPage(page, orderByClause, keywords);
		} catch (Exception e) {
			log.error("生产订单分页列表查看出错--sapOrder.list"+e);
		}
    	model.put("list",page.getResult());
    	String[] fieldNames = {"productOrderId","saleOrderId","saleOrderRow",
				"userSimpleName","materialId","materialDescribe","targetSum",
				"finishedTotal","wasteTotal","relateScarp","planStartDate","planEndDate"};
    	model.put("fieldNames",fieldNames);
    	return new ModelAndView(new ExcleView<SapOrder>("生产订单"),model);
    }
    
}

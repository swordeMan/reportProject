package com.eliteams.quick4j.web.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eliteams.quick4j.core.entity.Json;
import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.core.generic.GenericController;
import com.eliteams.quick4j.web.form.ExcleView;
import com.eliteams.quick4j.web.form.ReportByHandForm;
import com.eliteams.quick4j.web.model.ReportYield;
import com.eliteams.quick4j.web.model.SapOrder;
import com.eliteams.quick4j.web.service.ReportYieldService;
import com.eliteams.quick4j.web.service.SapOrderService;

@Controller
@RequestMapping(value = "/reportYield")
public class ReportYieldController extends GenericController {
	
	private static Logger log = Logger.getLogger(ReportYieldController.class); // 初始化日志对象
	
	@Resource
	SapOrderService sapOrderService;
	
	@Resource
	ReportYieldService reportYieldService;
	
	@RequestMapping("/reportByHand/{productOrderId}")
   	public String reportByHand(@PathVariable("productOrderId") String productOrderId, Model model) {
    	try {
			SapOrder sapOrder = sapOrderService.getSapOrderInfoById(productOrderId);
			List<SapOrder> sapOrderList = new LinkedList<>();
			//销售订单可能为空
			if(sapOrder.getSaleOrderId()==null||"".equals(sapOrder.getSaleOrderId())){
				sapOrderList.add(sapOrder);
			}else{
				sapOrderList = sapOrderService.getSapOrderListBySaleOrderId(sapOrder.getSaleOrderId());
			}
			Date now = new Date();
			model.addAttribute("now",now);
			model.addAttribute(sapOrderList);
		} catch (Exception e) {
			log.error("手动报工页面展示出错--reportYield.reportByHand"+e);
		}
   		return "plan/sapOrder/reportByHand";
   	}
	
	
    @RequestMapping(value = "/doReportByHand",method = RequestMethod.POST)
    @ResponseBody
    public Json doReportByHand(@ModelAttribute("reportByHandForm")ReportByHandForm reportByHandForm, Json json) {
    	try {
    		List<ReportYield> reportYieldList = reportYieldService.getReportYieldListByHandForm(reportByHandForm);
    		if(reportYieldList==null){
    			return json.ajaxDoneError("前道工序订单还未报工，请重新填写");//只要有一个不合适就打回重填
    		}
    		List<ReportYield> reportYieldedList = reportYieldService.reportMoreYields(reportYieldList);
    		StringBuffer notification = new StringBuffer("通知：");
        	for(ReportYield ryed :reportYieldedList){
        		notification.append("\r\n").append(ryed.getProductOrderId()).append(ryed.getMessage());
        	}
        	return json.ajaxConfirmSuccess(notification.toString());
		} catch (Exception e) {
			log.error("手动报工提交出错--reportYield.doReportByHand",e);
			return json.ajaxDoneError("系统数据出现异常");
		}
    }
    
    /**
     * 报工列表查看
     */
    @RequestMapping(value = "/list")
    public String list(Model model,Page<ReportYield> page,HttpServletRequest request) {
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
    		reportYieldService.getReportYieldByPage(page,orderByClause, keywords);
		} catch (Exception e) {
			log.error("报工列表查看出错--reportYield.list"+e.getMessage());
		}
    	model.addAttribute(page);
        return "execute/report/list";
    }
    
    /**
     * 根据主键查单条报工记录进行冲销
     * @param id
     * @return
     */
    @RequestMapping("/cancelReportYield/{id}")
    @ResponseBody
   	public Json cancelReportYield(@PathVariable("id") Long id, Json json) {
    	ReportYield reportYield = reportYieldService.selectReportYieldById(id);
    	ReportYield cancelReportYielded;
    	try {
    		//报工冲销
    		cancelReportYielded = reportYieldService.cancelReportYield(reportYield);
		} catch (Exception e) {
			log.error("报工冲销出错--reportYield.save",e);
			return json.ajaxDoneError("订单冲销失败");
		}
    	return json.ajaxDoneSuccess(cancelReportYielded.getMessage());
   	}
    
    @RequestMapping(value = "/exportExcle")
    public ModelAndView exportExcle(Map<String, Object> model,Page<ReportYield> page,HttpServletRequest request) {
    	String orderField = request.getParameter("orderField");
		String orderDirection = request.getParameter("orderDirection");
		String keywords = request.getParameter("keywords");
    	String orderByClause = null;
    	if(orderField!=null&&!"".equals(orderField)){
    		orderByClause = orderField+" "+orderDirection;
    	}
		page.setPageSize(65000);
    	try {
    		reportYieldService.getReportYieldByPage(page,orderByClause, keywords);
		} catch (Exception e) {
			log.error("报工列表查看出错--reportYield.list"+e.getMessage());
		}
    	model.put("list",page.getResult());
    	String[] fieldNames = {"messageId","productOrderId","saleOrderId",
				"saleOrderRow","materialId","materialDescribe","currentYield",
				"currentWaste","message","reportUsername","accountDate","createTime"};
    	model.put("fieldNames",fieldNames);
    	return new ModelAndView(new ExcleView<ReportYield>("报工单列表"),model);
    }
    
}

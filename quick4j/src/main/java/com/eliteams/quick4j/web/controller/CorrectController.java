package com.eliteams.quick4j.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eliteams.quick4j.core.entity.Json;
import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.core.generic.GenericController;
import com.eliteams.quick4j.web.model.Correct;
import com.eliteams.quick4j.web.service.CorrectService;
import com.eliteams.quick4j.web.service.StockService;
import com.eliteams.quick4j.web.service.SysSerialNumberService;


/**
 *修正控制
 *
 * @author liuliu
 * 2016年6月26日 下午4:09:20
 */
@Controller
@RequestMapping(value = "/correct")
public class CorrectController extends GenericController{
	private static Logger log = Logger.getLogger(CorrectController.class); // 初始化日志对象
	@Resource
	private CorrectService correctService;
	@Resource
	private StockService stockService;
	@Resource
	private SysSerialNumberService sysSerialNumberService;
	/**
	 * 修正记录单列表查看
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(Model model,Page<Correct> page,HttpServletRequest request){
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
			correctService.getCorrectByPage(page,orderByClause, keywords);
			model.addAttribute(page);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("产量修正查询错误");
		}
		
		return "yield/correct/correctList";
	}
	
	/**
	 * 按照correctId查询修正单,显示订单详情
	 * @param correctId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/query/{correctId}")
	public String query(@PathVariable("correctId") String correctId, Model model){
		Correct correct=correctService.getCorrectByCorrectId(correctId);
		model.addAttribute("correct",correct);
		return "yield/correct/detail";
	}
	/**
	 * 添加新的修正单
	 * 改变待分配库存量
	 * @param correct
	 * @param model
	 * @param request
	 * @return result
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
	public Json insertCorrect(Correct correct,HttpServletRequest request ,Json json){
		correct.setCorrectId(sysSerialNumberService.generateSerialNumberByModelCode("XZ"));
		
		try {
			correctService.insertCorrect(correct);
		} catch (Exception e) {
			log.error("创建修正单错误"+e);
			return json.ajaxDoneError();
	}
		return json.ajaxDoneSuccess(null);
}
}
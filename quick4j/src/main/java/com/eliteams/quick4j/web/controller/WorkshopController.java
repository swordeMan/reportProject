package com.eliteams.quick4j.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eliteams.quick4j.core.entity.Json;
import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.model.ReworkReason;
import com.eliteams.quick4j.web.model.Workshop;
import com.eliteams.quick4j.web.service.WorkshopService;

@Controller
@RequestMapping(value = "/workshop")
public class WorkshopController {
	private static Logger log = Logger.getLogger(ReworkReasonController.class); // 初始化日志对象

	@Resource
	private WorkshopService workshopService;
	@RequestMapping(value = "/workshopList")
	public String list(Model model,Page<Workshop> page,HttpServletRequest request){
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
		try{
			workshopService.getWorkshopByPage(page,orderByClause,keywords);
			model.addAttribute("page",page);
		}catch (Exception e) {
			log.error("车间列表查询错误");
	}
		return "gernal/workshop/workshopList";
}
	/**
	 * 添加框展示
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toAdd")
	public String toAdd() {
			return "gernal/workshop/add";
	}
	/**
	 * 编辑框展示
	 * @param model
	 * @return
	 */
	 
	@RequestMapping(value = "/qureyId/{id}")
	public String qurey(@PathVariable("id")int id ,Model model){
		Workshop workshop = workshopService.selectByPrimaryKey(id);
	    model.addAttribute(workshop);
	    return "gernal/workshop/edit";
	}
	 /**
     * 工厂编辑
     */
    @RequestMapping(value ="/update")
    @ResponseBody
	public Json update(Workshop workshop,Json json) {

    	try {workshopService.editWorkshop(workshop);
		} 
    	catch (Exception e) {
			log.error("用户删除出错"+e);
			return json.ajaxDoneError();
		}
		return json.ajaxDoneSuccess();
	}
	
	/**
	 * 插入车间
	 * @param workshop
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/insert")
    @ResponseBody
    public Json save(Workshop workshop,Json json) {
		try {
			workshopService.insertWorkshop(workshop);
		} catch (Exception e) {
			log.error("添加车间出错"+e);
			return json.ajaxDoneError();
		}
		return json.ajaxDoneSuccess();
			
	}	
	/**
     * 报废原因删除
     */
    @RequestMapping(value ="/delete/{id}")
    @ResponseBody
	public Json delete(@PathVariable("id")int id ,Json json) {

    	try {
    		workshopService.deleteWorkshop(id);
		} catch (Exception e) {
			log.error("用户删除出错"+e);
			return json.ajaxDoneError();
		}
		return json.ajaxDoneSuccess();
	}
	
}
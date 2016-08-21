package com.eliteams.quick4j.web.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.eliteams.quick4j.core.generic.GenericController;
import com.eliteams.quick4j.web.model.ReworkReason;
import com.eliteams.quick4j.web.model.ScrapReason;
import com.eliteams.quick4j.web.model.UserInfo;
import com.eliteams.quick4j.web.service.ReworkReasonService;

/**
 * 
 * 返修原因控制器
 * @author shenji
 *
 */
@Controller
@RequestMapping(value = "/reworkReason")
public class ReworkReasonController extends GenericController{
	private static Logger log = Logger.getLogger(ReworkReasonController.class); // 初始化日志对象
	
	@Resource
	private ReworkReasonService reworkReasonService;
	/**
	 * 页面展示
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(Model model, Page<ReworkReason> page,HttpServletRequest request ) {
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
			reworkReasonService.getReworkReasonByPage(page,orderByClause, keywords);
			model.addAttribute(page);
			
		} catch (Exception e) {
			log.error("返修原因查询错误");
		}
		
		return "gernal/reworkReason/list";
	}
	
	
	/**
	 * 添加框展示
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toAdd")
	public String toAdd() {
			return "gernal/reworkReason/add";
	}
	/**
	 * 编辑框展示
	 * @param model
	 * @return
	 */
	 
	@RequestMapping(value = "/qureyId/{id}")
	public String qurey(@PathVariable("id")long id ,Model model){
		ReworkReason reworkReason = reworkReasonService.selectByPrimaryKey(id);
	    model.addAttribute(reworkReason);
	    return "gernal/reworkReason/edit";
	}
	
	
	 /**
     * 返修原因编辑
     */
    @RequestMapping(value ="/update")
    @ResponseBody
	public Json update(ReworkReason reworkReason,Json json) {

    	try {
    		reworkReasonService.editReworkReason(reworkReason);
		} catch (Exception e) {
			log.error("用户删除出错"+e);
			return json.ajaxDoneError();
		}
		return json.ajaxDoneSuccess();
	}

	
	
	/**
	 * 插入返修原因
	 * @param insertReworkReason
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/insert")
    @ResponseBody
    public Json save(ReworkReason reworkReason,Json json) {
		try {
			reworkReasonService.insertReworkReason(reworkReason);
		} catch (Exception e) {
			log.error("添加返修原因"+e);
			return json.ajaxDoneError();
		}
		return json.ajaxDoneSuccess();
			
	}	
	 /**
     * 报废原因删除
     */
    @RequestMapping(value ="/delete/{id}")
    @ResponseBody
	public Json delete(@PathVariable("id")long id ,Json json) {

    	try {
    		reworkReasonService.deleteReworkReason(id);
		} catch (Exception e) {
			log.error("用户删除出错"+e);
			return json.ajaxDoneError();
		}
		return json.ajaxDoneSuccess();
	}
    /**
     * 原因下拉列表重复带回
     * @param request
     * @param model
     * @return
     */
	@RequestMapping(value = "/page")
	public String getScrapReason(HttpServletRequest request,Model model){
		String materialDescribe= request.getParameter("materialDescribe");
		List<ReworkReason> reworkReasonList = reworkReasonService.selectReworkReasonByName(materialDescribe);
    	model.addAttribute(reworkReasonList);
		return "include/reworkReasonSelect";
	}
	/**
	 * 我的主页中根据物料描述onchange后首次查询返修原因
	 * @param request
	 * @return
	 */
	@RequestMapping("/getReworkReason")
    @ResponseBody
	public List<ReworkReason> getReworkReason(HttpServletRequest request){
		List<ReworkReason> reworkReasonList= new ArrayList<ReworkReason>();
		try{
		String materialDescribe= request.getParameter("materialDescribe");
		reworkReasonList=reworkReasonService.selectReworkReasonByName(materialDescribe);
		}catch(Exception e){
			log.error("返修原因查询出错"+e);
		}
		return reworkReasonList;
		
	}
	
}
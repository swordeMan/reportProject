package com.eliteams.quick4j.web.controller;

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
import com.eliteams.quick4j.web.model.ScrapReason;
import com.eliteams.quick4j.web.model.UserInfo;
import com.eliteams.quick4j.web.service.ScrapReasonService;
/**
 * 
 * 报废原因控制器
 * @author qianjun
 *
 */
@Controller
@RequestMapping(value = "/scrapReason")
public class ScrapReasonController extends GenericController{
	
	private static Logger log = Logger.getLogger(ScrapReasonController.class); // 初始化日志对象
	
	@Resource
	private ScrapReasonService scrapReasonService;
	/**
	 * 页面展示
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(Model model, Page<ScrapReason> page,HttpServletRequest request) {
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
			scrapReasonService.getScrapReasonByPage(page, orderByClause,keywords);
			model.addAttribute(page);
		} catch (Exception e) {
			log.error("报废原因查询错误");
		}
		
		return "gernal/scrapReason/list";
	}
	
	/**
	 * 
	 * 报废原因动态添加
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/page")
	public String getScrapReason(HttpServletRequest request,Model model){
		String materialDescribe= request.getParameter("materialDescribe");
		List<ScrapReason> scrapReasonList = scrapReasonService.selectScrapReasonByMaterialDescribe(materialDescribe);
    	model.addAttribute(scrapReasonList);
		return "include/reasonSelect";
		
	}
	
	/**
	 * 我的主页中根据物料描述onchange后首次查询报废原因
	 * @param request
	 * @return
	 */
	@RequestMapping("/getScrapReason")
    @ResponseBody
	public List<ScrapReason> getScrapReason(HttpServletRequest request){
		List<ScrapReason> scrapReasonList = null;
		try {
			String materialDescribe= request.getParameter("materialDescribe");
			scrapReasonList = scrapReasonService.selectScrapReasonByMaterialDescribe(materialDescribe);
		} catch (Exception e) {
			log.error("报废原因查询出错"+e);
		}
		return scrapReasonList;
	}
	
	/**
	 * 添加框展示
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toAdd")
	public String toAdd() {
			return "gernal/scrapReason/add";
	}
	/**
	 * 查找到要编辑的记录，编辑框展示
	 * @param model
	 * @return
	 */
	 
	@RequestMapping(value = "/qureyById/{id}")
	public String qurey(@PathVariable("id")long id ,Model model){
		ScrapReason scrapReason = scrapReasonService.selectByPrimaryKey(id);
	    model.addAttribute(scrapReason);
	    return "gernal/scrapReason/edit";
	}
	
	/**
	 * ajax请求，根据id查询报废原因对象，用以判断原因是否为其他
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/selectByPrimaryKey")
	@ResponseBody
	public ScrapReason selectByPrimaryKey(HttpServletRequest request){
		String reasonId = request.getParameter("id");
		Long id = (long) Integer.parseInt(reasonId);
		ScrapReason scrapReason = scrapReasonService.selectByPrimaryKey(id);
		return scrapReason;
	}
	
	
	 /**
     * 报废原因编辑
     */
    @RequestMapping(value ="/update")
    @ResponseBody
	public Json update(ScrapReason scrapReason,Json json) {

    	try {
    		scrapReasonService.editScrapReason(scrapReason);
		} catch (Exception e) {
			log.error("用户删除出错"+e);
			return json.ajaxDoneError();
		}
		return json.ajaxDoneSuccess();
	}

	
	
	/**
	 * 插入原因
	 * @param insertScrapReason
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/insert")
    @ResponseBody
    public Json save(ScrapReason scrapReason,Json json) {
		try {
			scrapReasonService.insertScrapReason(scrapReason);
		} catch (Exception e) {
			log.error("添加报废原因"+e);
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
    		scrapReasonService.deleteScrapReason(id);
		} catch (Exception e) {
			log.error("用户删除出错"+e);
			return json.ajaxDoneError();
		}
		return json.ajaxDoneSuccess();
	}
}

package com.eliteams.quick4j.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.core.generic.GenericController;
import com.eliteams.quick4j.web.model.MaterialMaintain;
import com.eliteams.quick4j.web.service.MaterialMaintainService;

/**
 * 物料维护控制器
 * @author qianjun
 *
 */
@Controller
@RequestMapping(value = "/material")
public class MaterialMaintainController extends GenericController{
	
	private static Logger log = Logger.getLogger(MaterialMaintainController.class); // 初始化日志对象
	
	@Resource
	private MaterialMaintainService materialMaintainService;
	
	/**
	 * 物料基础表页面展示
	 * @param model
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(Model model, Page<MaterialMaintain> page,HttpServletRequest request) {
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
			materialMaintainService.getMaterialInfoByPageAndKeywords(page, orderByClause, keywords);
			model.addAttribute(page);
		} catch (Exception e) {
			log.error("物料基础数据查询错误"+e);
		}
		
		return "gernal/materialMaintain/list";
	}
}

package com.eliteams.quick4j.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 我的主页业务功能跳转使用
 * @author qianjun
 *
 */
@Controller
@RequestMapping(value = "/homePage")
public class HomePageController {
	
	private static Logger log = Logger.getLogger(HomePageController.class); // 初始化日志对象
	
	//新建报废
	@RequestMapping("/scrap_create")
    public String switchToScrapCreate(){
    	return "gernal/homePage/scrap_create";
    }
	
	//新建返修
	@RequestMapping("/rework_create")
    public String switchToReworkCreate(){
    	return "gernal/homePage/rework_create";
    }
	
	//产线切换
	@RequestMapping("/productLine_switch")
    public String switchToProductLine(){
    	return "gernal/homePage/productLine_switch";
    }
	
	//任务下达
	@RequestMapping("/assignment_order")
    public String switchToAssignmentOrder(){
    	return "gernal/homePage/assignment_order";
    }
}

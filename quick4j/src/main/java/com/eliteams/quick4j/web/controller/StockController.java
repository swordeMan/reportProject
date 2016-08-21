package com.eliteams.quick4j.web.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.core.generic.GenericController;
import com.eliteams.quick4j.web.dao.StockMapper;
import com.eliteams.quick4j.web.model.DeviceInfo;
import com.eliteams.quick4j.web.model.ReworkReason;
import com.eliteams.quick4j.web.model.Stock;
import com.eliteams.quick4j.web.service.DeviceService;
import com.eliteams.quick4j.web.service.ReworkReasonService;
import com.eliteams.quick4j.web.service.StockService;

/*
 * 
 * 报废控制器
 */
@Controller
@RequestMapping(value = "/stock")
public class StockController extends GenericController {
	private static Logger log = Logger.getLogger(StockController.class); // 初始化日志对象
	@Resource
	private StockService stockService;
	
    @Resource
    private ReworkReasonService  reworkReasonService;
    
    @Resource
    private StockMapper stockMapper;
    @Resource
   private DeviceService deviceService;
	/**
	 * 待分配物料列表查看
	 */
	@RequestMapping(value = "/list")
	public String list(Model model, Page<Stock> page,HttpServletRequest request) {
		log.info("StockController.list");
		try {
			String keywords = request.getParameter("keywords");
			stockService.getStockByPage(page, keywords);
			model.addAttribute(page);
		} catch (Exception e) {
			log.error("待分配量查询错误");
		}
  		  return "yield/stock/stockList";
	}
	
	
	/**
	 * 根据页面请求跳转到添加新修正单界面
	 * 根据物料编码查询待分配量
	 * @param correctId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toAddCorrect/{id}")
	public String toAddCorrect(@PathVariable("id") long id, Model model){
		Stock stockquery=stockMapper.selectByPrimaryKey(id);
		
		model.addAttribute(stockquery);
		
		return "yield/correct/addCorrect";
	}
	
	/**
	 * 根据页面请求跳转到添加新返修单界面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toAddRework/{id}")
	public String toAddRework(@PathVariable("id") long id, Model model){
		Stock stockquery=stockMapper.selectByPrimaryKey(id);
		String materialDescribe=stockquery.getMaterialDescribe();
		List<DeviceInfo> deviceInfoList=deviceService.selectAllDeviceInfo();
		List<ReworkReason> reworkReasonList = null;
		Date now = new Date();
		try {
			reworkReasonList = reworkReasonService.selectReworkReasonByName(materialDescribe);
		} catch (Exception e) {
			log.error("StockController.toAddRework"+e);
		}
		model.addAttribute("now", now);
		model.addAttribute(reworkReasonList);
		model.addAttribute(stockquery);
		model.addAttribute(deviceInfoList);
		return "yield/rework/addRework";
	}
	
}

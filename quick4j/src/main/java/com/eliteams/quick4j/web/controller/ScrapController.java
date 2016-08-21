package com.eliteams.quick4j.web.controller;

import java.util.Date;
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

import com.eliteams.quick4j.core.entity.Json;
import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.core.generic.GenericController;
import com.eliteams.quick4j.web.dao.ScrapMapper;
import com.eliteams.quick4j.web.dao.ScrapViewMapper;
import com.eliteams.quick4j.web.form.CreateScrapForm;
import com.eliteams.quick4j.web.model.Assignment;
import com.eliteams.quick4j.web.model.DeviceInfo;
import com.eliteams.quick4j.web.model.ReportYield;
import com.eliteams.quick4j.web.model.Scrap;
import com.eliteams.quick4j.web.model.ScrapReason;
import com.eliteams.quick4j.web.model.ScrapView;
import com.eliteams.quick4j.web.service.AssignmentService;
import com.eliteams.quick4j.web.service.DeviceService;
import com.eliteams.quick4j.web.service.ScrapService;

/*
 * 
 * 报废控制器
 */
@Controller
@RequestMapping(value = "/scrap")
public class ScrapController extends GenericController {
	
	private static Logger log = Logger.getLogger(ScrapController.class); // 初始化日志对象

	@Resource
	private ScrapService scrapService;
	
	@Resource
	private ScrapViewMapper scrapViewMapper;
	
	@Resource
	private ScrapMapper scrapMapper;
	
	@Resource
	private AssignmentService assignmentService;
	
	@Resource
	private DeviceService deviceService;
	
	/**
	 * 报废单列表查看
	 */
	@RequestMapping(value = "/list")
	public String list(Model model,Page<Scrap> page,HttpServletRequest request) {
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
			scrapService.getScrapInfoByPage(page, orderByClause, keywords);
			model.addAttribute(page);
		} catch (Exception e) {
			log.error("报废分页列表查看--scrap.list"+e);
		}
		return "execute/scrap/list";
	}
	
	
	/**
	 * 报工异常报废单列表查看
	 */
	@RequestMapping(value = "/scrapExceptionList")
	public String ExceptionList(Model model,Page<Scrap> page,HttpServletRequest request) {
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
			scrapService.getExceptionScrapByPageAndKeywords(page, orderByClause, keywords);
			model.addAttribute(page);
		} catch (Exception e) {
			log.error("异常报废报工分页列表查看--scrap.scrapExceptionList"+e);
		}
		return "execute/scrap/scrapExceptionList";
	}
	
	
	
	/**
	 * 编制新的报废单
	 * 1 页面跳转
	 * 2 数据库插入加页面刷新
	 * 3任务表查询，为了取销售单号
	 */
	@RequestMapping(value = "/add")
	public String dispatch(Model model){
		try {
			Date now = new Date();
			List<String> saleOrderIdList = assignmentService.getAllSaleOrderId();
			List<DeviceInfo> deviceInfoList = deviceService.selectAllDeviceInfo();
			model.addAttribute("deviceInfoList", deviceInfoList);
			model.addAttribute("saleOrderIdList",saleOrderIdList);
			model.addAttribute("now",now);
		} catch (Exception e) {
			log.error("销售订单号列表查询错误"+e);
		}
		return "execute/scrap/add";
	}
	
	/**
	 * 主页新建报废单，跳转到查询页面
	 * @return
	 */
	@RequestMapping(value = "/criteriaQuery")
	public String switchTo(){
		/*try {
			List<String> materialDescribeList = assignmentService.selectAllMaterialDescribe();
			List<String> userSimpleNameList = assignmentService.selectAllUserSimpleName();
			model.addAttribute("materialDescribeList",materialDescribeList);
			model.addAttribute("userSimpleNameList", userSimpleNameList);
			
		} catch (Exception e) {
			log.error("查询启动任务中物料描述和客户简称出错"+e);
		}*/
		return "execute/scrap/criteriaQuery";	
	}
	/**
	 * 动态查询已确定任务中的物料描述
	 * @return
	 */
	@RequestMapping(value = "/getMaterialDescribeList")
	@ResponseBody
	public List<String> getMaterialDescribeList(){
		List<String> materialDescribeList = assignmentService.selectAllMaterialDescribe();
		return materialDescribeList;
	}
	/**
	 * 动态查询已确定任务中的客户简称
	 * @return
	 */
	@RequestMapping(value = "/getUserSimpleName")
	@ResponseBody
	public List<String> getUserSimpleName(){
		List<String> userSimpleNameList = assignmentService.selectAllUserSimpleName();
		return userSimpleNameList;
	}
	
	/**
	 * 提交保存报废单，一线员工刷卡提交
	 * @param createScrapForm
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Json save(@ModelAttribute("createScrapForm")CreateScrapForm createScrapForm,Json json) {
		//scrapService.insertScrap(scrap,scrapReasonRelation);
		String workNo = createScrapForm.getScrap().getInspector();
		if(workNo==null||"".equals(workNo)){
			return json.ajaxDoneError("请刷卡后再提交");
		}
		try {
			log.info("scrap.save开始"+createScrapForm);
			scrapService.insertScrap(createScrapForm);
		} catch (Exception e) {
			log.error("报废保存--scrap.save"+e);
			return json.ajaxDoneError("编制报废单失败");
		}
		return json.ajaxDoneSuccess("编制报废单成功");
    }
	
	/**
	 * 提交保存报废单，管理人员提交
	 * @param createScrapForm
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/insertByManager")
    @ResponseBody
    public Json insertByManager(@ModelAttribute("createScrapForm")CreateScrapForm createScrapForm,Json json) {
		try {
			scrapService.insertScrap2(createScrapForm);
		} catch (Exception e) {
			log.error("报废保存--scrap.save"+e);
			return json.ajaxDoneError("编制报废单失败");
		}
		return json.ajaxDoneSuccess("编制报废单成功");
    }
	
	/**
	 * 关联视图查询报废详情供审核
	 * @param scrapId
	 * @return
	 */
	@RequestMapping(value = "/verify/{scrapId}")
	public String verify(@PathVariable("scrapId") String scrapId,Model model,Json json){
		try {
			List<ScrapView> scrapViewList = scrapViewMapper.selectById(scrapId);
			/*if(scrapViewList.get(0).getState()!=null){
			   return json.ajaxDoneSuccess("该报废单已经审核");
			}*/
			Date now = new Date();
			model.addAttribute("scrapViewList", scrapViewList);
			model.addAttribute("now", now);
		} catch (Exception e) {
			log.error("关联视图查询报废详情"+e);
		}
		
		/*return json.ajaxDoneSuccess("报废单详情查询", "execute/scrap/auditing");*/
		return "execute/scrap/auditing";
	}
	
	/**
	 * 根据报废单号查询该条记录供重新报工
	 * @param scrapId
	 * @return
	 */
	@RequestMapping(value = "/reportAgain/{scrapId}")
	@ResponseBody
	public Json reportAgain(@PathVariable("scrapId") String scrapId,Json json){
		try {
			Scrap scrap = scrapService.selectByscrapId(scrapId);
			if(scrapService.yield(scrap)){
				return json.ajaxDoneSuccess("报废重新报工成功");
			}else{
				return json.ajaxDoneSuccess("报废重新报工失败");
			}
		} catch (Exception e) {
			log.error("报废重新报工失败"+e);
			return json.ajaxDoneSuccess("报废重新报工失败");
		}
	}
	
	
	
	/**
	 * 关联视图查询报废详情供打印
	 * @param scrapId
	 * @return
	 */
	@RequestMapping(value = "/print/{scrapId}")
	public String print(@PathVariable("scrapId") String scrapId,Model model){
		try {
			List<ScrapView> scrapViewList = scrapViewMapper.selectById(scrapId);
			model.addAttribute("scrapViewList", scrapViewList);
		} catch (Exception e) {
			log.error("关联视图查询报废详情供打印失败"+e);
		}
		return "execute/scrap/printScrap";
	}
	
	/**
	 * 报废审核
	 * @param scrap
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/update")
    @ResponseBody
    public Json update(Scrap scrap,Json json) {
		try {
			scrapMapper.updateByScrap(scrap);
			
			if(scrap.getState().equals("审核通过")){
				/*if(scrapService.yieldAndUpdate(scrap)){
					//得到关联报废列表
					List<Scrap> relatedScrapList = scrapService.getRelatedScrap(scrap);
					if(relatedScrapList.size()>0){
						for(Scrap sc:relatedScrapList){
							scrapService.updateRelatedScrapNum(sc);
						}
					}
				}*/
				scrapService.yieldAndUpdate(scrap);
				//得到关联报废列表
				List<Scrap> relatedScrapList = scrapService.getRelatedScrap(scrap);
				if(relatedScrapList.size()>0){
					for(Scrap sc:relatedScrapList){
						scrapService.updateRelatedScrapNum(sc);
					}
				}
			}
			
		} catch (Exception e) {
			log.error("报废审核"+e);
			return json.ajaxDoneError("该报废单审核失败");
		}
		return json.ajaxDoneSuccess("该报废单审核成功");
    }
	
	/**
	 * 
	 * 报废备注说明动态添加
	 * @return
	 */
	@RequestMapping(value = "/page")
	public String getScrapIllustration(){
		return "include/scrapIllustration";
	}
	/**
	 * 报废单打印
	 */
	@RequestMapping(value = "/printScrap/{scrapId}")
	@ResponseBody
	public Json printRework(@PathVariable("scrapId")String scrapId,Json json){
		try {
			scrapService.printScrapList(scrapId);
		} catch (Exception e) {
			log.error("打印报废单错误"+e);
			return json.ajaxDoneError();
		}
		return json.ajaxDoneSuccess(null);
		
	}
}

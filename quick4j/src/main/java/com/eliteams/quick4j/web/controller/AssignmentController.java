package com.eliteams.quick4j.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.eliteams.quick4j.web.dao.AssignmentMapper;
import com.eliteams.quick4j.web.dao.AssignmentViewMapper;
import com.eliteams.quick4j.web.form.CreateAssignmentForm;
import com.eliteams.quick4j.web.model.Assignment;
import com.eliteams.quick4j.web.model.AssignmentView;
import com.eliteams.quick4j.web.model.DeviceInfo;
import com.eliteams.quick4j.web.model.MaterialMaintain;
import com.eliteams.quick4j.web.model.ReportYield;
import com.eliteams.quick4j.web.model.SapOrder;
import com.eliteams.quick4j.web.model.ScrapReason;
import com.eliteams.quick4j.web.service.AssignmentService;
import com.eliteams.quick4j.web.service.AssignmentViewService;
import com.eliteams.quick4j.web.service.DeviceService;
import com.eliteams.quick4j.web.service.MaterialMaintainService;
import com.eliteams.quick4j.web.service.SapOrderService;
import com.eliteams.quick4j.web.service.ScrapReasonService;

/**
 * 任务下达控制器
 * @author qianjun
 *
 */
@Controller
@RequestMapping(value = "/assignment")
public class AssignmentController {
	
	private static Logger log = Logger.getLogger(ScrapController.class); // 初始化日志对象
	
	@Resource
	private AssignmentService assignmentService;
	
	@Resource
	private AssignmentMapper assignmentMapper;
	
	@Resource
	private DeviceService deviceService;
	
	@Resource
	private SapOrderService sapOrderService;
	
	@Resource
	private AssignmentViewMapper assignmentViewMapper;
	
	@Resource
	private AssignmentViewService assignmentViewService;
	
	@Resource
	ScrapReasonService scrapReasonService;
	
	@Resource
	private MaterialMaintainService materialMaintainService;
	
	/**
	 * 单个生产订单任务下达
	 * @param createAssignmentForm
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Json save(@ModelAttribute("createAssignmentForm")CreateAssignmentForm createAssignmentForm,Json json) {
		try {
			assignmentService.insertIntoAssignment(createAssignmentForm);
		} catch (Exception e) {
			log.error("任务下达保存"+e);
			return json.ajaxDoneError("任务下达失败");
		}
		return json.ajaxDoneSuccess("任务下达成功");
    }
	
	/**
	 * 批量生产订单任务下达
	 * @param createAssignmentForm
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Json insert(@ModelAttribute("createAssignmentForm")CreateAssignmentForm createAssignmentForm,Json json) {
		try {
			assignmentService.insertAssignment(createAssignmentForm);
		} catch (Exception e) {
			log.error("任务下达保存"+e);
			return json.ajaxDoneError("任务下达失败");
		}
		return json.ajaxDoneSuccess("任务下达成功");
    }
	
	/**
	 * 查询任务列表
	 * @param model
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String getAssignmentList(Model model,Page<AssignmentView> page,HttpServletRequest request,AssignmentView assignmentView){
		String orderField = request.getParameter("orderField");
		String orderDirection = request.getParameter("orderDirection");
		String keywords = request.getParameter("keywords");
		/*model.addAttribute("orderField",orderField);
    	model.addAttribute("orderDirection",orderDirection);
    	model.addAttribute("keywords",keywords);*/
		assignmentView.setOrderField(orderField);
		assignmentView.setOrderDirection(orderDirection);
		assignmentView.setKeywords(keywords);
		
    	String orderByClause = null;
    	if(orderField!=null&&!"".equals(orderField)){
    		orderByClause = orderField+" "+orderDirection;
    		assignmentView.setOrderByClause(orderByClause);
    	}
    	model.addAttribute("assignmentView", assignmentView);
		try {
			assignmentViewMapper.getSapOrderByPageAndKeywords(page, assignmentView);
			model.addAttribute("page",page);
		} catch (Exception e) {
			log.error("生产订单任务查询出错"+e);
		}
		return "plan/assignmentOrder/list";
		
	}
	
	/**
	 * 启动单条任务
	 * @param id
	 * @param json
	 * @return
	 */
    @RequestMapping("/startAssignment/{id}")
    @ResponseBody
   	public Json StartAssignment(@PathVariable("id") Long id, Json json) {
    	try {
    		assignmentMapper.updateByPrimaryKey(id);
    		Assignment ass = assignmentMapper.selectByPrimaryKey(id);
    		String productOrderId = ass.getProductOrderId();
    		sapOrderService.updateSapOrderStateMark(productOrderId);
		} catch (Exception e) {
			log.error("任务启动失败"+e);
			return json.ajaxDoneError("任务启动失败");
		}
    	return json.ajaxDoneSuccess("任务启动成功");
   	}
    /**
     * 设置任务为优先执行，即设启动时间提前一年
     * @param id
     * @param json
     * @return
     */
    @RequestMapping("/first/{id}")
    @ResponseBody
   	public Json first(@PathVariable("id") Long id, Json json) {
    	try {
    		assignmentMapper.updateStartTimeByPrimaryKey(id);
		} catch (Exception e) {
			log.error("设置任务优先执行失败",e);
			return json.ajaxDoneError("任务优先执行失败");
		}
    	return json.ajaxDoneSuccess("任务优先执行成功");
   	}
    
    /**
     * 查询任务视图中的单条记录，填报报废单
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/queryAssignmentView/{id}")
   	public String queryAssignmentView(@PathVariable("id") Long id, Model model) {
    	try {
    		AssignmentView asv = assignmentViewService.selectAssignmentViewById(id);
    		String materialName = asv.getMaterialDescribe();
			List<ScrapReason> scrapReasonList = scrapReasonService.selectScrapReasonByMaterialDescribe(materialName);
			Date now = new Date();
			model.addAttribute("now", now);
			model.addAttribute("scrapReasonList",scrapReasonList);
			model.addAttribute("asv",asv);
    	} catch (Exception e) {
			log.error("任务视图查询，报废原因查询失败-assignment.queryAssignmentView"+e);
		}
    	return "plan/sapOrder/createScrapByManager";
   	}
    
    /**
     * 查询下达任务中的所有物料描述
     * @param request
     * @return
     */
    @RequestMapping("/getMaterialDsc")
    @ResponseBody
    public List<Assignment> getMaterialDsc(HttpServletRequest request){
    	//List<String> MaterialDscList = null;
    	List<Assignment> assignmentList = null;
    	try {
			String saleOrderId = request.getParameter("saleOrderId");
			//MaterialDscList = assignmentService.getAllMaterialDesBySaleOrderId(saleOrderId);
			assignmentList = assignmentService.getAssignmentListBySaleOrderId(saleOrderId);
		} catch (Exception e) {
			log.error("物料描述查询出错"+e);
		}
		return assignmentList;
    }
    
    /**
     * 根据物料批量下达任务
     * 查询所有的工序信息,转向主页任务下达
     * @param model
     * @return
     */
    @RequestMapping("/getDeviceInfo")
   	public String getDeviceInfo(Model model,HttpServletRequest request) {
    	try {
    		String deviceId = request.getParameter("deviceId");
			int dd = Integer.parseInt(deviceId);
			List<MaterialMaintain> materialMaintainList = materialMaintainService.getMaterialInfoBydeviceId(dd);
			
			/*List<DeviceInfo> deviceInfoList = deviceService.selectAllDeviceInfo();
			List<SapOrder> sapOrderList = sapOrderService.selectSapOrderList();
			List<String> materialIdList = sapOrderService.getMaterialIdFromUnstart();*/
			Date now = new Date();
			model.addAttribute("now",now);
			model.addAttribute("materialMaintainList", materialMaintainList);
			/*model.addAttribute("deviceInfoList", deviceInfoList);
			model.addAttribute("sapOrderList", sapOrderList);
			model.addAttribute("materialIdList", materialIdList);*/
		} catch (Exception e) {
			log.error("基础物料信息查询出错--assignment.getDeviceInfo",e);
		}
   		return "plan/assignmentOrder/createAssignment";
   	}
    
    /**
     * 根据用户输入的三个查询条件查询对应的已启动任务（报废日期、物料描述和客户简称）
     * @param request
     * @return
     */
    @RequestMapping("/getAssignmentList")
    public String getAssignmentList(Assignment assignment,Model model, HttpServletRequest request){
    	//List<Assignment> assignmentList = null;
    	try {
    		/*String materialDescribe = request.getParameter("materialDescribe");
    		String scrapTime = request.getParameter("scrapTime");
    		String userSimpleName = request.getParameter("userSimpleName");
    		
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    		Date date = sdf.parse(scrapTime);  
    		
    		Assignment assignment = new Assignment();
    		assignment.setStartTime(date);
    		assignment.setMaterialDescribe(materialDescribe);
    		assignment.setUserSimpleName(userSimpleName);*/
    		//String deiviceDescribe = request.getParameter("deiviceDescribe");
    		Date now = new Date();
			model.addAttribute("now", now);
    		
    		List<Assignment> assignmentList = assignmentService.obtainAssignmentByCriteriaQuery(assignment);
    		Assignment ass = assignmentList.get(0);
    		
    		String productOrderId = ass.getProductOrderId();
    		SapOrder sapOrder = sapOrderService.getSapOrderInfoById(productOrderId);
    		
			String materialName = ass.getMaterialDescribe();
			int deviceId = ass.getDeviceId();
			DeviceInfo deviceInfo = deviceService.selectDeiviceDescribeByDeviceId(deviceId);
			
			List<ScrapReason> scrapReasonList = scrapReasonService.selectScrapReasonByMaterialDescribe(materialName);
			model.addAttribute(scrapReasonList);
			model.addAttribute("ass",ass);
			model.addAttribute("sapOrder", sapOrder);
			model.addAttribute("assignment", assignment);
			model.addAttribute("deviceInfo", deviceInfo);
		} catch (Exception e) {
			log.error("生产订单查询，关联原因出错"+e);
		}
		return "plan/sapOrder/newScrap";
    }
    
    /**
     * ajax请求，根据用户输入的三个查询条件查询是否有已启动任务（报废日期、物料描述和客户简称）
     * @param request
     * @return
     */
    @RequestMapping("/selectAssignment")
    @ResponseBody
    public List<Assignment> getAssignment(HttpServletRequest request){
    	List<Assignment> assignmentList = null;
    	try {
    		String materialId = request.getParameter("materialId");
    		String startTime = request.getParameter("startTime");
    		String userSimpleName = request.getParameter("userSimpleName");
    		
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    		Date date = sdf.parse(startTime);  
    		
    		Assignment assignment = new Assignment();
    		assignment.setStartTime(date);
    		assignment.setMaterialId(materialId);
    		assignment.setUserSimpleName(userSimpleName);
    		
    		assignmentList = assignmentService.obtainAssignmentByCriteriaQuery(assignment);
    		
		} catch (Exception e) {
			log.error("根据用户输入的三个查询条件查询是否有已启动任务出错"+e);
		}
		return assignmentList;
    }
    
    /**
     * 根据工序查询对应的物料描述、编码和客户简称信息
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/assignmentView")
    public String getInfoByDeviceDescribe(HttpServletRequest request,Model model){
    	try {
			String deviceId = request.getParameter("deviceId");
			int dd = Integer.parseInt(deviceId);
			List<AssignmentView> assignmentViewList = assignmentViewService.getMaterialIdAndDescribeByDeviceDes(dd);
			List<String> userSimpleList = assignmentViewService.getUserSimpleNameByDeviceDes(dd);
			model.addAttribute("assignmentViewList", assignmentViewList);
			model.addAttribute("userSimpleList", userSimpleList);
			//model.addAttribute("deiviceDescribe", deiviceDescribe);
			
		} catch (Exception e) {
			log.error("根据工序查询对应的物料描述、编码和客户简称信息出错"+e);
		}
    	
		return "execute/scrap/criteriaQuery2";
    }
    
    @RequestMapping("/deleteAssignment/{id}")
    @ResponseBody
   	public Json deleteAssignment(@PathVariable("id") Long id, Json json) {
    	try {
    		Assignment ass = assignmentMapper.selectByPrimaryKey(id);
    		String productOrderId = ass.getProductOrderId();
    		
    		assignmentService.deleteByPrimaryKey(id);
    		sapOrderService.updateSapOrderStateMarkToUnAllocate(productOrderId);
		} catch (Exception e) {
			log.error("任务删除失败"+e);
			return json.ajaxDoneError("任务删除失败");
		}
    	return json.ajaxDoneSuccess("任务删除成功");
   	}
    
    /**
     * 根据物料编码查询未下达任务的订单
     * @param request
     * @return
     */
    @RequestMapping("/getUnStartSapOrderList")
    @ResponseBody
    public List<SapOrder> getUnStartSapOrderList(HttpServletRequest request){
    	List<SapOrder> sapOrderList = null;
    	try {
    		String materialId = request.getParameter("materialId");
    		sapOrderList = sapOrderService.getUnStartSapOrderListByMaterialId(materialId);
    		
		} catch (Exception e) {
			log.error("根据物料编码查询未下达任务的订单出错",e);
		}
		return sapOrderList;
    }
    
    /**
     * 任务列表高级搜索页面跳转
     * @return
     */
    @RequestMapping("/queriedAccurately")
    public String queriedAccurately(){
    	return "plan/assignmentOrder/advancedSearch";
    }
}

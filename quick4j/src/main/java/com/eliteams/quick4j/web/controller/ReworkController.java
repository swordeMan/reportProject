package com.eliteams.quick4j.web.controller;

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
import com.eliteams.quick4j.core.generic.GenericController;
import com.eliteams.quick4j.web.dao.ReworkReasonMapper;
import com.eliteams.quick4j.web.dao.ReworkViewMapper;
import com.eliteams.quick4j.web.form.CreateReworkForm;
import com.eliteams.quick4j.web.form.CreateScrapForm;
import com.eliteams.quick4j.web.model.Correct;
import com.eliteams.quick4j.web.model.DeviceInfo;
import com.eliteams.quick4j.web.model.Rework;
import com.eliteams.quick4j.web.model.ReworkReasonRelation;
import com.eliteams.quick4j.web.model.ReworkView;
import com.eliteams.quick4j.web.model.Stock;
import com.eliteams.quick4j.web.service.DeviceService;
import com.eliteams.quick4j.web.service.PDFReportService;
import com.eliteams.quick4j.web.service.ReworkReasonRelationService;
import com.eliteams.quick4j.web.service.ReworkService;
import com.eliteams.quick4j.web.service.StockService;

@Controller
@RequestMapping(value = "/rework")
public class ReworkController extends GenericController {
	private static Logger log = Logger.getLogger(ReworkController.class); // 初始化日志对象
	@Resource
	private ReworkService reworkService;
	@Resource
	private ReworkReasonRelationService reworkReasonRelationService;
	@Resource
	private ReworkReasonMapper reworkReasonMapper;
	@Resource
	private StockService stockService;
    @Resource
    private DeviceService deviceService;
	/**
	 * 查询所有返修单
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(Model model, Page<Rework> page, HttpServletRequest request) {
		String orderField = request.getParameter("orderField");
		String orderDirection = request.getParameter("orderDirection");
		String keywords = request.getParameter("keywords");
		model.addAttribute("orderField", orderField);
		model.addAttribute("orderDirection", orderDirection);
		model.addAttribute("keywords",keywords);
		String orderByClause = null;
		if (orderField != null && !"".equals(orderField)) {
			orderByClause = orderField + " " + orderDirection;
		}
		try {
			reworkService.getReworkByPage(page, orderByClause,keywords);
			model.addAttribute(page);

		} catch (Exception e) {
			// TODO: handle exception
			log.error("返修处理查询错误");
		}

		return "yield/rework/reworkList";
	}

	/**
	 * 查看某一返修订单详情
	 * 
	 * @param reworkId
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/query/{reworkId}")
	public String qurey(@PathVariable("reworkId") String reworkId, Model model) {

		Rework rework = reworkService.selectByReworkId(reworkId);
		// 获取原因关联list
		List<ReworkReasonRelation> reworkReasonRelationList = reworkReasonRelationService.selectByReworkId(reworkId);
		// 从关联表获取原因ID list
		List<Long> reworkReasonIdList = reworkReasonRelationService.selectByReworkIdGetReasonId(reworkId);
		// 创建list用来接收原因
		List<String> reworkReasonList = new ArrayList<String>();
		String reworkReason = null;
		Long reworkReasonId = null;
		// 通过每个审核原因id查询审核原因再添加到原因list中
		for (int i = 0; i < reworkReasonIdList.size(); i++) {
			reworkReasonId = reworkReasonIdList.get(i);
			reworkReason = reworkReasonMapper.selectByPrimaryKey(reworkReasonId).getReason();
			reworkReasonList.add(reworkReason);
		}
		model.addAttribute(rework);
		model.addAttribute(reworkReasonRelationList);
		model.addAttribute("reworkReasonList", reworkReasonList);
		return "yield/rework/detail";
	}

	/**
	 * 跳转到审核界面
	 * 
	 * @param reworkId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryForAuditing/{reworkId}")
	public String qureyForAuditing(@PathVariable("reworkId") String reworkId, Model model) {
		Rework rework = reworkService.selectByReworkId(reworkId);
		// 获取原因关联list
		List<ReworkReasonRelation> reworkReasonRelationList = reworkReasonRelationService.selectByReworkId(reworkId);
		// 从关联表获取原因ID list
		List<Long> reworkReasonIdList = reworkReasonRelationService.selectByReworkIdGetReasonId(reworkId);
		// 创建list用来接收原因
		List<String> reworkReasonList = new ArrayList<String>();
		String reworkReason = null;
		Long reworkReasonId = null;
		// 通过每个审核原因id查询审核原因再添加到原因list中
		for (int i = 0; i < reworkReasonIdList.size(); i++) {
			reworkReasonId = reworkReasonIdList.get(i);
			reworkReason = reworkReasonMapper.selectByPrimaryKey(reworkReasonId).getReason();
			reworkReasonList.add(reworkReason);
		}
		model.addAttribute(rework);
		model.addAttribute(reworkReasonRelationList);
		model.addAttribute("reworkReasonList", reworkReasonList);
		return "yield/rework/auditing";
	}

	/**
	 * 产生新的返修单
	 * 
	 * @param rework
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/insert")
	@ResponseBody
	public Json insertRework(@ModelAttribute("createReworkForm") CreateReworkForm createReworkForm, Json json) {
		try {
			reworkService.insertRework(createReworkForm);
		} catch (Exception e) {
			log.error("创建返修单错误"+e);
			return json.ajaxDoneError();
		}
		return json.ajaxDoneSuccess();
	}

	/**
	 * 进行审核
	 * 
	 * @param createReworkForm
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/insertAuditing", method = RequestMethod.POST)
	@ResponseBody
	public Json insertAuditing(@ModelAttribute("createAuditing") Rework rework, Json json) {
		try {
			reworkService.insertAuditing(rework);
		} catch (Exception e) {
			log.error("创建返修审核错误"+e);
			return json.ajaxDoneError();
		}
		return json.ajaxDoneSuccess(null);
	}
	/**
	 * 通过主页添加返修单
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/insertRework")
	public String dispatch(Model model,HttpServletRequest request) {
		try {
			
			String requestId = request.getParameter("deviceId");
			int deviceId=Integer.parseInt(requestId);
			DeviceInfo deviceInfo=deviceService.selectDeiviceDescribeByDeviceId(deviceId);
			String productionProcedure=deviceInfo.getDeiviceDescribe();
			List<Stock> stockdList = stockService.selectStockByMaterialName(productionProcedure);
	        // 工序下拉
			//List<DeviceInfo> deviceInfoList=deviceService.selectAllDeviceInfo();
			Date now = new Date();
			model.addAttribute("stockList", stockdList);
			model.addAttribute("now", now);
			model.addAttribute("productionProcedure",productionProcedure);
		} catch (Exception e) {
			log.error("待插入返修单错误"+e);
		}
		return "yield/rework/insertRework";
	}
	/**
	 * 列表界面打印返修单
	 * @return
	 */
	/*@RequestMapping(value = "/printRework/{reworkId}")
	@ResponseBody
	public Json printRework(@PathVariable("reworkId")String reworkId,Json json){
		try {
			reworkService.printRewokList(reworkId);
		} catch (Exception e) {
			log.error("打印返修单错误"+e);
			return json.ajaxDoneError();
		}
		return json.ajaxDoneSuccess(null);		
	}*/
}

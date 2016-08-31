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
import com.eliteams.quick4j.web.dao.ProductChangedMapper;
import com.eliteams.quick4j.web.model.Assignment;
import com.eliteams.quick4j.web.model.DeviceInfo;
import com.eliteams.quick4j.web.model.MaterialMaintain;
import com.eliteams.quick4j.web.model.ProductChanged;
import com.eliteams.quick4j.web.model.ProductingView;
import com.eliteams.quick4j.web.service.DeviceService;
import com.eliteams.quick4j.web.service.MaterialMaintainService;
import com.eliteams.quick4j.web.service.ProductingViewService;

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
	
	@Resource
	private DeviceService deviceService;
	
	@Resource
	private ProductingViewService productingViewService;
	
	@Resource
	private ProductChangedMapper productChangedMapper;
	
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
			log.error("物料基础数据列表查询错误"+e);
		}
		return "gernal/materialMaintain/list";
	}
	/**
	 * 添加框展示
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toAdd")
	public String toAdd() {
			return "gernal/materialMaintain/add";
	}
   /**
   * 添加物料数据
   * @param materialMaintain
   * @param json
   * @return
   */
	@RequestMapping(value = "/insert")
    @ResponseBody
    public Json save(MaterialMaintain materialMaintain,Json json) {
		try {
			materialMaintainService.insertMaterialMaintain(materialMaintain);
		} catch (Exception e) {
			log.error("添加物料数据"+e);
			return json.ajaxDoneError();
		}
		return json.ajaxDoneSuccess();			
	}
	/**
	 * 物料数据删除
	 * @param id
	 * @param json
	 * @return
	 */
	 @RequestMapping(value ="/delete/{id}")
	    @ResponseBody
		public Json delete(@PathVariable("id")Long id ,Json json) {

	    	try {
	    		materialMaintainService.deleteMaterialMaintain(id);
			} catch (Exception e) {
				log.error("物料删除出错"+e);
				return json.ajaxDoneError();
			}
			return json.ajaxDoneSuccess();
		}
	 /**
	  *跳转到编辑界面
	  * @param id
	  * @param model
	  * @return
	  */
	@RequestMapping(value = "/qureyById/{id}")
	public String qurey(@PathVariable("id")Long id ,Model model){
		MaterialMaintain materialMaintain = materialMaintainService.selectByPrimaryKey(id);
	    model.addAttribute(materialMaintain);
	    return "gernal/materialMaintain/edit";
	}
	/**
	 *修改物料数据
	 * @param materialMaintain
	 * @param json
	 * @return
	 */
	 @RequestMapping(value ="/update")
	    @ResponseBody
		public Json update(MaterialMaintain materialMaintain,Json json) {

	    	try {materialMaintainService.editMaterialMaintain(materialMaintain);
			} 
	    	catch (Exception e) {
				log.error("用户删除出错"+e);
				return json.ajaxDoneError();
			}
			return json.ajaxDoneSuccess();
		}

	/**
	 * 待切换物料基础数据查询
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/getMaterialInfo")
	public String switchProduct(Model model,HttpServletRequest request){
		try {
			String deviceId = request.getParameter("deviceId");
			int dd = Integer.parseInt(deviceId);
			List<ProductingView> productingViewList = productingViewService.getMaterialInfoBydeviceDesc(dd);
			model.addAttribute("productingViewList", productingViewList);
			model.addAttribute("dd", dd);
		} catch (Exception e) {
			log.error("根据工序查询物料基础数据错误",e);
		}
		return "switch/productionChanged";
	}
	
	/**
	 * 产线切换
	 * @param productChanged
	 * @param json
	 * @return
	 */
	@RequestMapping("/change")
    @ResponseBody
   	public Json deleteAssignment(ProductChanged productChanged, Json json) {
		String workNum = productChanged.getWorkNum();
		if(workNum == null ||"".equals(workNum)){
			return json.ajaxDoneError("请刷卡后再提交");
		}
    	try {
    		productChangedMapper.insert(productChanged);
		} catch (Exception e) {
			log.error("产线切换失败"+e);
			return json.ajaxDoneError("产线切换失败");
		}
    	return json.ajaxDoneSuccess("产线切换成功");
   	}
}

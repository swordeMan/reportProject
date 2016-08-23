package com.eliteams.quick4j.web.service;

import java.util.List;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.form.ReportByHandForm;
import com.eliteams.quick4j.web.model.ReportYield;
import com.eliteams.quick4j.web.model.StockAssignmentView;
import com.sap.conn.jco.JCoException;

/**
 * sap 订单交互接口
 * @author zhang
 *
 */
public interface ReportYieldService{
	
	String REPORT_MESSAGE_ID = "BG";//报工消息首字母

	String CANCLE_MESSAGE_ID = "CX";//冲销信息首字母
	
	String REPORT_OPREATION = "A";//报工操作
	
	String CANCEL_OPREATION = "B";//冲销操作
	
	String SUC_MESSAGE_TYPE = "S";//报工或冲销成功

	String WARN_MESSAGE_TYPE = "W";//报工或冲销警告
	
	String ERR_MESSAGR_TYPE = "E";
	
	String SCRAP_OPREATION = "C";//报废操作
	
	/**
	 * 
	 * @param ry 报工时只有拥有传入参数的对象，其中opreation为A-报工;B-冲销
	 * @return ReportYield 报工之后得到回传值得对象
	 * @throws JCoException
	 */
	ReportYield reportCurrentYield (ReportYield ry) throws JCoException;
	
	/**
	 * 通过复杂的手工报工form得到报工表列表
	 * 手工form以一个销售订单统计
	 * @param reportByHandForm
	 * @return
	 */
	List<ReportYield> getReportYieldListByHandForm(ReportByHandForm reportByHandForm);
	
	/**
	 * 循环报工
	 * @param reportYieldList
	 * @throws JCoException
	 */
	List<ReportYield> reportMoreYields(List<ReportYield> reportYieldList) throws JCoException;
	
	/**
	 * 报工列表查看功能
	 * @param page
	 * @param keywords 搜索关键词
	 * @return
	 */
	List<ReportYield> getReportYieldByPage(Page page,String orderByClause,String keywords);
	
	/**
	 * 根据主键查报工记录
	 * @param id
	 * @return
	 */
	ReportYield selectReportYieldById(Long id);
	
	/**
	 * 插入一条冲销记录
	 */
	ReportYield cancelReportYield(ReportYield reportYield) throws Exception;
	
	/**
	 * 获取给生产订单的所有报工单，包括报工和冲销两类
	 * @param productOrderId
	 * @return
	 */
	List<ReportYield> getALLReportYieldByProductOrderId(String productOrderId);
	
	/**
	 * 只获取报工信息列表
	 * @param productOrderId
	 * @return
	 */
	List<ReportYield> getAReportYieldByProductOrderId(String productOrderId);
	
	/**
	 * 只获取消息信息列表
	 * @param productOrderId
	 * @return
	 */
	List<ReportYield> getBReportYieldByProductOrderId(String productOrderId);
	
	void updateFinishAndWasteTotal(ReportYield reportYielded);

	int reportByStockAssignmentView(StockAssignmentView sav);

}

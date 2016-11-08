package com.eliteams.quick4j.web.service.impl;

import com.eliteams.quick4j.core.entity.ReportException;
import com.eliteams.quick4j.core.feature.factory.SapConn;
import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.core.util.OrderUtil;
import com.eliteams.quick4j.web.dao.AssignmentMapper;
import com.eliteams.quick4j.web.dao.ReportYieldMapper;
import com.eliteams.quick4j.web.dao.SapOrderMapper;
import com.eliteams.quick4j.web.dao.StockMapper;
import com.eliteams.quick4j.web.factory.ReportYieldFactory;
import com.eliteams.quick4j.web.form.ReportByHandForm;
import com.eliteams.quick4j.web.model.ReportYield;
import com.eliteams.quick4j.web.model.SapOrder;
import com.eliteams.quick4j.web.model.Stock;
import com.eliteams.quick4j.web.model.StockAssignmentView;
import com.eliteams.quick4j.web.service.ReportYieldService;
import com.eliteams.quick4j.web.service.SapOrderService;
import com.eliteams.quick4j.web.service.StockService;
import com.sap.conn.jco.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class ReportYieldServiceImp implements ReportYieldService {
	
	private static Logger log = Logger.getLogger(ReportYieldServiceImp.class); // 初始化日志对象

	@Resource
	private ReportYieldMapper reportYieldMapper;
	
	@Resource
	private ReportYieldFactory reportYieldFactory;
	
	@Resource
	private SapOrderService sapOrderService;
	
	@Resource
	private SapOrderMapper sapOrderMapper;
	
	@Resource
	private StockService stockService;

	@Resource
	private StockMapper stockMapper;

	@Resource
	private AssignmentMapper assignmentMapper;
	
	@Override
	public ReportYield reportCurrentYield(ReportYield ry) throws JCoException {
		
		log.info("向SAP报工--ReportYieldServiceImp.reporCurrentYield");
		JCoFunction function = null;
		JCoDestination destination = SapConn.connect();
		
		try {
			function = destination.getRepository().getFunction("ZBC_TOSAP_0020");//获取报工接口方法
			
			JCoStructure structure = function.getImportParameterList().getStructure("GT_INPUT");//得到传入数据结构
			
			JCoTable GT_OUTPUT = function.getTableParameterList().getTable("GT_OUTPUT");//传出属性表格
			
			//传入参数赋值
			structure.setValue("XID", ry.getMessageId());
			structure.setValue("FLAG", ry.getOperation());
			structure.setValue("RUECK", ry.getOperationFinishNo());
			structure.setValue("RMZHL", ry.getConfirmCount());
			structure.setValue("AUFNR", ry.getProductOrderId());
			structure.setValue("LTXA1", ry.getProcessDescribe());
			structure.setValue("LMNGA", ry.getCurrentYield());
			structure.setValue("XMNGA", ry.getCurrentWaste());
			structure.setValue("PRDDATE", new Date());
			structure.setValue("GRUNR", ry.getClasses());
			structure.setValue("BUDAT", ry.getAccountDate());
			
			function.execute(destination);//执行接口
			
			//接收回传参数
			ry.setMessageId(GT_OUTPUT.getString("XID"));
			ry.setOperationFinishNo(GT_OUTPUT.getInt("RUECK"));
			ry.setConfirmCount(GT_OUTPUT.getInt("RMZHL"));
			ry.setMessageType(GT_OUTPUT.getString("TYPE"));
			ry.setMessage(GT_OUTPUT.getString("MESSAGE"));
			
			
		} catch (JCoException e) {
			log.error("向SAP报工出错--ReportYieldServiceImp.reporCurrentYield",e);
			throw e;
		}
		
		return ry;
	}


	@Override
	public List<ReportYield> getReportYieldListByHandForm(ReportByHandForm reportByHandForm) throws ReportException {
		ReportYield[] reportYields = reportByHandForm.getReportYield();
//		Subject currentUser = SecurityUtils.getSubject();
//		String username = currentUser.getPrincipal().toString();
		List<ReportYield> ReportYieldList = new LinkedList<>();
		//先判断可以是否前道工序有问题，有则所有都不可以报工
		for(ReportYield reportYield:reportYields){
			if(!validateCanReportByHand(reportYield,reportYields)){
				return null;
			}
		}
		for(ReportYield reportYield:reportYields){
			reportYieldFactory.createHandReportYield(reportYield);
//			String messageId = sysSerialNumberService.generateSerialNumberByModelCode(REPORT_MESSAGE_ID);
//			reportYield.setMessageId(messageId);
//			reportYield.setReportUsername(username);
			reportYield.setSaleOrderId(reportByHandForm.getSaleOrderId());
//			reportYield.setOperation(REPORT_OPREATION);
			ReportYieldList.add(reportYield);
		}
		return ReportYieldList;
	}
	
	//判断该条订单能否被报工
	private boolean validateCanReportByHand(ReportYield ry,ReportYield[] reportYields) throws ReportException{
		String productId = ry.getProductOrderId();
		SapOrder sapOrder = sapOrderService.getSapOrderInfoById(productId);
		if(ry.getCurrentYield()>sapOrder.getTargetSum()-sapOrder.getFinishedTotal()+sapOrder.getWasteTotal()+sapOrder.getRelateScarp()){
			throw new ReportException("本条报工数量不正确");
		}
		/*if(ry.getCurrentWaste()>sapOrder.getFinishedTotal()-sapOrder.getWasteTotal()){
			throw new ReportException("本条报废数量不正确");
		}*/
		String simpleDescribe = OrderUtil.simplifyMaterialDescribe(ry.getMaterialDescribe());
		if(simpleDescribe.equals(OrderUtil.SPIN)||simpleDescribe.equals(OrderUtil.SPORK)||simpleDescribe.equals(OrderUtil.RIM)){
			return true;
		}else{
			//得到子订单列表
			List<String> childProductOrderIds = OrderUtil.getChildProductOrderIds(ry.getProductOrderId());
			//遍历子订单
			for(String childId :childProductOrderIds){
				SapOrder so = sapOrderService.getSapOrderInfoById(childId);
				//说明历史上该订单没有报工，应该检查这次的数组里面有没有。没有的话，直接return false
				//有的话，等于0 直接return false
				//没有的话，跳到下一次上层循环
				if(so!=null){//只有含有子订单时再做后续判断
					if(so.getFinishedTotal()<=0){
						if(isContain(childId, reportYields)){
							for(ReportYield currentRy :reportYields){
								if(currentRy.getProductOrderId().equals(childId)&&currentRy.getCurrentYield()==0){
									return false;
								}
							}
						}else{
							return false;
						}
					}
				}

			}
			return true;//所有子订单都遍历结束了
		}
	}
	
	/*private int childReportTotal(String productId,List<ReportYield> reportYields){
		List<String> childProductOrderIds = OrderUtil.getChildProductOrderIds(productId);
		for(String childId :childProductOrderIds){
			
		}
	}*/
	private boolean isContain(String productOrderId,ReportYield[] reportYields){
		for(ReportYield ry:reportYields){
			if(ry.getProductOrderId().equals(productOrderId)){
				return true;
			}
		}
		return false;
	}


	@Override
	public List<ReportYield> reportMoreYields(List<ReportYield> reportYieldList) throws JCoException {
		log.info("循环报工--ReportYieldServiceImp.reportByHand");
		List<ReportYield> reportYieldedList = new ArrayList<ReportYield>(5);
		for(ReportYield reportYield:reportYieldList){
			//报工完毕拥有回传参数的报工单
			ReportYield reportYielded = new ReportYield();
			reportYielded = reportCurrentYield(reportYield);
			reportYieldMapper.insert(reportYielded);
			//updateFinishAndWasteTotal(reportYielded);//更新sapOrder表完成数
			reportYieldedList.add(reportYielded);
		}
		return reportYieldedList;
	}


	@Override
	public List<ReportYield> getReportYieldByPage(Page page,String orderByClause, String keywords) {
		return reportYieldMapper.getReportYieldByPageAndKeywords(page,orderByClause, keywords);
	}

	/**
	 * 根据主键查报工记录
	 */
	@Override
	public ReportYield selectReportYieldById(Long id) {
		return reportYieldMapper.selectByPrimaryKey(id);
	}


	@Override
	public ReportYield cancelReportYield(ReportYield reportYield) throws Exception {
		log.info("进行冲销报工--ReportYieldServiceImp.cancelReportYield");
		ReportYield reportYielded = null;
		try {
			//设置冲销为
			reportYield.setOperation(CANCEL_OPREATION);
			reportYielded = reportCurrentYield(reportYield);
			reportYieldMapper.updateByPrimaryKey(reportYielded);

			//updateFinishAndWasteTotal(reportYielded);
			Stock stock = stockService.getStockByMaterialId(reportYielded.getMaterialId());
			stockService.updateByStockMaterialId(stock, reportYielded);
		} catch (JCoException e) {
			log.error("冲销报工报工sap接口出错--ReportYieldServiceImp.cancelReportYield", e);
		} catch (Exception e) {
			log.error("冲销报工内部代码出错--ReportYieldServiceImp.cancelReportYield", e);
			throw e;
		}
		return reportYielded;
	}

	/**
	 * 报工、冲销更新完成数
	 * @param reportYielded
	 */
	@Override
	public void updateFinishAndWasteTotal(ReportYield reportYielded) {
		try {
			if(SUC_MESSAGE_TYPE.equals(reportYielded.getMessageType())){
				log.info("更新完成量、报废量--ReportYieldServiceImp.updateFinishAndWasteTotal");
				String productOrderId = reportYielded.getProductOrderId();
				SapOrder sapOrder = sapOrderService.getSapOrderInfoById(productOrderId);
				if(REPORT_OPREATION.equals(reportYielded.getOperation())){
					//报工加上完成量和报废量
					sapOrder.setFinishedTotal(sapOrder.getFinishedTotal()+reportYielded.getCurrentYield());
					sapOrder.setWasteTotal(sapOrder.getWasteTotal()+reportYielded.getCurrentWaste());
				}else if(CANCEL_OPREATION.equals(reportYielded.getOperation())){
					//系统报工的冲销减去完成量和报废量，手动报工的冲销不改动
					sapOrder.setFinishedTotal(sapOrder.getFinishedTotal()-reportYielded.getCurrentYield());
					sapOrder.setWasteTotal(sapOrder.getWasteTotal()-reportYielded.getCurrentWaste());
				}
				sapOrderMapper.updateByPrimaryKey(sapOrder);
            }else {
				log.info("更新完成量、报废量失败，sap不是成功或警告");
			}
		} catch (Exception e) {
			log.error("更新完成量、报废量出错",e);
		}
	}


	@Override
	public List<ReportYield> getALLReportYieldByProductOrderId(String productOrderId) {
		return reportYieldMapper.getALLReportYieldByProductOrderId(productOrderId);
	}


	@Override
	public List<ReportYield> getAReportYieldByProductOrderId(String productOrderId) {
		return reportYieldMapper.getAReportYieldByProductOrderId(productOrderId);
	}


	@Override
	public List<ReportYield> getBReportYieldByProductOrderId(String productOrderId) {
		return reportYieldMapper.getBReportYieldByProductOrderId(productOrderId);
	}

	public int reportByStockAssignmentView(StockAssignmentView sav){
		SapOrder sapOrder = sapOrderService.getSapOrderInfoById(sav.getProductOrderId());
		//现有临时库存量
		long stockNum = stockService.getStockByMaterialId(sav.getMaterialId()).getStockNum();
		//计算未完成量:可报工量=目标量+报废量+关联报废量-完成量
		int requiredTotal = sapOrder.getTargetSum()+sapOrder.getWasteTotal()+sapOrder.getRelateScarp()-sapOrder.getFinishedTotal();
		if(requiredTotal>0&&stockNum>0){
			//增加首次报工时间
			if(null==sav.getFirstReportTime()){
				assignmentMapper.firstReport(sav.getAssignmentId());
			}
			int currentYield = (int) (requiredTotal<stockNum?requiredTotal:stockNum);
			//进行报工
			ReportYield reportYield = reportYieldForCurrentYield(sapOrder, currentYield);
			ReportYield reportYielded = null;
			try {
				reportYielded = reportCurrentYield(reportYield);
				reportYieldMapper.insert(reportYielded);
				//updateFinishAndWasteTotal(reportYielded);//更新sapOrder表完成数

			} catch (JCoException e) {
				log.error("通过接口对sap进行自动报工错误",e);
			}
			Stock stock = stockMapper.selectByMaterialId(sav.getMaterialId());

			try {
				stockService.updateByStockMaterialId(stock,reportYielded);
			} catch (Exception e) {
				log.error("自动报工时更新待分配量出错",e);
			}
			return currentYield;
		}else{
			return 0;
		}

	}

	private ReportYield reportYieldForCurrentYield(SapOrder sapOrder,int currentYield){
		ReportYield ry = reportYieldFactory.createAutoReportYield();
		ry.setProductOrderId(sapOrder.getProductOrderId());
		ry.setSaleOrderId(sapOrder.getSaleOrderId());
		ry.setSaleOrderRow(sapOrder.getSaleOrderRow());
		ry.setMaterialId(sapOrder.getMaterialId());
		ry.setMaterialDescribe(sapOrder.getMaterialDescribe());
		ry.setCurrentYield(currentYield);//当前报工量，取待分配量与所需报工量的较小值
		return ry;
	}

	
}

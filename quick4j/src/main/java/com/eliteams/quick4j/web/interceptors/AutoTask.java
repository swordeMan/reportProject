package com.eliteams.quick4j.web.interceptors;

import com.eliteams.quick4j.web.dao.ObtainYieldRecordMapper;
import com.eliteams.quick4j.web.dao.StockAssignmentViewMapper;
import com.eliteams.quick4j.web.dao.StockMapper;
import com.eliteams.quick4j.web.model.DShiftOutput;
import com.eliteams.quick4j.web.model.Stock;
import com.eliteams.quick4j.web.model.StockAssignmentView;
import com.eliteams.quick4j.web.service.ObtainYieldService;
import com.eliteams.quick4j.web.service.ReportYieldService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AutoTask {
	
	private static Logger log = Logger.getLogger(AutoTask.class); // 初始化日志对象
	
	@Resource
	private ObtainYieldService obtainYieldService;
	
	@Resource
	private StockAssignmentViewMapper stockAssignmentViewMapper;
	
	@Resource
	private ReportYieldService reportYieldService;
	
	@Resource
	private StockMapper stockMapper;
	
	@Resource
	private ObtainYieldRecordMapper obtainYieldRecord;
	
	public void obtainYield(){
		log.debug("获取待分配量开始");
		List<DShiftOutput> sqlserverList = obtainYieldService.getSqlserverList();
		obtainYieldService.obtainoYieldAndStockFromSqlserver(sqlserverList);
		log.debug("获取待分配量结束");
	}
	
	public void autoReport(){
		log.debug("开始进行自动报工");
		//任务下达与带分派量的视图列表
		List<StockAssignmentView> StockAssignmentViewList = stockAssignmentViewMapper.selectAll();
		for(StockAssignmentView sav :StockAssignmentViewList){
			int thisReport = 0;
			try {
				thisReport = reportYieldService.reportByStockAssignmentView(sav);
			} catch (Exception e) {
				log.error("自动报工失败"+sav,e);
			}
			log.info(sav+"当前报工完毕，数量为"+thisReport);
		}
	}
	/**
	 * 自动删除两月之前设备采集数据
	 */
	public void autodelet(){
		Calendar calM=Calendar.getInstance();      
		calM.add(Calendar.MONTH,-2); 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=calM.getTime();
		String dateString = formatter.format(date);
		 try {
				date=formatter.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		obtainYieldRecord.deleteByTwoMonth(date);
		log.debug("删除两月前设备采集记录");
	}
	
	//通过待分配量与任务下达的视图进行报工
	/*private int reportByStockAssignmentView(StockAssignmentView sav){
		SapOrder sapOrder = sapOrderService.getSapOrderInfoById(sav.getProductOrderId());
		//现有临时库存量
		long stockNum = stockService.getStockByMaterialId(sav.getMaterialId()).getStockNum();
		//计算未完成量:可报工量=目标量+报废量+关联报废量-完成量
		int requiredTotal = sapOrder.getTargetSum()+sapOrder.getWasteTotal()+sapOrder.getRelateScarp()-sapOrder.getFinishedTotal();
		if(requiredTotal>0&&stockNum>0){
			int currentYield = (int) (requiredTotal<stockNum?requiredTotal:stockNum);
			//进行报工
			ReportYield reportYield = ReportYieldForCurrentYield(sapOrder, currentYield);
			ReportYield reportYielded = null;
			try {
				reportYielded = reportYieldService.reportCurrentYield(reportYield);
				reportYieldMapper.insert(reportYielded);
				reportYieldService.updateFinishAndWasteTotal(reportYielded);//更新sapOrder表完成数
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
		
	}*/
	
	/**
	 * 
	 * @param sapOrder 生产订单
	 * @param currentYield 当前报工量
	 * @return 用生产订单和当前报工量组装成<B>还没有对sap接口进行报工</B>的报工类
	 */
	/*private ReportYield ReportYieldForCurrentYield(SapOrder sapOrder,int currentYield){
		ReportYield ry = new ReportYield();
		ry.setMessageId(sysSerialNumberService.generateSerialNumberByModelCode("BG"));
		ry.setOperation("A");//报工为A，冲销为B
		ry.setProductOrderId(sapOrder.getProductOrderId());
		ry.setSaleOrderId(sapOrder.getSaleOrderId());
		ry.setSaleOrderRow(sapOrder.getSaleOrderRow());
		ry.setMaterialId(sapOrder.getMaterialId());
		ry.setMaterialDescribe(sapOrder.getMaterialDescribe());
		ry.setCurrentYield(currentYield);//当前报工量，取待分配量与所需报工量的较小值
		ry.setReportUsername("system");
		ry.setAccountDate(new Date());
		return ry;
	}*/
	
	/**
	 * 
	 * @param sav
	 * @param currentYield
	 * @return 从视图到待分配量类的转化，set新待分配量
	 */
	/*private Stock stockAssignmentViewToStock(StockAssignmentView sav, int currentYield ){
		Stock stock = stockMapper.selectByMaterialId(sav.getMaterialId());
		stock.setStockNum(sav.getStockNum()-currentYield);
		return stock;
	}*/

}

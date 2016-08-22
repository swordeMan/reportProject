package com.eliteams.quick4j.web.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.eliteams.quick4j.core.feature.factory.SapConn;
import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.dao.BomOrderMapper;
import com.eliteams.quick4j.web.dao.ReportTimeMapper;
import com.eliteams.quick4j.web.dao.ReportYieldMapper;
import com.eliteams.quick4j.web.dao.SapOrderMapper;
import com.eliteams.quick4j.web.model.BomOrder;
import com.eliteams.quick4j.web.model.ReportTime;
import com.eliteams.quick4j.web.model.SapOrder;
import com.eliteams.quick4j.web.service.SapOrderService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

@Service
public class SapOrderServiceImpl implements SapOrderService {
	
	private static Logger log = Logger.getLogger(SapOrderServiceImpl.class); // 初始化日志对象

	@Resource
	private SapOrderMapper sapOrderMapper;
	
	@Resource
	private ReportYieldMapper reportYieldMapper;
	
	@Resource
	private BomOrderMapper bomOrderMapper;
	
	@Override
	public List<SapOrder> getProductOrderInfo(String factory, Date alterDate, Date alterTime) throws Exception {

		log.info("获取SAP订单信息--SapOrderServiceImpl.getProductOrderInfo");
		
//		SapOrder so = new SapOrder();

		String AUFNR = "";// 生产订单号
		String AUART = "";// 订单类型
		String KDAUF = "";// 销售订单
		String KDPOS = "";// 订单销售行
		String SORTL = "";// 客户简称
		String VERID = "";// 生产版本
		String MATNR = "";// 物料
		String MAKTX = "";// 物料描述
		Integer PSMNG = 0;// 数量
		Integer WEMNG = 0;// 完成数
		Integer PSAMG = 0;// 确认的废品
		String AMEIN = "";// 单位
		Date GSTRS = null;// 计划开始日期
		Date GLTRS = null;// 计划完成日期
		String LOEKZ = "";// 删除标记
		String STATUS = "";// 状态
		
		//bom table信息
		/*String RSNUM = "";//预留号
		String RSPOS = "";//预留项目
		Double BDMNG = 0.000;//需求数量
		String XLOEK = "";//删除标记
*/		 
		JCoFunction function = null;
		
		List<SapOrder> sapOrderList = new LinkedList<SapOrder>();
		
		// 连接sap，类似于连接数据库
		JCoDestination destination = SapConn.connect();
		try {
			// 调用ZBC_TOSAP_0010函数
			function = destination.getRepository().getFunction("ZBC_TOSAP_0010");
//			 将当前传入的值赋予各个参数
			
			function.getImportParameterList().setValue("WERKS", factory);
			function.getImportParameterList().setValue("AEDAT", alterDate);
			function.getImportParameterList().setValue("AEZEIT", alterTime);
			
			function.execute(destination);
			
			JCoTable GT_OUTPUT = function.getTableParameterList().getTable("GT_OUTPUT");//传出table
			
//			JCoTable GT_ZRESB = function.getTableParameterList().getTable("GT_ZRESB");//传出bom table
			for (int i = 0; i < GT_OUTPUT.getNumRows(); i++) {// 遍历Table
				SapOrder so = new SapOrder();
				GT_OUTPUT.setRow(i);// 将行指针指向特定的索引行
				AUFNR = GT_OUTPUT.getString("AUFNR");
				AUART = GT_OUTPUT.getString("AUART");
				KDAUF = GT_OUTPUT.getString("KDAUF");
				KDPOS = GT_OUTPUT.getString("KDPOS");
				SORTL = GT_OUTPUT.getString("SORTL");
				VERID = GT_OUTPUT.getString("VERID");
				MATNR = GT_OUTPUT.getString("MATNR");
				MAKTX = GT_OUTPUT.getString("MAKTX");
				PSMNG = GT_OUTPUT.getInt("PSMNG");
				WEMNG = GT_OUTPUT.getInt("WEMNG");
				PSAMG = GT_OUTPUT.getInt("PSAMG");
				AMEIN = GT_OUTPUT.getString("AMEIN");
				GSTRS = GT_OUTPUT.getDate("GSTRS");// 参数类型
				GLTRS = GT_OUTPUT.getDate("GLTRS");// 参数类型
				LOEKZ = GT_OUTPUT.getString("LOEKZ");
				STATUS = GT_OUTPUT.getString("STATUS");

				so.setDelRemark(LOEKZ);
				so.setFinishedTotal(WEMNG);
				so.setManufactureVersion(VERID);
				so.setMaterialId(MATNR);
				so.setMaterialDescribe(MAKTX);
				so.setPlanEndDate(GLTRS);
				so.setPlanStartDate(GSTRS);
				so.setProductOrderId(AUFNR);
				so.setProductOrderType(AUART);
				so.setSaleOrderRow(KDPOS);
				so.setSaleOrderId(KDAUF);
				so.setState(STATUS);
				so.setTargetSum(PSMNG);
				so.setUnit(AMEIN);
				so.setWasteTotal(PSAMG);
				so.setUserSimpleName(SORTL);

				sapOrderMapper.insert(so);
				sapOrderList.add(so);
				
			}
			
			/*for (int i = 0; i < GT_ZRESB.getNumRows(); i++) {// 遍历bom Table
				BomOrder bo = new BomOrder();
				GT_ZRESB.setRow(i);// 将行指针指向特定的索引行
				AUFNR = GT_ZRESB.getString("AUFNR");
				RSNUM = GT_ZRESB.getString("RSNUM");
				RSPOS = GT_ZRESB.getString("RSPOS");
				MATNR = GT_ZRESB.getString("MATNR");
				BDMNG = GT_ZRESB.getDouble("BDMNG");
				XLOEK = GT_ZRESB.getString("XLOEK");
				
				bo.setProductOrderId(AUFNR);
				bo.setPreserveId(RSNUM);
				bo.setPreserveProject(RSPOS);
				bo.setMaterialId(MATNR);
				bo.setTargetSum(BDMNG);
				bo.setDelRemark(XLOEK);
				
				bomOrderMapper.insert(bo);
			}*/
			log.info("获取SAP订单信息完毕--SapOrderServiceImpl.getProductOrderInfo");
			return sapOrderList;

		} catch (Exception e) {
			log.error("获取SAP订单信息出错--SapOrderServiceImpl.getProductOrderInfo"+e);
			throw e;
		}
	}

	
	/**
	 * 
	 * 获取所有生产订单信息
	 */
	@Override
	public List<SapOrder> selectAllSapOrder(Page page) {
		return sapOrderMapper.getSapOrderByPage(page);
	}
	/**
	 * 
	 * 根据生产订单号查单条生产订单所有信息
	 */
	@Override
	public SapOrder getSapOrderInfoById(String productOrderId) {
		SapOrder sapOrder = sapOrderMapper.selectByProductOrderId(productOrderId);
		return sapOrder;
	}

	@Override
	public List<SapOrder> getSapOrderListBySaleOrderId(String saleOrderId) {
		return sapOrderMapper.getSapOrderListBySaleOrderId(saleOrderId);
	}


	@Override
	public List<SapOrder> getUserInfoByPage(Page page, String orderByClause,String keywords) {
		return sapOrderMapper.getSapOrderByPageAndKeywords(page,orderByClause, keywords);
	}

	/**
     * 查询所有未启动的生产订单信息
     * @return
     */
	@Override
	public List<SapOrder> selectSapOrderList() {
		return sapOrderMapper.selectSapOrderList();
	}

	/**
	 * 更新生产订单的启动状态为已启动---1
	 */
	@Override
	public void updateSapOrderStateMark(String productOrderId) {
		sapOrderMapper.updateSapOrderStateMark(productOrderId);
	}
	
	/**
	 * 更新生产订单的启动状态为未启动---null
	 */
	@Override
	public void updateSapOrderStateMarkToUnAllocate(String productOrderId) {
		sapOrderMapper.updateSapOrderStateMarkToUnAllocate(productOrderId);
	}
	
	/**
	 * 更新生产订单的下达状态
	 */
	@Override
	public void updateSapOrderStateMarkToAllocate(String productOrderId) {
		sapOrderMapper.updateSapOrderStateMarkToAllocate(productOrderId);
	}
	
	/**
	 * 查询所有未启动的生产订单中的物料编码
	 */
	public List<String> getMaterialIdFromUnstart(){
		return sapOrderMapper.getMaterialIdFromUnstart();
	}


	@Override
	public String getBySaleOrderIdAndSimpleDescribe(String saleOrderId, String saleOrderRow,String simpleDescribe) {
		return sapOrderMapper.getBySaleOrderIdAndSimpleDescribe(saleOrderId,saleOrderRow,simpleDescribe).getProductOrderId();
	}


}

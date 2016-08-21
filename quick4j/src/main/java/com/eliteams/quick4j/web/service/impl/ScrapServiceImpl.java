package com.eliteams.quick4j.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.dao.BomOrderMapper;
import com.eliteams.quick4j.web.dao.ReportYieldMapper;
import com.eliteams.quick4j.web.dao.SapOrderMapper;
import com.eliteams.quick4j.web.dao.ScrapMapper;
import com.eliteams.quick4j.web.dao.ScrapReasonRelationMapper;
import com.eliteams.quick4j.web.dao.ScrapViewMapper;
import com.eliteams.quick4j.web.form.CreateScrapForm;
import com.eliteams.quick4j.web.model.OrderNode;
import com.eliteams.quick4j.web.model.ReportYield;
import com.eliteams.quick4j.web.model.ReworkView;
import com.eliteams.quick4j.web.model.SapOrder;
import com.eliteams.quick4j.web.model.Scrap;
import com.eliteams.quick4j.web.model.ScrapReasonRelation;
import com.eliteams.quick4j.web.model.ScrapView;
import com.eliteams.quick4j.web.service.PDFReportService;
import com.eliteams.quick4j.web.service.ReportYieldService;
import com.eliteams.quick4j.web.service.SapOrderService;
import com.eliteams.quick4j.web.service.ScrapService;
import com.eliteams.quick4j.web.service.SysSerialNumberService;
import com.eliteams.quick4j.web.service.UserInfoService;
import com.sap.conn.jco.JCoException;

/*
 * 
 * 报废接口实现类
 */
@Service
public class ScrapServiceImpl implements ScrapService {
	
	private static Logger log = Logger.getLogger(ScrapServiceImpl.class); // 初始化日志对象

	@Resource
	private ScrapMapper scrapMapper;

	@Resource
	private ScrapReasonRelationMapper scrapReasonRelationMapper;

	@Resource
	private SapOrderMapper sapOrderMapper;

	@Resource
	private BomOrderMapper bomOrderMapper;
	
	@Resource
	private SapOrderService sapOrderService;
	
	@Resource
	private SysSerialNumberService sysSerialNumberService;
	
	@Resource
	private ReportYieldService reportYieldService;
	
	@Resource
	private ReportYieldMapper reportYieldMapper;
	
	@Resource
	private UserInfoService userInfoService;
	@Resource
	private ScrapViewMapper scrapViewMapper;
	@Resource
	private PDFReportService pdfReportService;
	
	// List<OrderNode> orderNodeList;
	/**
	 * 查询所有报废列表
	 */
	@Override
	public List<Scrap> slelectAllScrap() {
		List<Scrap> scrapList = new ArrayList<Scrap>();
		scrapList = scrapMapper.selectAll();
		return scrapList;
	}
	/**
	 * 调用cmd打印报废单
	 * @return
	 */
	public boolean printScrapPdf(String scrapId,int iNum){  
		String iNums=String.valueOf(iNum);
        try{  
        	
        	String  fileName=scrapId+iNums;
          //  Runtime.getRuntime().exec("cmd.exe /c\"D:\\福昕pdf\\Foxit Reader\\FoxitReader.exe\"/p" +"D:\\text.pdf"); 
            Runtime.getRuntime().exec("D:\\福昕pdf\\Foxit Reader\\FoxitReader.exe /p D:\\scrap\\"+fileName+".pdf ");
        //D:\福昕pdf\Foxit Reader\FoxitReader.exe /p "D:\text.pdf"
            return true;  
        }catch(Exception e){  
            e.printStackTrace();  
            return false;  
        }  
    } 
	
	
	/**
	 * 向报废记录表中插入报废记录；向scrap_reason_relation关联表中插入记录
	 */
	@Override
	public void insertScrap(CreateScrapForm createScrapForm) {
		log.info("插入开始");
		Scrap scrap = createScrapForm.getScrap();
		String scrapId = sysSerialNumberService.generateSerialNumberByModelCode("BF");
		if(scrap!=null){
			log.info("scrap!=null");
			scrap.setScrapId(scrapId);
			//工号转用户名
			scrap.setInspector(userInfoService.workNoToUserName(scrap.getInspector()));
		}else{
			log.info("scrap==null");
		}
		log.info("填单人"+userInfoService.workNoToUserName(scrap.getInspector()));
		scrapMapper.insert(scrap);
		
		log.info("插入scrap成功");
		/*List<Scrap> relatedScrapList = getRelatedScrap(scrap);
		for(Scrap sp:relatedScrapList){
			scrapMapper.insert(sp);
			if(sp.getMaterialId().equals(scrap.getMaterialId())){
				scrapId = sp.getScrapId();
				continue;
			}
			ScrapReasonRelation srr = new ScrapReasonRelation();
			srr.setScrapId(sp.getScrapId());
			srr.setScrapNum(sp.getScrapNum());
			srr.setsReasonId("1");
			scrapReasonRelationMapper.insert(srr);
		}*/

		ScrapReasonRelation[] scrapReasonRelations = createScrapForm.getScrapReasonRelation();
		for (ScrapReasonRelation scrapReasonRelation : scrapReasonRelations) {
			scrapReasonRelation.setScrapId(scrapId);
//			scrapReasonRelation.setScrapId(sysSerialNumberService.generateSerialNumberByModelCode("BF"));
			scrapReasonRelationMapper.insert(scrapReasonRelation);
		}
		 //打印pdf
		 printScrapList(scrap.getScrapId());
	}
	/**
	 * 打印报废单pdf
	 * @param scrapId
	 */
	public void printScrapList(String scrapId){
		 //查询reworkView视图
		 List<ScrapView> scrapViewList2= scrapViewMapper.selectById(scrapId);
		//创建新的list
		 List<ScrapView> scrapViewList=new ArrayList<>();
		
		 for (int i = 0; i < scrapViewList2.size(); i++) {
			    if((i+1)%6!=0){
				 scrapViewList.add(scrapViewList2.get(i));
				}
				if((i+1)%6==0){
					scrapViewList.add(scrapViewList2.get(i));
					//每过6个导出一个pdf并打印
					 pdfReportService.generateScrapPDF(scrapViewList,i);
					 printScrapPdf(scrapId,i);
					 //清除列表
					 scrapViewList.clear();	
				}
				if((i+1)==scrapViewList2.size()){
					 pdfReportService.generateScrapPDF(scrapViewList,i);
					 printScrapPdf(scrapId,i);
				}
		}
		 //导出pdf
		// pdfReportService.generateScrapPDF(scrapViewList);
		 //打印pdf
		// printScrapPdf(scrapId);
	};
	
	
	/**
	 * 向报废记录表中插入报废记录；向scrap_reason_relation关联表中插入记录
	 */
	@Override
	public void insertScrap2(CreateScrapForm createScrapForm) {
		Scrap scrap = createScrapForm.getScrap();
		String scrapId = sysSerialNumberService.generateSerialNumberByModelCode("BF");
		if(scrap!=null){
			scrap.setScrapId(scrapId);
		}else{
			log.info("scrap==null");
		}
		scrapMapper.insert(scrap);
		log.info("插入scrap成功");
		
		ScrapReasonRelation[] scrapReasonRelations = createScrapForm.getScrapReasonRelation();
		for (ScrapReasonRelation scrapReasonRelation : scrapReasonRelations) {
			scrapReasonRelation.setScrapId(scrapId);
			scrapReasonRelationMapper.insert(scrapReasonRelation);
		}
		 //打印pdf
		 printScrapList(scrap.getScrapId());
	}
	
	//得到关联报废列表,不包含自身
	public List<Scrap> getRelatedScrap(Scrap scrap){
		List<Scrap> relatedScrapList = new ArrayList<Scrap>();
		List<SapOrder> sapOrderList = sapOrderMapper.getSapOrderListBySaleOrderIdAndSaleOrderRow(scrap.getSaleOrderId(),scrap.getSaleOrderRow());
		String materialName = materialDescribeToName(scrap.getMaterialDescribe());
		if(materialName.equals("钢圈")){
			for (SapOrder sapOrder : sapOrderList) {
				if(sapOrder.getMaterialDescribe().equals(scrap.getMaterialDescribe())){
					continue;
				}
				Scrap sp = new Scrap();
				sp.setClasses(scrap.getClasses());
				sp.setCreateTime(scrap.getCreateTime());
				sp.setInspector(scrap.getInspector());
				sp.setMaterialDescribe(sapOrder.getMaterialDescribe());
				sp.setMaterialId(sapOrder.getMaterialId());
				sp.setProductOrderId(sapOrder.getProductOrderId());
				sp.setScrapNum(scrap.getScrapNum());
				sp.setScrapTime(scrap.getScrapTime());
				sp.setProductionProcess(scrap.getProductionProcess());
//				sp.setScrapId(sysSerialNumberService.generateSerialNumberByModelCode("BF"));
				//sp.setScrapId("BF");
				relatedScrapList.add(sp);
			}
		}else if(materialName.equals("合成")){
			for(SapOrder sapOrder : sapOrderList){
				String s = materialDescribeToName(sapOrder.getMaterialDescribe());
				if(!s.equals("钢圈")){
					if(sapOrder.getMaterialDescribe().equals(scrap.getMaterialDescribe())){
						continue;
					}
					Scrap sp = new Scrap();
					sp.setClasses(scrap.getClasses());
					sp.setCreateTime(scrap.getCreateTime());
					sp.setInspector(scrap.getInspector());
					sp.setMaterialDescribe(sapOrder.getMaterialDescribe());
					sp.setMaterialId(sapOrder.getMaterialId());
					sp.setProductOrderId(sapOrder.getProductOrderId());
					sp.setScrapNum(scrap.getScrapNum());
					sp.setScrapTime(scrap.getScrapTime());
					sp.setProductionProcess(scrap.getProductionProcess());
//					sp.setScrapId(sysSerialNumberService.generateSerialNumberByModelCode("BF"));
					//sp.setScrapId("BF");
					relatedScrapList.add(sp);
				}
			}
		}
		return relatedScrapList;
	}

	// 截取物料描述的前两个字符串
	public String materialDescribeToName(String materialDescribe) {
		String s = materialDescribe.substring(0, 2);
		return s;
	}

	// 根据销售订单号查询该销售订单下的所有生产订单
	@Override
	public List<OrderNode> selectSapOrderBySaleOrderId(String saleOrderId) {
		List<OrderNode> sapList = sapOrderMapper.getSapOrderBySaleOrderId(saleOrderId);
		return sapList;
	}

	// 根据生产订单号去bom_order中查询记录
	@Override
	public OrderNode selectFromBomOrderByProductOrderId(String productOrderId) {
		OrderNode orderNode = bomOrderMapper.getBomOrderByProductOrderId(productOrderId);
		return orderNode;
	}

	// 拿sapList组装第二个list
	public List<OrderNode> getOrderNodeListBySapList(List<OrderNode> sapList) {
		List<OrderNode> resultList = new ArrayList<OrderNode>();
		for (OrderNode on : sapList) {
			String productOrderId = on.getProductOrderId();
			resultList.add(selectFromBomOrderByProductOrderId(productOrderId));
		}
		return resultList;
	}
	
	//根据page和关键字查询报废列表
	@Override
	public List<Scrap> getScrapInfoByPage(Page page, String orderByClause,String keywords) {
		return scrapMapper.getUserInfoByPageAndKeywords(page, orderByClause,keywords);
	}
	
	//根据page和关键字查询报废列表
	@Override
	public List<Scrap> getExceptionScrapByPageAndKeywords(Page page, String orderByClause,String keywords) {
		return scrapMapper.getExceptionScrapByPageAndKeywords(page, orderByClause,keywords);
	}
	
	//根据id查询单条报废记录
	@Override
	public Scrap getScrapInfoById(Long id) {
		return scrapMapper.selectByPrimaryKey(id);
	}
	
	//根据scrapId查询单条报废记录
	@Override
	public Scrap selectByscrapId(String scrapId) {
		return scrapMapper.selectByscrapId(scrapId);
	}

	@Override
	public List<Scrap> getScrapListByProductOrderId(String productOrderId) {
		return scrapMapper.selectScrapListByProductId(productOrderId);
	}
	//报废类转报工类
	@Override
	public ReportYield switchScrapToReportYield(Scrap scrap) {
		
		String productOrderId = scrap.getProductOrderId();
		SapOrder sapOrder = sapOrderService.getSapOrderInfoById(productOrderId);
		ReportYield reportYield = new ReportYield();
		reportYield.setMessageId(sysSerialNumberService.generateSerialNumberByModelCode("BG"));
		reportYield.setOperation("C");
		reportYield.setProductOrderId(productOrderId);
		reportYield.setCurrentYield(0);
		reportYield.setCurrentWaste(scrap.getScrapNum());
		reportYield.setMaterialDescribe(sapOrder.getMaterialDescribe());
		reportYield.setAccountDate(scrap.getCreateTime());
		reportYield.setReportUsername(scrap.getInspector());
		reportYield.setSaleOrderId(sapOrder.getSaleOrderId());
		
		return reportYield;
	}
	
	//自身报废报工加更新sapOrder中报废量加更新报废表中sap消息文本
	public void yieldAndUpdate(Scrap scrap){
		
		ReportYield ry = switchScrapToReportYield(scrap);
		try {
			ReportYield reportYielded = reportYieldService.reportCurrentYield(ry);
			String messageType = reportYielded.getMessageType();
			String message = reportYielded.getMessage();
			
			scrap.setMessageType(messageType);
			scrap.setMessage(message);
			scrapMapper.updateByScrap(scrap);
			
			//reportYieldMapper.insert(reportYielded);
			//更新sapOrder中相应订单的报废量
			updateFinishAndWasteTotalByScrapSelf(reportYielded);
			/*if(!messageType.equals("S")){
				return false;
			}*/
		} catch (JCoException e) {
			e.printStackTrace();
		}
	}
	
	//sap写入异常的报废重新报工
	public boolean yield(Scrap scrap) {

		ReportYield ry = switchScrapToReportYield(scrap);
		try {
			ReportYield reportYielded = reportYieldService.reportCurrentYield(ry);
			String messageType = reportYielded.getMessageType();
			String message = reportYielded.getMessage();

			scrap.setMessageType(messageType);
			scrap.setMessage(message);
			scrapMapper.updateByScrap(scrap);

			if (!messageType.equals("S")) {
				return false;
			}
		} catch (JCoException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	//关联报废更新sapOrder中的关联报废量
	public void updateRelatedScrapNum(Scrap scrap){
		
		String productOrderId = scrap.getProductOrderId();
		SapOrder sapOrder = sapOrderService.getSapOrderInfoById(productOrderId);
		sapOrder.setRelateScarp(sapOrder.getRelateScarp()+scrap.getScrapNum());
		sapOrderMapper.updateByPrimaryKey(sapOrder);
		
	}
	//自身报废更新sapOrder中的报废量和完成量
	@Override
	public void updateFinishAndWasteTotalByScrapSelf(ReportYield reportYielded) {
		String productOrderId = reportYielded.getProductOrderId();
		SapOrder sapOrder = sapOrderService.getSapOrderInfoById(productOrderId);
		if ("C".equals(reportYielded.getOperation())) {
			// 报工加上完成量和报废量
			sapOrder.setFinishedTotal(sapOrder.getFinishedTotal() + reportYielded.getCurrentYield());
			sapOrder.setWasteTotal(sapOrder.getWasteTotal() + reportYielded.getCurrentWaste());
			sapOrderMapper.updateByPrimaryKey(sapOrder);
		} else {
			log.info("自身报废报工更新完成量、报废量失败,失败原因" + reportYielded.getMessage());
		}
	}

}

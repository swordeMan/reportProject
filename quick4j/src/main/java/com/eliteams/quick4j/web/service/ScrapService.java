package com.eliteams.quick4j.web.service;

import java.util.List;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.form.CreateScrapForm;
import com.eliteams.quick4j.web.model.OrderNode;
import com.eliteams.quick4j.web.model.ReportYield;
import com.eliteams.quick4j.web.model.Scrap;
import com.eliteams.quick4j.web.model.ScrapReasonRelation;

/*
 * 
 * 报废单接口
 */
public interface ScrapService {
	
	List<Scrap> slelectAllScrap();//查询所有报废单信息
	
	//根据page和关键字查询报废列表
	List<Scrap> getScrapInfoByPage(Page page,String orderByClause,String keywords);
	
	//根据page和关键字查询异常报废列表
	List<Scrap> getExceptionScrapByPageAndKeywords(Page page,String orderByClause,String keywords);
	
	void insertScrap(CreateScrapForm createScrapForm);//编制新的报废单，向两个表中插入，方法一
	
	void insertScrap2(CreateScrapForm createScrapForm);//编制新的报废单，向两个表中插入，方法二
	
	//根据销售订单号查询该销售订单下的所有生产订单
	List<OrderNode> selectSapOrderBySaleOrderId(String saleOrderId);
	
	//根据生产订单号去bom_order中查询组件物料
	OrderNode selectFromBomOrderByProductOrderId(String productOrderId);
	
	//根据id查询单条报废记录
	Scrap getScrapInfoById(Long id);
	
	//根据报废单号查询单条报废记录
	Scrap selectByscrapId(String scrapId);
	
	List<Scrap> getScrapListByProductOrderId(String productOrderId);
	
	//报废类转报工类
	ReportYield switchScrapToReportYield(Scrap scrap);
	
	//自身报废报工加更新sapOrder中报废量
	void yieldAndUpdate(Scrap scrap);
	
	//sap写入异常的报废重新报工
	boolean yield(Scrap scrap);
	
	//关联报废更新sapOrder中的关联报废量
	void updateRelatedScrapNum(Scrap scrap);
	
	//得到关联报废列表,不包含自身
	List<Scrap> getRelatedScrap(Scrap scrap);
	
	//打印报废pdf
	public void printScrapList(String scrapId,String productionProcess);
	
	//自身报废报工更新sapOrder中报废量和完成量
	void updateFinishAndWasteTotalByScrapSelf(ReportYield reportYielded);
}

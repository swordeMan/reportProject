package com.eliteams.quick4j.web.service;

import java.util.List;
import java.util.Map;

import com.eliteams.quick4j.web.model.DeviceFinishedView;
import com.eliteams.quick4j.web.model.ScrapView;

public interface ScrapAnalysisService {
	
	//分组查询各报废原因和数量
	List<ScrapView> getScrapDetailsByScrapView(ScrapView scrapView);
	
	//循环ScrapViewList得到报废总量
	Integer getScrapTotal(List<ScrapView> list);
	
	//循环ScrapViewList得到各原因报废数量所占比例list
	List<Double> getRatio(List<ScrapView> list,Integer wasteSum);
	
	//循环ratioList得到累加百分比
	List<Double> getAddRatio(List<Double> list);
	
	//根据年份和组别查询每月报废数量
	List<Integer> getScrapTotalByMonth(ScrapView scrapView);
	
	//根据工序显示对应的目标值
	Double getObjective(String productionProcess);
	
	//根据年份和组别查询每月生产数量
	List<Integer> getFinishedTotalByMonth(DeviceFinishedView deviceFinishedView);
	
	//循环两个list得到报废率
	List<Double> getScrapRatio(List<Integer> list1,List<Integer> list2);
	
	//循环报废率list得到合格率list
	List<Double> getPassRatio(List<Double> list);
	
	//List<ScrapView> getScrapTotalByMonth(ScrapView scrapView);
	
	//List<Map<Object,Object>>组新的Map
	//Map<Object,Object> createNewMap(List<Map<Object,Object>> list);
	
	//未查到的月份，报废数设为0，组成新的List
	//List<ScrapView> createNewList(ScrapView scrapView);
}

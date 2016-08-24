package com.eliteams.quick4j.web.service;

import java.util.List;

import com.eliteams.quick4j.web.model.ReworkView;
import com.eliteams.quick4j.web.model.ScrapView;

public interface ReworkAnalysisService {
	
	//根据月份分组查询返修原因和数量
	List<ReworkView> getReworkDetailsByReworkView(ReworkView reworkView);
	
	//循环reworkViewList得到返修总量
	Integer getReworkTotal(List<ReworkView> list);
	
	//循环reworkViewList得到各原因返修数量所占比例list
	List<Double> getRatio(List<ReworkView> list,Integer reworkSum);
	
	//循环reworkViewList得到累加百分比
	List<Double> getAddRatio(List<Double> list);
}

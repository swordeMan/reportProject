package com.eliteams.quick4j.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eliteams.quick4j.web.dao.ReworkViewMapper;
import com.eliteams.quick4j.web.model.ReworkView;
import com.eliteams.quick4j.web.model.ScrapView;
import com.eliteams.quick4j.web.service.ReworkAnalysisService;
@Service
public class ReworkAnalysisServiceImpl implements ReworkAnalysisService {
	
	@Resource
	private ReworkViewMapper reworkViewMapper;
	
	@Resource
	private ScrapAnalysisServiceImpl scrapAnalysisServiceImpl;
	
	@Override
	public List<ReworkView> getReworkDetailsByReworkView(ReworkView reworkView) {
		return reworkViewMapper.getReworkDetailsByReworkView(reworkView);
	}

	@Override
	public Integer getReworkTotal(List<ReworkView> list) {
		Integer a= 0;
		for(ReworkView reworkView:list){
			a+=reworkView.getReworkNum();
		}
		return a;
	}

	@Override
	public List<Double> getRatio(List<ReworkView> list, Integer reworkSum) {
		List<Double> ratioList = new ArrayList<Double>();
		for(ReworkView reworkView:list){
			double ratio = (double)reworkView.getReworkNum()/reworkSum;
			double ra = scrapAnalysisServiceImpl.setScale(ratio);
			ratioList.add(ra);
		}
		return ratioList;
	}

	@Override
	public List<Double> getAddRatio(List<Double> list) {
		List<Double> ratioAddList = new ArrayList<Double>();
		double a=0;
		for(double i:list){
			a=a+i;
			ratioAddList.add(scrapAnalysisServiceImpl.setScale(a));
		}
		return ratioAddList;
	}

}

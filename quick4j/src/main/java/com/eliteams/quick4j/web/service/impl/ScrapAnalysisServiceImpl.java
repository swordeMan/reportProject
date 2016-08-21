package com.eliteams.quick4j.web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eliteams.quick4j.web.dao.DeviceFinishedViewMapper;
import com.eliteams.quick4j.web.dao.ScrapViewMapper;
import com.eliteams.quick4j.web.model.DeviceFinishedView;
import com.eliteams.quick4j.web.model.ScrapView;
import com.eliteams.quick4j.web.service.ScrapAnalysisService;

@Service
public class ScrapAnalysisServiceImpl implements ScrapAnalysisService {
	@Resource
	private ScrapViewMapper scrapViewMapper;
	
	@Resource
	private DeviceFinishedViewMapper deviceFinishedViewMapper;
	
	@Override
	public List<ScrapView> getScrapDetailsByScrapView(ScrapView scrapView) {
		return scrapViewMapper.getScrapDetailsByScrapView(scrapView);
	}

	@Override
	public Integer getScrapTotal(List<ScrapView> list) {
		Integer a= 0;
		for(ScrapView scrapView:list){
			a+=scrapView.getScrapNum();
		}
		return a;
	}

	@Override
	public List<Double> getRatio(List<ScrapView> list, Integer wasteSum) {
		List<Double> ratioList = new ArrayList<Double>();
		for(ScrapView scrapView:list){
			double ratio = (double)scrapView.getScrapNum()/wasteSum;
			/*DecimalFormat df = new DecimalFormat("0.00");
			String ra = df.format(ratio);*/
			double ra = setScale(ratio);
			ratioList.add(ra);
		}
		return ratioList;
	}
	/**
	 * double类型四舍五入保留三位小数
	 * @param f
	 * @return
	 */
	private double setScale(double f){
		BigDecimal b = new BigDecimal(f);  
		double f1 = b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	@Override
	public List<Double> getAddRatio(List<Double> list) {
		List<Double> ratioAddList = new ArrayList<Double>();
		double a=0;
		for(double i:list){
			a=a+i;
			ratioAddList.add(setScale(a));
		}
		return ratioAddList;
	}

	@Override
	public List<Integer> getScrapTotalByMonth(ScrapView scrapView) {
		Map<String, Map> resultMap = scrapViewMapper.getScrapTotalByMonth(scrapView);
		List<Integer> scrapSumList = new ArrayList<Integer>(12);
		for (Integer i = 0; i < 12; i++) {
			Map m = resultMap.get(i.toString());
			if(m!=null){
				int scrapNum = ((BigDecimal)m.get("scrapNum")).intValue();
				scrapSumList.add(i, scrapNum);
			}else{
				scrapSumList.add(i, 0);
			}
		}
		return scrapSumList;
		
	}

	@Override
	public Double getObjective(String productionProcess) {
		Double objective = (double) 0;
		String s = productionProcess.substring(2, 4);
		if(s.equals("轮辐")){
			objective = 0.006;
		}else if(s.equals("轮辋")){
			objective = 0.008;
		}else if(s.equals("合成")){
			objective = 0.002;
		}else{
			objective = 0.001;
		}
		return objective;
	}

	@Override
	public List<Integer> getFinishedTotalByMonth(DeviceFinishedView deviceFinishedView) {
		Map<String, Map> resultMap = deviceFinishedViewMapper.getFinishedTotalByMonth(deviceFinishedView);
		List<Integer> scrapSumList = new ArrayList<Integer>(12);
		for (Integer i = 0; i < 12; i++) {
			Map m = resultMap.get(i.toString());
			if(m!=null){
				int finishedNum = ((BigDecimal)m.get("finishedNum")).intValue();
				scrapSumList.add(i, finishedNum);
			}else{
				scrapSumList.add(i, 0);
			}
		}
		return scrapSumList;
	}

	@Override
	public List<Double> getScrapRatio(List<Integer> list1, List<Integer> list2) {
		List<Double> scrapRatioList = new ArrayList<Double>();
		for(int i=0;i<12;i++){
			if(list2.get(i)==0){
				double ra = 0;
				scrapRatioList.add(ra);
			}else{
				double ratio = (double)list1.get(i)/list2.get(i);
				scrapRatioList.add(setScale(ratio));
			}
		}
		return scrapRatioList;
	}

	@Override
	public List<Double> getPassRatio(List<Double> list) {
		List<Double> passRateList = new ArrayList<Double>();
		for(double i:list){
			double a = 1-i;
			passRateList.add(setScale(a));
		}
		return passRateList;
	}
	
	
	
	/*@Override
	public List<ScrapView> getScrapTotalByMonth(ScrapView scrapView) {
		return scrapViewMapper.getScrapTotalByMonth(scrapView);
	}*/
	//组建新的list,含所有月份
	/*public List<ScrapView> createNewList(ScrapView scrapView) {
		List<ScrapView> list = getScrapTotalByMonth(scrapView);
		//List<ScrapView> monthlyScrapList = new ArrayList<ScrapView>();
		List<ScrapView> monthlyScrapList = SetUniqueList.decorate(new ArrayList<ScrapView>());
			for(int i=1;i<13;i++){
				for(ScrapView sc:list){
					if(sc.getMonth()==i){
						monthlyScrapList.add(sc);
					}else{
						ScrapView sv = new ScrapView();
						sv.setMonth(i);
						sv.setScrapNum(0);
						monthlyScrapList.add(sv);
					}
				}
			}
		return monthlyScrapList;
	}*/
	//list去重
	/*public List<ScrapView> getNewList(ScrapView scrapView){
		List<ScrapView> list = createNewList(scrapView);
		List<ScrapView> resultList = new ArrayList<ScrapView>(); 
		for(ScrapView sc:list){
		    if(Collections.frequency(resultList, sc) < 1){
		    	resultList.add(sc);
		    }
		}
		return resultList;
		
	}*/
	
	/*@Override
	public Map<Object,Object> createNewMap(List<Map<Object,Object>> list) {
		Map<Object,Object> Monthmap = new HashMap<>();
		for(Map<Object,Object> map:list){
			int month =0;
			int scrapNum =0;
			for(Map.Entry<Object, Object> entry : map.entrySet()){
				if ("month".equals(entry.getKey())) {
						month = (int)entry.getValue();
			        } else if ("scrapNum".equals(entry.getKey())) {
			        	scrapNum = (int) entry.getValue();
			        }
			}
			Monthmap.put(month, scrapNum);
		}
		return Monthmap;
	}*/
	

}

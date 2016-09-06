package com.eliteams.quick4j.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.eliteams.quick4j.core.feature.factory.DataSourceContextHolder;
import com.eliteams.quick4j.web.dao.AssignmentMapper;
import com.eliteams.quick4j.web.dao.DShiftOutputMapper;
import com.eliteams.quick4j.web.dao.MaterialMaintainMapper;
import com.eliteams.quick4j.web.dao.ObtainYieldMapper;
import com.eliteams.quick4j.web.dao.ObtainYieldRecordMapper;
import com.eliteams.quick4j.web.dao.ProductChangedMapper;
import com.eliteams.quick4j.web.dao.StockMapper;
import com.eliteams.quick4j.web.model.Assignment;
import com.eliteams.quick4j.web.model.DShiftOutput;
import com.eliteams.quick4j.web.model.ObtainYield;
import com.eliteams.quick4j.web.model.ObtainYieldRecord;
import com.eliteams.quick4j.web.model.ProductChanged;
import com.eliteams.quick4j.web.service.ObtainYieldService;

/**
 * ObtainYield实现类
 *
 * @author liuliu 2016年7月6日 下午4:03:46
 */
@Service
public class ObtainYieldServiceImpl implements ObtainYieldService {
	
	private static Logger log = Logger.getLogger(ObtainYieldServiceImpl.class); // 初始化日志对象

	@Resource
	private ObtainYieldMapper obtainYieldMapper;
	@Resource
	private ObtainYieldRecordMapper obtainYieldRecordMapper;
	@Resource
	private AssignmentMapper assignmentMapper;
	@Resource
	private StockMapper stockMapper;
	@Resource
	private DShiftOutputMapper dShiftOutputMapper;
	@Resource
	private ProductChangedMapper productChangedMapper;
	@Resource
	private MaterialMaintainMapper materialMaintainMapper;
	/**
	 * 获取sqlserver表(采集原始信息)的list
	 */
	@Override
	public List<DShiftOutput> getSqlserverList() {
		log.info("获取sqlserver表(采集原始信息)的list开始");
		Date outputDate = getDateForSelect();
		List<DShiftOutput> sqlserverList = new ArrayList<DShiftOutput>();
		// sqlserverLists=sqlserverMapper.selectByShiftId(shiftId);
		DataSourceContextHolder.setDbType("sqlServerDataSource");
		sqlserverList = dShiftOutputMapper.selectByOutputDate(outputDate);
		DataSourceContextHolder.setDbType("mySqlDataSource");
		log.info("获取sqlserver表(采集原始信息)的list结束");
		return sqlserverList;
	}
	
	/**
	 * 1、从sqlserverList中获得当前的生产量，先得到上次的生产量，再计算与这次的差量
	 * 2、根据任务下达情况，得到当前生产的物料号
	 */
	@Override
	public void obtainoYieldAndStockFromSqlserver(List<DShiftOutput> sqlserverList) {
		log.debug("解析sqlserverList");
		for (DShiftOutput sqlserver : sqlserverList) {
			
//			从每一条sqlserver中得到当前产量，分别插入更新表和流水记录表
			long numThree = sqlserverToObtainYield(sqlserver);
			//将设备号转化为工序号
			int EqptId=sqlserver.getEqptId();
			int deviceId=setEqptIdToDeviceId(EqptId);
			
			ProductChanged productChanged=new ProductChanged();
			productChanged.setDeviceId(deviceId);
			//通过任务下达来获取物料号
	// 		Assignment assignment = new Assignment();
			//根据工序号进行查询
			productChanged=productChangedMapper.selectByDeviceId(deviceId);
		//	assignment.setDeviceId(deviceId);
		//	assignment.setStartTime(sqlserver.getOutputDate());
		//	assignment = assignmentMapper.selectByDeviceIdAndTime(assignment);
			if (productChanged != null) {
				//获取物料描述
				String materialdescribe=materialMaintainMapper.selectMaterialDescribe(productChanged.getMaterialId());
				Map map = new HashMap();
				map.put("materialDescribe", materialdescribe);
				map.put("materialId",productChanged.getMaterialId());
				map.put("numThree", numThree);
				stockMapper.updateByMaterialIdMap(map);//增加待分配量，最近的产量
			}
	    	  
		}
	}
	
	/**
	 * 从每一条sqlserver中得到当前产量，分别插入更新表和流水记录表
	 * @param sqlserver
	 * @return 返回本次新增产量
	 */
	private long sqlserverToObtainYield(DShiftOutput sqlserver){
		ObtainYield obtainYield = new ObtainYield();
		obtainYield.setdSoId(sqlserver.getdSoId());
		obtainYield.setEqptId(sqlserver.getEqptId());
		obtainYield.setShiftId(sqlserver.getShiftId());
		obtainYield.setOutputDate(sqlserver.getOutputDate());
		obtainYield.setReportNum(sqlserver.getDaNum().longValue());
		// 获取系统更新时间
		obtainYield.setLastUpdateTime(new Date());
		// 获取系统内的设备上一次报工数
		long numOne = selectPreTotalByShiftIdDate(obtainYield);
		// 获取最新设备报工数
		long numTwo = sqlserver.getDaNum().longValue();
		long numThree = numTwo - numOne;//计算最新生产量
		log.debug("上一次采集量为"+numOne+" 最新一次为"+numTwo+" 计算最新产量为"+numThree);
		// 插入到更新表
		obtainYieldMapper.insertOrUpdate(obtainYield);
		
		if(numThree>0){
			// 如果有最新的数量插入到流水记录表
			obtainYieldRecordMapper.insert(addObtainYieldRecord(sqlserver));
		}
		return numThree;
	}
	

	/**
	 * 获取系统内的设备上一次报工数
	 */
	@Override
	public long selectPreTotalByShiftIdDate(ObtainYield obtainYield) {
		long longs = 0;
		if (obtainYieldMapper.selectByShiftIdDate(obtainYield) == null) {
			return longs;
		} else {
			longs = obtainYieldMapper.selectByShiftIdDate(obtainYield);
			return longs;
		}
	}

	/**
	 * 插入设备采集记录
	 * 
	 * @param sqlserver
	 * @return
	 */
	public ObtainYieldRecord addObtainYieldRecord(DShiftOutput sqlserver) {
		ObtainYieldRecord obtainYieldRecord = new ObtainYieldRecord();
		obtainYieldRecord.setdSoId(sqlserver.getdSoId());
		obtainYieldRecord.setEqptId(sqlserver.getEqptId());
		obtainYieldRecord.setShiftId(sqlserver.getShiftId());
		obtainYieldRecord.setOutputDate(sqlserver.getOutputDate());
		obtainYieldRecord.setReportNum(sqlserver.getDaNum().longValue());
		obtainYieldRecord.setLastUpdateTime(sqlserver.getLastUpdateTime());
		obtainYieldRecord.setInsertTime(new Date());
		return obtainYieldRecord;

	}

	/**
	 * 当前时间减去1分钟然后转化为日的格式，避免跨夜的时候计算错误
	 * 
	 * @return
	 */
	@Override
	public Date getDateForSelect() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -1); // 减少30分钟
		// Cal.add(Calendar.HOUR,-3); // 目前時間加3小時
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = cal.getTime();
		String dateString = formatter.format(date);
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
    /**
     * 将采集点设备号转化为工序号
     */
	public int setEqptIdToDeviceId(int eqptId){
		if(eqptId==194){
			eqptId=193;
		}
		if(eqptId==198){
			eqptId=197;
		}
		if(eqptId==202){
			eqptId=201;
		}
		if(eqptId==213){
			eqptId=212;
		}
		return eqptId;
	}
	
}

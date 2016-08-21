package com.eliteams.quick4j.web.service;

import java.util.Date;
import java.util.List;

import com.eliteams.quick4j.web.model.DShiftOutput;
import com.eliteams.quick4j.web.model.ObtainYield;



/**
 *
 *获取产量接口
 * @author liuliu
 * 2016年7月6日 下午3:59:53
 */
public interface ObtainYieldService {
	/**
	 * 通过班次编号获取采集点数据
	 * @param shiftId
	 * @return
	 */
//	void obtainoYieldFromSqlserver(int shiftId);
	
	public List<DShiftOutput> getSqlserverList();

	/**
	 * 根据班产时间进行查询
	 * @param outputDate
	 */
//	void obtainoYieldFromSqlserver(Date outputDate);
	
	void obtainoYieldAndStockFromSqlserver(List<DShiftOutput> sqlserverLists);
	/**
	 * 通过期间加设备号查询已存报工数
	 * @param obtainYield
	 * @return
	 */
	long selectPreTotalByShiftIdDate(ObtainYield obtainYield);
	
	public Date getDateForSelect();
	
}

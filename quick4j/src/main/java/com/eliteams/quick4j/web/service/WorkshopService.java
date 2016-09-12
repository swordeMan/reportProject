package com.eliteams.quick4j.web.service;

import java.util.List;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.model.ReworkReason;
import com.eliteams.quick4j.web.model.Workshop;

public interface WorkshopService {
	List<Workshop> slelectAllWorkshop(); // 查询所有车间
	
	Workshop selectByPrimaryKey(int id);
	
	List<Workshop> getWorkshopByPage(Page page);
	
	List<Workshop> getWorkshopByPage(Page page,String orderByClause, String keywords);
	
	void insertWorkshop(Workshop Workshop);// 编制新的工厂，在表中插入

	void deleteWorkshop(int id); // 删除工厂

	void editWorkshop(Workshop workshop); // 修改工厂
    
	Workshop selectByworkshop(String workshop);//通过产区进行查询
}

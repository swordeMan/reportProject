package com.eliteams.quick4j.web.service;

import java.util.List;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.model.MaterialMaintain;

public interface MaterialMaintainService {
	
	//关键字分页查询物料基础数据表
	List<MaterialMaintain> getMaterialInfoByPageAndKeywords(Page page,String orderByClause,String keywords);
	
	//工序号查询对应物料基础表中的所有物料
	List<MaterialMaintain> getMaterialInfoBydeviceId(Integer deviceId);
	
}
package com.eliteams.quick4j.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.dao.MaterialMaintainMapper;
import com.eliteams.quick4j.web.model.MaterialMaintain;
import com.eliteams.quick4j.web.service.MaterialMaintainService;

@Service
public class MaterialMaintainServiceImpl implements MaterialMaintainService {
	
	@Resource
	private MaterialMaintainMapper materialMaintainMapper;
	
	@Override
	public List<MaterialMaintain> getMaterialInfoByPageAndKeywords(Page page, String orderByClause, String keywords) {
		return materialMaintainMapper.getMaterialInfoByPageAndKeywords(page, orderByClause, keywords);
	}
	
	@Override
	public List<MaterialMaintain> getMaterialInfoBydeviceDesc(String deviceDesc) {
		return materialMaintainMapper.getMaterialInfoBydeviceDesc(deviceDesc);
	}

}

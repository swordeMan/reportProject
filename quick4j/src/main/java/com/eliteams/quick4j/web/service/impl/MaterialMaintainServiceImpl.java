package com.eliteams.quick4j.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.dao.DeviceInfoMapper;
import com.eliteams.quick4j.web.dao.MaterialMaintainMapper;
import com.eliteams.quick4j.web.model.MaterialMaintain;
import com.eliteams.quick4j.web.model.ProductingView;
import com.eliteams.quick4j.web.service.MaterialMaintainService;

@Service
public class MaterialMaintainServiceImpl implements MaterialMaintainService {
	
	@Resource
	private MaterialMaintainMapper materialMaintainMapper;
	@Resource
	private DeviceInfoMapper deviceInfoMapper;
	/** 
	 * 查看所有
	 */
	@Override
	public List<MaterialMaintain> getMaterialInfoByPageAndKeywords(Page page, String orderByClause, String keywords) {
		return materialMaintainMapper.getMaterialInfoByPageAndKeywords(page, orderByClause, keywords);
	}
	/**
	 * 添加
	 */
	@Override
	public void insertMaterialMaintain(MaterialMaintain materialMaintain) {
		// TODO Auto-generated method stub
		String deviceDescribes=deviceInfoMapper.selectDeiviceDescribeByDeviceId(materialMaintain.getDeviceId()).getDeiviceDescribe();
		materialMaintain.setDeviceDescribe(deviceDescribes);
		materialMaintainMapper.insert(materialMaintain);
	}
	/**
	 * 删除
	 */
	@Override
	public void deleteMaterialMaintain(Long id) {
		// TODO Auto-generated method stub
		materialMaintainMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 修改物料信息
	 */
	@Override
	public void editMaterialMaintain(MaterialMaintain materialMaintain) {
		// TODO Auto-generated method stub
		String deviceDescribes=deviceInfoMapper.selectDeiviceDescribeByDeviceId(materialMaintain.getDeviceId()).getDeiviceDescribe();
		materialMaintain.setDeviceDescribe(deviceDescribes);
		materialMaintainMapper.updateByPrimaryKey(materialMaintain);
	}
	/**
	 * 根据主键查询
	 */
	@Override
	public MaterialMaintain selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return materialMaintainMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<MaterialMaintain> getMaterialInfoBydeviceId(Integer deviceId) {
		return materialMaintainMapper.getMaterialInfoBydeviceId(deviceId);
	}

}

package com.eliteams.quick4j.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eliteams.quick4j.web.dao.ProductingViewMapper;
import com.eliteams.quick4j.web.model.ProductingView;
import com.eliteams.quick4j.web.service.ProductingViewService;
import com.eliteams.quick4j.web.service.UserInfoService;

@Service
public class ProductingViewServiceImpl implements ProductingViewService {
	
	@Resource
	private ProductingViewMapper productingViewMapper;
	
	@Resource
	private UserInfoService userInfoService;
	
	@Override
	public List<ProductingView> getMaterialInfoBydeviceDesc(Integer deviceId) {
		return productingViewMapper.getMaterialInfoBydeviceDesc(deviceId);
	}

	/*@Override
	public List<ProductingView> workNumToUserNameList(Integer deviceId) {
		List<ProductingView> productingViewList = getMaterialInfoBydeviceDesc(deviceId);
		List<ProductingView> pvList = new ArrayList<ProductingView>();
		for(ProductingView pv:productingViewList){
			if(pv.getWorkNum()!= null){
				pv.setUsername(userInfoService.workNoToUserName(pv.getWorkNum()));
			}
			pvList.add(pv);
		}
		return pvList;
	}*/

}

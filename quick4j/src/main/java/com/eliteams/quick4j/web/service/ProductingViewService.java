package com.eliteams.quick4j.web.service;

import java.util.List;

import com.eliteams.quick4j.web.model.ProductingView;

public interface ProductingViewService {
	
	//工序号查询物料基础信息（在产和不在产）
	List<ProductingView> getMaterialInfoBydeviceDesc(Integer deviceId);
	
	/*//查询到的productingViewList中，set用户名
	List<ProductingView> workNumToUserNameList(Integer deviceId);*/

}

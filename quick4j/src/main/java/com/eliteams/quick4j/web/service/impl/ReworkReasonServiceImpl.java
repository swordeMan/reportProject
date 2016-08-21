package com.eliteams.quick4j.web.service.impl;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.dao.ReworkReasonMapper;
import com.eliteams.quick4j.web.model.ReworkReason;
import com.eliteams.quick4j.web.service.ReworkReasonService;

@Service
public class ReworkReasonServiceImpl implements ReworkReasonService {

	private static Logger log = Logger.getLogger(ReworkReasonServiceImpl.class); // 初始化日志对象
	@Resource
	private ReworkReasonMapper reworkReasonMapper;

	// 截取物料描述的前两个字符串
	@Override
	public String materialDescribeToName(String materialDescribe) {
		String s = materialDescribe.substring(0, 2);
		if (s.equals("钢圈")) {
			s = "涂装";
		}
		return s;
	}

	/**
	 * 根据前两个字符串（物料名）查询对应的原因
	 */
	@Override
	public List<ReworkReason> selectReworkReasonByName(String materialDescribe) {
		String materialName = materialDescribeToName(materialDescribe);
		List<ReworkReason> reworkReasonList = new ArrayList<ReworkReason>();
		reworkReasonList = reworkReasonMapper.selectByMaterialName(materialName);
		return reworkReasonList;
	}

	// 查询所有原因信息
	@Override
	public List<ReworkReason> slelectAllReworkReason() {
		List<ReworkReason> ReworkreasonList = new ArrayList<ReworkReason>();
		ReworkreasonList = reworkReasonMapper.selectAll();
		return ReworkreasonList;
	}

	// 编制新的返修原因，表中插入
	@Override
	public void insertReworkReason(ReworkReason insertReworkReason) {
		reworkReasonMapper.insert(insertReworkReason);
	};

	@Override
	public List<ReworkReason> getReworkReasonByPage(Page page) {
		log.info("ReworkReasonServiceImpl.getReworkReasonByPage");
		return reworkReasonMapper.getReworkReasonByPage(page);
	}

	public List<ReworkReason> getReworkReasonByPage(Page page, String orderByClause,String keywords) {
		log.info("ReworkReasonServiceImpl.getReworkReasonByPage");
		return reworkReasonMapper.getReworkReasonByPageAndKeywords(page,orderByClause, keywords);
	}

	// 删除返修原因
	@Override
	public void deleteReworkReason(long id) {
		reworkReasonMapper.deleteByPrimaryKey(id);
	};

	// 修改返修原因
	@Override
	public void editReworkReason(ReworkReason reworkReason) {
		reworkReasonMapper.updateByPrimaryKey(reworkReason);
	}

	@Override
	public ReworkReason selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return reworkReasonMapper.selectByPrimaryKey(id);

	}
	

}

package com.eliteams.quick4j.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eliteams.quick4j.web.dao.ReworkReasonRelationMapper;
import com.eliteams.quick4j.web.model.ReworkReasonRelation;
import com.eliteams.quick4j.web.service.ReworkReasonRelationService;
/**
 * 
 *返修原因关联实现类
 * @author liuliu
 * 2016年7月4日 下午5:26:30
 */
@Service
public class ReworkReasonRelationServiceImpl implements ReworkReasonRelationService{
	@Resource
	private  ReworkReasonRelationMapper  reworkReasonRelationMapper;
	/**
	 * 根据返修订单查询返修关联表
	 */
	@Override
	public List<ReworkReasonRelation> selectByReworkId(String reworkId) {
		List <ReworkReasonRelation> reworkReasonRelationList=new ArrayList<ReworkReasonRelation>();
		reworkReasonRelationList=reworkReasonRelationMapper.selectByReworkId(reworkId);
		return reworkReasonRelationList ;
	}
	/**
	 * 从关联表查询返修原因列表
	 */
	@Override
	public List<Long> selectByReworkIdGetReasonId(String reworkId) {
		List<Long>  reworkReasonIdList=new ArrayList<Long>();
		reworkReasonIdList=reworkReasonRelationMapper.selectByReworkIdGetReasonId(reworkId);
		return reworkReasonIdList;
	}
	
}

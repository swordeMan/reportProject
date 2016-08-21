package com.eliteams.quick4j.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.controller.UserController;
import com.eliteams.quick4j.web.dao.ScrapReasonMapper;
import com.eliteams.quick4j.web.model.ScrapReason;
import com.eliteams.quick4j.web.model.UserInfo;
import com.eliteams.quick4j.web.service.ScrapReasonService;

/**
 * 
 * 原因接口实现类
 * @author qianjun
 *
 */
@Service
public class ScrapReasonServiceImpl implements ScrapReasonService {
	
	private static Logger log = Logger.getLogger(ScrapReasonServiceImpl.class); // 初始化日志对象
	@Resource
	private ScrapReasonMapper scrapReasonMapper;

	//查询所有原因信息
	@Override
	public List<ScrapReason> slelectAllScrapReason() {
		List<ScrapReason> ScrapreasonList = new ArrayList<ScrapReason>();
		ScrapreasonList = scrapReasonMapper.selectAll();
		return ScrapreasonList;
	}
	
	//截取物料描述的前两个字符串
	@Override
	public String materialDescribeToName(String materialDescribe) {
		String s = materialDescribe.substring(0, 2);
		if(s.equals("钢圈")){
			s = "涂装";
		}
		return s;
	}
	
	//根据前两个字符串（物料名）查询对应的原因
	@Override
	public List<ScrapReason> selectScrapReasonByMaterialDescribe(String materialDescribe) {
		String materialName = materialDescribeToName(materialDescribe);
		List<ScrapReason> scrapReasonList = new ArrayList<ScrapReason>();
		scrapReasonList = scrapReasonMapper.selectByMaterialName(materialName);
		return scrapReasonList;
	}
	//编制新的报废原因，表中插入
	@Override
	public void insertScrapReason(ScrapReason insertScrapReason ){
		scrapReasonMapper.insert(insertScrapReason);
	};
	//删除报废原因
		@Override
	public void deleteScrapReason(long id ){
		scrapReasonMapper.deleteByPrimaryKey(id);
	};
	//修改报废原因
			@Override
	public void editScrapReason(ScrapReason scrapReason ){
		scrapReasonMapper.updateByPrimaryKey(scrapReason);
		}

	       @Override
	public ScrapReason selectByPrimaryKey(Long id) {
				// TODO Auto-generated method stub
				return scrapReasonMapper.selectByPrimaryKey(id);
 
	   }
	       
	       @Override
	public List<ScrapReason> getScrapReasonByPage(Page page) {
	   	   		log.info("ScrapReasonServiceImpl.getScrapReasonByPage");
	   	   		return scrapReasonMapper.getScrapReasonByPage(page);
	   	   	}
 
	       @Override
	public List<ScrapReason> getScrapReasonByPage(Page page,String orderByClause,String keywords) {
	   		log.info("ScrapReasonServiceImpl.getScrapReasonByPage");
	   		return scrapReasonMapper.getScrapReasonByPageAndKeywords(page, orderByClause,keywords);
	   	}

			
	

}

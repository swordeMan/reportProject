package com.eliteams.quick4j.web.service;

import java.util.List;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.form.CreateScrapForm;
import com.eliteams.quick4j.web.model.ScrapReason;
import com.eliteams.quick4j.web.model.UserInfo;

/**
 * 
 * 报废原因接口
 * @author qianjun
 *
 */
public interface ScrapReasonService {
	
	List<ScrapReason> slelectAllScrapReason();             //查询所有原因信息
	
	List<ScrapReason> getScrapReasonByPage(Page page);
	
	List<ScrapReason> getScrapReasonByPage(Page page,String orderByClause,String keywords);
	
	ScrapReason  selectByPrimaryKey(Long id);
	
	void insertScrapReason(ScrapReason insertScrapReason );//编制新的报废原因，在表中插入
	
	void deleteScrapReason(long id );                      //删除报废原因
	
	void editScrapReason(ScrapReason scrapReason );        //修改报废原因

	String materialDescribeToName(String materialDescribe);//截取物料描述的前两个字符串
	
	List<ScrapReason> selectScrapReasonByMaterialDescribe(String materialDescribe);//根据前两个字符串（物料名）查询对应的原因
	
}

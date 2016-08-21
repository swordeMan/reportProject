package com.eliteams.quick4j.web.service;

import java.util.List;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.core.generic.GenericService;
import com.eliteams.quick4j.web.model.Correct;
import com.eliteams.quick4j.web.model.ReworkReason;
import com.eliteams.quick4j.web.model.Stock;
import com.eliteams.quick4j.web.model.User;

/**
 *修正业务接口
 *
 * @author liuliu
 * 2016年6月26日 下午3:10:06
 */
public interface CorrectService extends GenericService<Object, Object> {
	/**
	 * 查询所有修正记录单
	 * @return
	 */
	
	List<Correct> selectAllCorrect();
	
	List<Correct> getCorrectByPage(Page page);
	
	List<Correct> getCorrectByPage(Page page,String orderByClause, String keywords);
/**
 * 根据修正单id查询修正单详情
 * @param correctId
 * @return
 */
	Correct getCorrectByCorrectId(String correctId);
/**
 * 新建修正单,同时引发待分配量变化
 * @param correct
 */
	void insertCorrect(Correct correct);

    //List<Correct> slelectByCorrectId(String correctId);
}
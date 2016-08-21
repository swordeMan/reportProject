package com.eliteams.quick4j.web.service;

import java.util.List;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.model.ReworkReason;

/**
 * 
 * 返修原因接口
 * 
 * @author shenji
 *
 */
public interface ReworkReasonService {

	String materialDescribeToName(String materialDescribe);
	/**
	 * 根据物料描述查询返修原因
	 * @param materialDescribe
	 * @return
	 */
	List<ReworkReason> selectReworkReasonByName(String materialDescribe);
	  /**
	   * 截取物料描述的前两个字符串
	   * @param materialDescribe
	   * @return
	   */
	List<ReworkReason> slelectAllReworkReason(); // 查询所有返修原因信息

	ReworkReason selectByPrimaryKey(Long id);

	List<ReworkReason> getReworkReasonByPage(Page page);

	List<ReworkReason> getReworkReasonByPage(Page page,String orderByClause, String keywords);

	void insertReworkReason(ReworkReason insertReworkReason);// 编制新的返修原因，在表中插入

	void deleteReworkReason(long id); // 删除返修原因

	void editReworkReason(ReworkReason reworkReason); // 修改返修原因

}

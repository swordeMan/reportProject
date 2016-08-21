package com.eliteams.quick4j.web.service;

import java.util.List;


import com.eliteams.quick4j.web.model.ReworkReasonRelation;

/**
 *返修原因关联接口
 *
 * @author liuliu
 * 2016年7月4日 下午5:31:30
 */

public interface ReworkReasonRelationService{
/**
 * 通过返修单号查询
 * @param reworkId
 * @return
 */
List<ReworkReasonRelation> selectByReworkId(String reworkId);
/**
 * 通过返修单号查询返修原因id
 * @param reworkId
 * @return
 */
List<Long> selectByReworkIdGetReasonId(String reworkId);


}

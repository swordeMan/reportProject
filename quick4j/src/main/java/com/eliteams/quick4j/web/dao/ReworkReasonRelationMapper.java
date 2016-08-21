package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.web.model.ReworkReasonRelation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReworkReasonRelationMapper {
	 int deleteByPrimaryKey(Long id);

	    int insert(ReworkReasonRelation record);

	    ReworkReasonRelation selectByPrimaryKey(Long id);

	    List<ReworkReasonRelation> selectAll();

	    int updateByPrimaryKey(ReworkReasonRelation record);

		List<ReworkReasonRelation> selectByReworkId(String reworkId);

		List<Long> selectByReworkIdGetReasonId(String reworkId);
}
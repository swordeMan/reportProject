package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.web.model.ScrapReasonRelation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapReasonRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ScrapReasonRelation record);

    ScrapReasonRelation selectByPrimaryKey(Long id);

    List<ScrapReasonRelation> selectAll();

    int updateByPrimaryKey(ScrapReasonRelation record);
}
package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.web.model.DeviceProcessRelation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceProcessRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeviceProcessRelation record);

    DeviceProcessRelation selectByPrimaryKey(Integer id);

    List<DeviceProcessRelation> selectAll();

    int updateByPrimaryKey(DeviceProcessRelation record);
}
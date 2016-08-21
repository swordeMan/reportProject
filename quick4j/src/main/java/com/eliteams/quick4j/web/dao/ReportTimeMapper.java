package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.web.model.ReportTime;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportTimeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportTime record);

    ReportTime selectByPrimaryKey(Integer id);

    List<ReportTime> selectAll();

    int updateByPrimaryKey(ReportTime record);
}
package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.web.model.ReworkView;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReworkViewMapper {
    int insert(ReworkView record);

    List<ReworkView> selectAll();
    
    List<ReworkView> selectByReworkId(String reworkId);
}
package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.web.model.StockAssignmentView;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockAssignmentViewMapper {
    int insert(StockAssignmentView record);

    List<StockAssignmentView> selectAll();
}
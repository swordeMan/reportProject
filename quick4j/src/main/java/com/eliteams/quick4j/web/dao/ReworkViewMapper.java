package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.web.model.ReworkView;
import java.util.List;

public interface ReworkViewMapper {
    int insert(ReworkView record);

    List<ReworkView> selectAll();
    
    List<ReworkView> getReworkDetailsByReworkView(ReworkView reworkView);
}
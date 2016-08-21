package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.web.model.DeviceFinishedView;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceFinishedViewMapper {
    int insert(DeviceFinishedView record);

    List<DeviceFinishedView> selectAll();
    
    @MapKey("month")
    Map<String,Map> getFinishedTotalByMonth(DeviceFinishedView deviceFinishedView);
}
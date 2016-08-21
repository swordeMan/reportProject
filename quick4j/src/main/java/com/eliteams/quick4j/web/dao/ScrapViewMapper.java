package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.web.model.DeviceFinishedView;
import com.eliteams.quick4j.web.model.ScrapView;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapViewMapper {
	
    int insert(ScrapView record);

    List<ScrapView> selectById(String scrapId);
    
    List<ScrapView> getScrapDetailsByScrapView(ScrapView scrapView);
    
    @MapKey("month")
    Map<String,Map> getScrapTotalByMonth(ScrapView scrapView);
    
    //List<ScrapView> getScrapTotalByMonth(ScrapView scrapView);
    
    
}
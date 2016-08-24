package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.web.model.ObtainYieldRecord;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ObtainYieldRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ObtainYieldRecord record);

    ObtainYieldRecord selectByPrimaryKey(Long id);

    List<ObtainYieldRecord> selectAll();

    int updateByPrimaryKey(ObtainYieldRecord record);
    //删除两个月前的数据
    int deleteByTwoMonth(Date date);
    
}
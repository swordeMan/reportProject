package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.web.model.SysSerialNumber;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysSerialNumberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysSerialNumber record);

    SysSerialNumber selectByPrimaryKey(Long id);

    List<SysSerialNumber> selectAll();

    int updateByPrimaryKey(SysSerialNumber record);
    
    long getMaxSerialByModuleCode(String moduleCode);
}
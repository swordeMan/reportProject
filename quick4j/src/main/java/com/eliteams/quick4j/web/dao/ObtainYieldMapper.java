package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.web.model.ObtainYield;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ObtainYieldMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ObtainYield record);

    ObtainYield selectByPrimaryKey(Long id);

    List<ObtainYield> selectAll();

    int updateByPrimaryKey(ObtainYield record);
    
    Long selectByShiftIdDate(ObtainYield record);
    
    Long selectByShiftIdDate1();
	//int selectByShiftIdDate(Integer shiftId, Date outputDate,Integer eqptId);

	int insertOrUpdate(ObtainYield obtainYield);
}
package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.web.model.DShiftOutput;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DShiftOutputMapper {

    //根据班次编号进行查询
//	List<DShiftOutput> selectByShiftIdDate(Integer shiftId, Date outputDate);
    //根据日期进行查询
	List<DShiftOutput> selectByOutputDate(Date outputDate);
}
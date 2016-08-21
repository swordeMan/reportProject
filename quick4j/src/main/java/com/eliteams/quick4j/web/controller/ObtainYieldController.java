package com.eliteams.quick4j.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eliteams.quick4j.core.entity.Json;
import com.eliteams.quick4j.web.service.ObtainYieldService;

@Controller
@RequestMapping(value = "/obtainyield")

public class ObtainYieldController {
	@Resource
	private ObtainYieldService obtainYieldService;
	/**
	 * 采集设备报工数更新待分配量
	 */
	@RequestMapping(value = "/addyield", method = RequestMethod.GET)
    public void selectSqlserverByOutputDate(){
//		Date date=getDateForSelect();
//    	obtainYieldService.obtainoYieldFromSqlserver(date);
//		obtainYieldService.obtainoYieldFromSqlserver();
    }
	/**
	 * 当前时间减去30分钟然后转化为日的格式
	 * @return
	 */
	public Date getDateForSelect(){
	    Calendar cal=Calendar.getInstance();      
		cal.add(Calendar.MINUTE,-1);  //减少30分钟  
	//	Cal.add(Calendar.HOUR,-3); // 目前時間加3小時 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 Date date=cal.getTime();
		 String dateString = formatter.format(date);
		 try {
			date=formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 return date;
	}
}

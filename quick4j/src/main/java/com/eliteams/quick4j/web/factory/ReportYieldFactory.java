package com.eliteams.quick4j.web.factory;

import com.eliteams.quick4j.web.model.ReportYield;
import com.eliteams.quick4j.web.service.ReportYieldService;
import com.eliteams.quick4j.web.service.SysSerialNumberService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhang on 2016/8/22.
 * 练习使用工厂模式，来创建报工类
 */

@Component
public class ReportYieldFactory {

    @Resource
    private SysSerialNumberService sysSerialNumberService;


    /**
     * 产生手动报工基础对象
     * @return
     */
    public ReportYield createHandReportYield(){
        ReportYield reportYield = new ReportYield();
        String messageId = sysSerialNumberService.generateSerialNumberByModelCode(ReportYieldService.REPORT_MESSAGE_ID);
        reportYield.setMessageId(messageId);//设置消息ID
        Subject currentUser = SecurityUtils.getSubject();
        String username = currentUser.getPrincipal().toString();
        reportYield.setReportUsername(username);//报工人
        reportYield.setOperation(ReportYieldService.REPORT_OPREATION);//设为A
        return reportYield;
    }

    /**
     * 重载对已有对象的新增，主要用于手动报工时
     * @param reportYield
     * @return
     */
    public ReportYield createHandReportYield(ReportYield reportYield){
        String messageId = sysSerialNumberService.generateSerialNumberByModelCode(ReportYieldService.REPORT_MESSAGE_ID);
        reportYield.setMessageId(messageId);//设置消息ID
        Subject currentUser = SecurityUtils.getSubject();
        String username = currentUser.getPrincipal().toString();
        reportYield.setReportUsername(username);//报工人
        reportYield.setOperation(ReportYieldService.REPORT_OPREATION);//设为A
        return reportYield;
    }

    /**
     * 产生自动报工类
     * @return 自动报工的基础对象
     */
    public ReportYield createAutoReportYield(){
        ReportYield reportYield = new ReportYield();
        String messageId = sysSerialNumberService.generateSerialNumberByModelCode(ReportYieldService.REPORT_MESSAGE_ID);
        reportYield.setMessageId(messageId);//设置消息ID
        reportYield.setReportUsername("system");//报工人
        reportYield.setOperation(ReportYieldService.REPORT_OPREATION);//设为A
        //提前八个小时作为记账日期
        reportYield.setAccountDate(getTime());
        return reportYield;
    }
    /**
     * 记账日期提前八个小时
     * @return 提前八小时的日期date
     */
    public Date getTime(){
    	Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR,-8); // 目前時間加3小時
		Date date = cal.getTime();
	    return date;
    }

}

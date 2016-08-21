package com.eliteams.quick4j.test.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.eliteams.quick4j.core.feature.test.TestSupport;
import com.eliteams.quick4j.web.service.SysSerialNumberService;

public class UserServiceTest extends TestSupport {

    @Resource
    private SysSerialNumberService sysSerialNumberService;

    @Test
    public void test_insert() {
    	String serialNumber = sysSerialNumberService.generateSerialNumberByModelCode("WX");
    	System.out.println(serialNumber);
    }

}

package com.eliteams.quick4j.test.bean;

import java.lang.reflect.Field;

import javax.annotation.Resource;

import org.junit.Test;

import com.eliteams.quick4j.core.feature.test.TestSupport;
import com.eliteams.quick4j.web.enums.FieldNameEnum;
import com.eliteams.quick4j.web.model.SapOrder;

/**
 * SpiderTest : 爬虫测试类
 *
 * @author StarZou
 * @since 2014-10-27 22:44
 */
public class SpiderTest{

    @Test
    public void getFieldNames(){
    	
    	Field[] declaredFields = SapOrder.class.getDeclaredFields();
    	
    	
    	String[] fields = {"productOrderId","saleOrderId","saleOrderRow"};
		String [] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			fieldNames[i] = FieldNameEnum.valueOf("productOrderId").getTitle();
		}
	}
    
    
}

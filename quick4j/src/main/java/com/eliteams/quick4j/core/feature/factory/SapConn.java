package com.eliteams.quick4j.core.feature.factory;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.DestinationDataProvider;



public class SapConn {
	/*
	 * sap连接配置
	 */
	private static final String ABAP_AS_POOLED = "ABAP_AS_WITH_POOL"; 
	private static Logger log = Logger.getLogger(SapConn.class); // 初始化日志对象
	static{
		Properties connectionProperties = new Properties();
	//	connectionProperties.setProperty(DestinationDataProvider.JCO_ASHOST, "192.168.18.21");//服务器
	//  connectionProperties.setProperty(DestinationDataProvider.JCO_SYSNR, "00");//系统编号
	//	connectionProperties.setProperty(DestinationDataProvider.JCO_CLIENT, "800");//sap集团
	//	connectionProperties.setProperty(DestinationDataProvider.JCO_USER, "JGRFC");//sap用户名
	//	connectionProperties.setProperty(DestinationDataProvider.JCO_PASSWD, "123456");//密码
		
		
		connectionProperties.setProperty(DestinationDataProvider.JCO_ASHOST, "192.168.18.20");//测试服务器
	    connectionProperties.setProperty(DestinationDataProvider.JCO_SYSNR, "10");//系统编号
		connectionProperties.setProperty(DestinationDataProvider.JCO_CLIENT, "300");//sap集团
		connectionProperties.setProperty(DestinationDataProvider.JCO_USER, "JUPIN");//sap用户名
		connectionProperties.setProperty(DestinationDataProvider.JCO_PASSWD, "123456");//密码
		//connectionProperties.setProperty(DestinationDataProvider.JCO_LANG, "");//登录语言
		//connectionProperties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, "");//最大连接数
		//connectionProperties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT, "");//最大连接线程
		
		createPropertiesFile(ABAP_AS_POOLED,"jcoDestination",connectionProperties);
		
	}
	
	/*
	 * 创建sap接口属性文件
	 * @param name ABAP管道名称
	 * @param suffix 属性文件后缀名
	 * @param properties 属性文件内容
	 */
	public static void createPropertiesFile(String name,String suffix,Properties properties){
		File cfg = new File(name+"."+suffix);
		if(cfg.exists()){
			cfg.deleteOnExit();
		}
		try {
			FileOutputStream fos = new FileOutputStream(cfg,false);
			properties.store(fos, "it is just for test");
			fos.close();
		} catch (Exception e) {
			log.error("Create Data file fault, error msg: " + e.toString());
			throw new RuntimeException("Unable to create the destination file"+cfg.getName(),e);
		}
	}
	
	/*
	 * 获取sap连接
	 * @return sap对象
	 */
	public static JCoDestination connect(){
		JCoDestination destination = null;
		try {
			destination = JCoDestinationManager.getDestination(ABAP_AS_POOLED);
		} catch (JCoException e) {
			log.error("connect to sap error,error msg:"+e.toString());
		}
		return destination;
		
	}

}

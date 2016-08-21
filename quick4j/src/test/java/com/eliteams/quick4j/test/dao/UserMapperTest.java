package com.eliteams.quick4j.test.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.eliteams.quick4j.core.entity.Json;
import com.eliteams.quick4j.core.feature.test.TestSupport;
import com.eliteams.quick4j.web.dao.ReportTimeMapper;
import com.eliteams.quick4j.web.dao.ReportYieldMapper;
import com.eliteams.quick4j.web.dao.StockAssignmentViewMapper;
import com.eliteams.quick4j.web.dao.StockMapper;
import com.eliteams.quick4j.web.model.ReportTime;
import com.eliteams.quick4j.web.model.ReportYield;
import com.eliteams.quick4j.web.model.SapOrder;
import com.eliteams.quick4j.web.model.Stock;
import com.eliteams.quick4j.web.model.StockAssignmentView;
import com.eliteams.quick4j.web.service.ObtainYieldService;
import com.eliteams.quick4j.web.service.ReportYieldService;
import com.eliteams.quick4j.web.service.SapOrderService;
import com.eliteams.quick4j.web.service.StockService;
import com.eliteams.quick4j.web.service.SysSerialNumberService;
import com.sap.conn.jco.JCoException;

public class UserMapperTest extends TestSupport {
private static Logger log = Logger.getLogger(UserMapperTest.class); // 初始化日志对象
	
	@Resource
	private ObtainYieldService obtainYieldService;
	
	@Resource
	private StockAssignmentViewMapper stockAssignmentViewMapper;
	
	@Resource
	private SapOrderService sapOrderService;
	
	@Resource
	private SysSerialNumberService sysSerialNumberService;
	
	@Resource
	private ReportYieldService reportYieldService;
	
	@Resource
	private ReportYieldMapper reportYieldMapper;
	
	@Resource
	private StockMapper stockMapper;
	
	@Resource
	private StockService stockService;
	
	@Resource
	ReportTimeMapper reportTimeMapper;

    /*@Test
    public void test_selectByExampleAndPage() {
        start();
        Page<User> page = new Page<>(1, 3);
        UserExample example = new UserExample();
        example.createCriteria().andIdGreaterThan(0L);
        final List<User> users = userMapper.selectByExampleAndPage(page, example);
        for (User user : users) {
            System.err.println(user);
        }
        end();
    }*/
    
    /*@Test
    public void test_selectBySqlServer() {
        start();
        DataSourceContextHolder.setDbType("sqlServerDataSource");
//        int i = collectMapper.selectCount();
        Date date=obtainYieldService.getDateForSelect();
        List<DShiftOutput> sqlserverLists = dShiftOutputMapper.selectByOutputDate(date);
//        DataSourceContextHolder.setDbType("mySqlDataSource");
        long i = obtainYieldMapper.selectByShiftIdDate1();
        end();
    }*/
    
    /*@Test
    public void test_towDataSource() {
        start();
        List<DShiftOutput> sqlserverLists = obtainoYieldFromSqlserver();
        obtainoYield(sqlserverLists);
        end();
    }*/
    
   /* public List<DShiftOutput> obtainoYieldFromSqlserver() {
		Date outputDate = getDateForSelect();
		List<DShiftOutput> sqlserverLists = new ArrayList<DShiftOutput>();
		// sqlserverLists=sqlserverMapper.selectByShiftId(shiftId);
		DataSourceContextHolder.setDbType("sqlServerDataSource");
		sqlserverLists = dShiftOutputMapper.selectByOutputDate(outputDate);
		DataSourceContextHolder.setDbType("mySqlDataSource");
		return sqlserverLists;
		
	}
    
    public void obtainoYield(List<DShiftOutput> sqlserverLists) {
		ObtainYield obtainYield = new ObtainYield();
		long numOne = 0;
		long numTwo = 0;
		long numThree = 0;
		String materialId = null;
		for (DShiftOutput sqlserver : sqlserverLists) {
			obtainYield.setdSoId(sqlserver.getdSoId());
			obtainYield.setEqptId(sqlserver.getEqptId());
			obtainYield.setShiftId(sqlserver.getShiftId());
			obtainYield.setOutputDate(sqlserver.getOutputDate());
			obtainYield.setReportNum(sqlserver.getDaNum().longValue());
			// 获取系统更新时间
			Date date = new Date();
			obtainYield.setLastUpdateTime(date);
			// 获取系统内的设备报工数
			numOne = selectByShiftIdDate(obtainYield);
			// 获取最新设备报工数
			numTwo = sqlserver.getDaNum().longValue();
			numThree = numTwo - numOne;
			// 插入到更新表
			obtainYieldMapper.insertOrUpdate(obtainYield);
			// 插入到记录表
		}
	}
    
    public long selectByShiftIdDate(ObtainYield obtainYield) {
		long longs = 0;
		DataSourceContextHolder.setDbType("mySqlDataSource");
		long i = obtainYieldMapper.selectByShiftIdDate1();
		if (obtainYieldMapper.selectByShiftIdDate(obtainYield) == null) {
			return longs;
		} else {
			longs = obtainYieldMapper.selectByShiftIdDate(obtainYield);
			return longs;
		}
	}
    
    public Date getDateForSelect() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -3000); // 减少30分钟
		// Cal.add(Calendar.HOUR,-3); // 目前時間加3小時
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = cal.getTime();
		String dateString = formatter.format(date);
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}*/
    
    /*@Test
    public void test_jdbc() {
        start();
        String driverName="net.sourceforge.jtds.jdbc.Driver";

        String dbURL="jdbc:jtds:sqlserver://127.0.0.1:1433/collect";

        String userName="sa";

        String userPwd="root";
        
        
			try {
				Class.forName(driverName);
				Connection ConnectiondbConn=DriverManager.getConnection(dbURL,userName,userPwd);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
         System.out.println("连接数据库成功");
        end();
    }*/
	
}

package com.eliteams.quick4j.web.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.dao.ReworkMapper;
import com.eliteams.quick4j.web.dao.ReworkReasonRelationMapper;
import com.eliteams.quick4j.web.dao.ReworkViewMapper;
import com.eliteams.quick4j.web.dao.StockMapper;
import com.eliteams.quick4j.web.form.CreateReworkForm;
import com.eliteams.quick4j.web.model.Rework;
import com.eliteams.quick4j.web.model.ReworkReasonRelation;
import com.eliteams.quick4j.web.model.ReworkView;
import com.eliteams.quick4j.web.model.Stock;
import com.eliteams.quick4j.web.service.PDFReportService;
import com.eliteams.quick4j.web.service.ReworkService;
import com.eliteams.quick4j.web.service.SysSerialNumberService;
import com.eliteams.quick4j.web.service.UserInfoService;
@Service
public class ReworkServiceImpl implements ReworkService{
	private static Logger log = Logger.getLogger(ReworkServiceImpl.class); // 初始化日志对象
	@Resource
	private ReworkMapper reworkMapper;
	@Resource
	private ReworkReasonRelationMapper reworkReasonRelationMapper;
	@Resource
	private StockMapper stockMapper;
	@Resource
	private SysSerialNumberService sysSerialNumberService;
	@Resource
	private UserInfoService userInfoService;
	@Resource
	private ReworkViewMapper reworkViewMapper;
	@Resource
	private PDFReportService pdfReportService;
	/**
	 * 实现查询所有返修单
	 */
	@Override
	public List<Rework> selectAllRework() {
		List<Rework> reworkList=new ArrayList<Rework>();
		reworkList=reworkMapper.selectAll();
		return reworkList;
	}
	/**
	 * 实现根据返修订单ID查询返修单
	 */
	@Override
	public Rework selectByReworkId(String reworkId) {
		return reworkMapper.selectByReworkId(reworkId);
	}
	/**
	 * 实现添加返修单
	 */
	@Override
	public void insertRework(Rework rework) {
		// TODO Auto-generated method stub
		//获取系统日期
		  Date date=new Date();
		  rework.setCreateTime(date);
		  //获取登录人员信息
		  Subject currentUser = SecurityUtils.getSubject();
		  String username = currentUser.getPrincipal().toString();
		  rework.setCreatePersonnel(username);
		  reworkMapper.insert(rework);
	}
	@Override
	public List<Rework> getReworkByPage(Page page) {
		log.info("ReworkServiceImpl.getReworkByPage");
		return reworkMapper.getReworkByPage(page);
	}

	public List<Rework> getReworkByPage(Page page,String orderByClause, String keywords) {
		log.info("ReworkServiceImpl.getReworkByPage");
		return reworkMapper.getReworkByPageAndKeywords(page, orderByClause,keywords);
	}
	
	/**
	 * 进行审核处理
	 */
	@Override
	public void insertAuditing(Rework rework) {
		//获取系统日期填制创建时间添加审核时间
		Date date=new Date();
		rework.setAuditingTime(date);
		//获取审核人员信息
		  Subject currentUser = SecurityUtils.getSubject();
		  String username = currentUser.getPrincipal().toString();
		  rework.setAuditor(username);
		  reworkMapper.updateByPrimaryKey(rework);
		  if((rework.getState().equals("审核通过"))&&(rework.getIncome().equals("是"))){
		  stockMapper.updateByReworkId(rework);
		  }
	}
	/**
	 * 打印返修单pdf
	 * @return
	 */
	public static boolean printReworkPdf(){  
        try{  
        //	String path="D:\text.pdf";
          //  Runtime.getRuntime().exec("cmd.exe /c\"D:\\福昕pdf\\Foxit Reader\\FoxitReader.exe\"/p" +"D:\\text.pdf"); 
            Runtime.getRuntime().exec("D:\\福昕pdf\\Foxit Reader\\FoxitReader.exe /p D:\\rework\\text.pdf ");
        //D:\福昕pdf\Foxit Reader\FoxitReader.exe /p "D:\text.pdf"
            return true;  
        }catch(Exception e){  
            e.printStackTrace();  
            return false;  
        }  
    } 
	
	/**
	 * 添加返修单
	 * @throws Exception 
	 */
	@Override
	public void insertRework(CreateReworkForm createReworkForm)  {
		  Rework rework=createReworkForm.getRework();
		  Stock stock=stockMapper.selectByMaterialId(rework.getMaterialId());
	      String materialDescribe=stock.getMaterialDescribe();
	      rework.setMaterialDescribe(materialDescribe);
		 //获取系统日期填制创建时间
		  Date date=new Date();
		  rework.setCreateTime(date);
		  //获取登录人员信息
		  String workNum = rework.getCreatePersonnel();
		  String username = userInfoService.workNoToUserName(workNum);//工号转姓名
		  rework.setCreatePersonnel(username);
		  rework.setIncome(materialDescribeToName(materialDescribe)); 
		  rework.setReworkId(sysSerialNumberService.generateSerialNumberByModelCode("FX"));
		  reworkMapper.insert(rework);
		  //对关联表进行插入
		  ReworkReasonRelation[] reworkReasonRelations=createReworkForm.getReworkReasonRelation();
		  for (ReworkReasonRelation reworkReasonRelation : reworkReasonRelations) {
			  reworkReasonRelation.setReworkId(rework.getReworkId());
			  reworkReasonRelationMapper.insert(reworkReasonRelation);
		}
		  //打印返修pdf
		//  printRewokList(rework.getReworkId());
	}
	/**
	 * 打印返修pdf
	 * @param reworkId
	 */
	/*public void printRewokList(String reworkId){
		  //查询reworkView视图
		 List<ReworkView> reworkViewList= reworkViewMapper.selectByReworkId(reworkId);
		 pdfReportService.generateReworkPDF(reworkViewList);
		 //打印pdf
		// printReworkPdf();
	}*/
	
	// 截取物料描述的前两个字符串 判断是否进产线
	@Override
	public String materialDescribeToName(String materialDescribe) {
		String s = materialDescribe.substring(0, 2);
		String income="是";
		if (s.equals("钢圈")||s.equals("涂装")){
			return income;
		}else{
			income="否";
			return income;
		}
		
	}

}

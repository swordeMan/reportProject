package com.eliteams.quick4j.web.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.dao.CorrectMapper;
import com.eliteams.quick4j.web.dao.StockMapper;
import com.eliteams.quick4j.web.model.Correct;
import com.eliteams.quick4j.web.model.ReworkReason;
import com.eliteams.quick4j.web.model.Stock;
import com.eliteams.quick4j.web.model.User;
import com.eliteams.quick4j.web.service.CorrectService;
@Service
public class CorrectServiceImpl implements CorrectService{
	private static Logger log = Logger.getLogger(CorrectServiceImpl.class); // 初始化日志对象
	@Resource
	private  CorrectMapper  correctMapper;
	@Resource
	private StockMapper stockMapper;
	
	@Override
	public int insert(Object model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object selectById(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectList() {

		return null;
	}
//实现查找所有修正记录
	@Override
	public List<Correct> selectAllCorrect() {
	    List<Correct> correctList= new ArrayList<Correct>();
	    correctList = correctMapper.selectAll();
		return correctList;
	
	}
/**
 * 实现按修正单号查询
*/
	@Override
	public Correct getCorrectByCorrectId(String correctId) {
		return correctMapper.selectByCorrectId(correctId);
	}
/**
 * 实现添加修正单
 */

    @Override
    public void insertCorrect(Correct correct) {
	// TODO Auto-generated method stub
	  stockMapper.updateByMaterialId(correct);
	  Date date=new Date();
	  correct.setCreateTime(date);
	  Subject currentUser = SecurityUtils.getSubject();
	  String username = currentUser.getPrincipal().toString();
	  correct.setRevokePersonnel(username);
	  correctMapper.insert(correct);
   }
    public List<Correct> getCorrectByPage(Page page) {
		log.info("CorrectServiceImpl.getCorrectByPage");
		return correctMapper.getCorrectByPage(page);
	} 
    
    public List<Correct> getCorrectByPage(Page page,String orderByClause ,String keywords) {
		log.info("CorrectServiceImpl.getCorrectByPage");
		return correctMapper.getCorrectByPageAndKeywords(page, orderByClause,keywords);
	} 
}

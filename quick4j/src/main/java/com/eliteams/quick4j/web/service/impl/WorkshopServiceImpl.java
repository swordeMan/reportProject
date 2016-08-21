package com.eliteams.quick4j.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.dao.WorkshopMapper;
import com.eliteams.quick4j.web.model.Workshop;
import com.eliteams.quick4j.web.service.WorkshopService;
@Service
public class WorkshopServiceImpl implements WorkshopService {
	private static Logger log = Logger.getLogger(ReworkReasonServiceImpl.class); // 初始化日志对象
	@Resource
	private WorkshopMapper workshopMapper;
	/**
	 * 查看所有
	 */
	@Override
	public List<Workshop> slelectAllWorkshop(){
		List<Workshop> workshopList = new ArrayList<Workshop>();
		workshopList = workshopMapper.selectAll();
		return workshopList;
	}
  /**
   * 添加
   */
	@Override
	public void insertWorkshop(Workshop workshop) {
		workshopMapper.insert(workshop);
	}
/**
 * 删除
 */
	@Override
	public void deleteWorkshop(int id) {
		workshopMapper.deleteByPrimaryKey(id);
		
	}
/**
 * 修改车间
 */
	@Override
	public void editWorkshop(Workshop workshop) {
		workshopMapper.updateByPrimaryKey(workshop);
		
	}
	/**
	 * 分页
	 */
@Override
public List<Workshop> getWorkshopByPage(Page page) {
	log.info("WorkshopServiceImpl.getWorkshopByPage");
	return workshopMapper.getWorkshopByPage(page);
}
    /**
     * 分页
     */
@Override
public List<Workshop> getWorkshopByPage(Page page,String orderByClause,String keywords) {
	log.info("WorkshopServiceImpl.getWorkshopByPage");
	return workshopMapper.getWorkshopByPageAndKeywords(page, orderByClause,keywords);
}

	@Override
	public Workshop selectByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return workshopMapper.selectByPrimaryKey(id);
	}

}

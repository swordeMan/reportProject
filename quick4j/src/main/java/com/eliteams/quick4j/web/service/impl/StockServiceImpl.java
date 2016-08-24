package com.eliteams.quick4j.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.eliteams.quick4j.web.service.ReportYieldService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.dao.ScrapMapper;
import com.eliteams.quick4j.web.dao.StockMapper;
import com.eliteams.quick4j.web.model.Correct;
import com.eliteams.quick4j.web.model.ReportYield;
import com.eliteams.quick4j.web.model.Scrap;
import com.eliteams.quick4j.web.model.ScrapReason;
import com.eliteams.quick4j.web.model.Stock;
import com.eliteams.quick4j.web.service.StockService;

/**
 * 临时库存Service实现类
 *
 *
 * @author liuliu 2016年6月25日 下午2:52:58
 */
@Service
public class StockServiceImpl implements StockService {
	private static Logger log = Logger.getLogger(StockServiceImpl.class); // 初始化日志对象
	@Resource
	private StockMapper stockMapper;

	@Override
	public List<Stock> selectAllStock() {
		// TODO Auto-generated method stub
		List<Stock> stockList = new ArrayList<Stock>();
		stockList = stockMapper.selectAll();
		return stockList;
	}

	/**
	 * 实现按照物料编号查询
	 */
	@Override
	public Stock getStockByMaterialId(String materialId) {

		return stockMapper.selectByMaterialId(materialId);
	}

	@Override
	public List<Stock> getStockByPage(Page page) {
		log.info("StockServiceImpl.getStockByPage");
		return stockMapper.getStockByPage(page);
	}

	@Override
	public List<Stock> getStockByPage(Page page, String keywords) {
		log.info("StockServiceImpl.getStockByPage");
		return stockMapper.getStockByPageAndKeywords(page, keywords);
	}

	@Override
	public void updateByStockMaterialId(Stock stock, ReportYield reportYield) throws Exception {
		String operation = reportYield.getOperation();
		String messageType = reportYield.getMessageType();
		String userName = reportYield.getReportUsername();
		if ("S".equals(messageType)&& ReportYieldService.SYSTEM_PERSON.equals(userName)) {
			try {
				if("A".equals(operation)){
                    log.info("StockServiceImpl.updateByStockMaterialId，报工完成，更新待分配量");
                    stock.setStockNum(stock.getStockNum()-reportYield.getCurrentYield());
                    stockMapper.updateByStockMaterialId(stock);
                }else if("B".equals(operation)){
                    log.info("StockServiceImpl.updateByStockMaterialId，冲销报工完成，更新待分配量");
                    stock.setStockNum(stock.getStockNum()+reportYield.getCurrentYield());
                    stockMapper.updateByStockMaterialId(stock);
                }
			} catch (Exception e) {
				log.error("更新待分配量失败",e);
				throw e;
			}

		}else{
			log.info("StockServiceImpl.updateByStockMaterialId，报工或冲销失败");
		}
	}

	/**
	 * 通过物料简称查询实现方法
	 */
	@Override
	public List<Stock> selectStockByMaterialName(String materialName) {
		String material=extractMaterialName(materialName);
		List<Stock> stockList = new ArrayList<Stock>();
		if(material.equals("轮辋")){
		stockList=stockMapper.selectByMaterialNameOne(material);
		}
		else{
		stockList=stockMapper.selectByMaterialName(material);}
		return stockList;
	}
	/**
	 * 通过工序转化为物料描述简称
	 * @param materialName
	 * @return
	 */
	
	@Override
   public String extractMaterialName(String materialName ){
		String material=materialName.substring(2,4);
		if(material.equals("涂装")){
			material="钢圈";
		}
	    return material;
		
	}
}

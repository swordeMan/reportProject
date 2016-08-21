package com.eliteams.quick4j.web.service;

import java.util.List;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.core.generic.GenericService;
import com.eliteams.quick4j.web.model.Correct;
import com.eliteams.quick4j.web.model.ReportYield;
import com.eliteams.quick4j.web.model.ScrapReason;
import com.eliteams.quick4j.web.model.Stock;
import com.eliteams.quick4j.web.model.User;


/**
 *临时库存业务接口
 *
 * @author liuliu
 * 2016年6月25日 下午2:45:39
 */
public interface StockService {
	
	/**
	 * 查询所有待分配量信息
	 * @return
	 */
	List<Stock> selectAllStock();
    /**
     * 按照物料编号进行查询
     * @param materialId
     * @return
     */

	Stock getStockByMaterialId(String materialId);
	/**
	 * 修正引发物料待分配量更改
	 * @param correct
	 */
	List<Stock> getStockByPage(Page page);
	List<Stock> getStockByPage(Page page,String keywords);
	
	/*
	 * 根据报工返回信息判断是否需要更新
	 */
	void updateByStockMaterialId(Stock stock,ReportYield reportYield) throws Exception;
	/**
	 * 通过物料简称进行查询
	 * @param materialName 
	 * @return
	 */
	List<Stock> selectStockByMaterialName(String materialName);
	
	/**
	 * 通过工序转化为物料描述简称
	 * @param materialName
	 * @return
	 */
	String extractMaterialName(String materialName);
	
}

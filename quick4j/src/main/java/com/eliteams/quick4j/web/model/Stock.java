package com.eliteams.quick4j.web.model;
/**
 * 
 *待分配量模型
 *
 * @author liuliu
 * 2016年6月26日 上午11:46:24
 */
public class Stock {
    private Long id;

    private String materialId;

    private String materialDescribe;

    private Long totalProduction;

    private Long stockNum;
    
    public Stock(String materialId){
    	this.materialId = materialId;
    }
    
    public Stock(){
    	
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId == null ? null : materialId.trim();
    }

    public String getMaterialDescribe() {
        return materialDescribe;
    }

    public void setMaterialDescribe(String materialDescribe) {
        this.materialDescribe = materialDescribe == null ? null : materialDescribe.trim();
    }

    public Long getTotalProduction() {
        return totalProduction;
    }

    public void setTotalProduction(Long totalProduction) {
        this.totalProduction = totalProduction;
    }

    public Long getStockNum() {
        return stockNum;
    }

    public void setStockNum(Long stockNum) {
        this.stockNum = stockNum;
    }
}
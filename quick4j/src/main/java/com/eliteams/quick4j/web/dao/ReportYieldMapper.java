package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.model.ReportYield;
import com.eliteams.quick4j.web.model.UserInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportYieldMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReportYield record);

    ReportYield selectByPrimaryKey(Long id);
    
    ReportYield selectByMessageId(ReportYield record);

    List<ReportYield> selectAll();

    int updateByPrimaryKey(ReportYield record);
    
    List<ReportYield> getReportYieldByPageAndKeywords(Page<UserInfo> page,@Param("orderByClause")String orderByClause,@Param("keywords")String keywords);
    
    //找到某生产订单下的所有报工单，包括报工和冲销
    List<ReportYield> getALLReportYieldByProductOrderId(String productOrderId);
    
    List<ReportYield> getAReportYieldByProductOrderId(String productOrderId);
    
    List<ReportYield> getBReportYieldByProductOrderId(String productOrderId);
}
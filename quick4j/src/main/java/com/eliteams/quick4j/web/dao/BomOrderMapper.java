package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.web.model.BomOrder;
import com.eliteams.quick4j.web.model.OrderNode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BomOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BomOrder record);

    BomOrder selectByPrimaryKey(Long id);

    List<BomOrder> selectAll();

    int updateByPrimaryKey(BomOrder record);
    
  //根据生产订单号去bom_order中查询组件物料
	OrderNode getBomOrderByProductOrderId(String productOrderId);
}
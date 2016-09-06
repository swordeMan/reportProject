package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.web.model.ProductChanged;
import java.util.List;

public interface ProductChangedMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductChanged record);

    ProductChanged selectByPrimaryKey(Long id);

    List<ProductChanged> selectAll();

    int updateByPrimaryKey(ProductChanged record);
    
    ProductChanged selectByDeviceId(Integer deviceId);
}
package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.web.model.ProductingView;
import java.util.List;

public interface ProductingViewMapper {
    int insert(ProductingView record);

    List<ProductingView> selectAll();
    
    List<ProductingView> getMaterialInfoBydeviceDesc(Integer deviceId);
}
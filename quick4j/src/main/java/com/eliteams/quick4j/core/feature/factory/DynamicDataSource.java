package com.eliteams.quick4j.core.feature.factory;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {  
    @Override  
    protected Object determineCurrentLookupKey() {  
        return DataSourceContextHolder.getDbType();  
    }  
}
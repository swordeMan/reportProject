package com.eliteams.quick4j.web.dao;

import com.eliteams.quick4j.web.model.DeviceInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeviceInfo record);

    DeviceInfo selectByPrimaryKey(Integer id);

    List<DeviceInfo> selectAll();

    int updateByPrimaryKey(DeviceInfo record);
    
    //根据工序id查询工序描述
  	DeviceInfo selectDeiviceDescribeByDeviceId(Integer deviceId);
}
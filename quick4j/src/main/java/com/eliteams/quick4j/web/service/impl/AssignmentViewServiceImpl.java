package com.eliteams.quick4j.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eliteams.quick4j.web.dao.AssignmentViewMapper;
import com.eliteams.quick4j.web.model.AssignmentView;
import com.eliteams.quick4j.web.service.AssignmentViewService;
@Service
public class AssignmentViewServiceImpl implements AssignmentViewService {
	
	@Resource
	private AssignmentViewMapper assignmentViewMapper;
	
	@Override
	public List<AssignmentView> getMaterialIdAndDescribeByDeviceDes(Integer deviceId) {
		return assignmentViewMapper.getMaterialIdAndDescribeByDeviceDes(deviceId);
	}

	@Override
	public List<String> getUserSimpleNameByDeviceDes(Integer deviceId) {
		return assignmentViewMapper.getUserSimpleNameByDeviceDes(deviceId);
	}

	@Override
	public AssignmentView selectAssignmentViewById(Long id) {
		return assignmentViewMapper.selectAssignmentViewById(id);
	}

}

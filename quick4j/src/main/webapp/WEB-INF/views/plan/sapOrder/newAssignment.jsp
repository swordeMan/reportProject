<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div class="pageContent">
	<form id="createAssignmentForm" method="post" action="rest/assignment/insert?callbackType=closeCurrent" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<%-- <input type="hidden" name="id" value="${sapOrder.id}"/> --%>
		<input type="hidden" name="assignment.saleOrderId" value="${sapOrder.saleOrderId}"/>
		<input type="hidden" name="assignment.userSimpleName" value="${sapOrder.userSimpleName}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>生产订单：</label>
				<input name="assignment.productOrderId" readonly="readonly" type="text" value="${sapOrder.productOrderId}" size="30"/>
			</p>
			<p>
				<label>物料编码：</label>
				<input name="assignment.materialId" readonly="readonly" type="text" value="${sapOrder.materialId}" size="30"/>
			</p>
			<p>
				<label>物料描述：</label>
				<input name="assignment.materialDescribe" readonly="readonly" type="text" value="${sapOrder.materialDescribe}" size="30"/>
			</p>
			
			<!-- <p>
				<label>班次：</label>
				<div id="combox_1059839" class="select" size="30">
					<a class="required" href="javascript:;" name="classes" value="早"></a>
					<select class="required" name="scrap.classes">
						<option value="">请选择</option>
						<option value="早">早</option>
						<option value="中">中</option>
						<option value="晚">晚</option>
					</select>
			</p> -->
			<p>
				<label>下达日期：</label>
				<input name="assignment.cmdTime" type="text" class="date" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>" readonly="false"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p>
				<label>下达人：</label>
				<input name="assignment.cmdPerson" style="width:16%" type="text" value="${userInfo.username}" readonly="readonly" size="30"/>
			</p>
			<p>
				<label>目标量：</label>
				<input type="text" value="${sapOrder.targetSum}" readonly="readonly" size="30"/>
			</p>
			<div class="divider"></div>
			<table class="list nowrap itemDetail" addButton="新增任务明细" width="100%">
				<thead>
					<tr>
						<th type="enum" name="assignments[#index#].deviceId" enumUrl="rest/device/page" size="12" fieldclass="required">工序</th>
						<th type="text" name="assignments[#index#].assignmentNum" defaultval="0">计划任务量</th>
						<th type="text" name="assignments[#index#].classes">班次</th>
						<th type="date" name="assignments[#index#].planTime" defaultval="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>">起始时间</th>
						<th type="del" width="25">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr class="unitBox">
						<td>
							<select name="assignments[0].deviceId" style="width:60%">
								<c:forEach var="item" items="${deviceInfoList}" varStatus="s">
									<option value="${item.deviceId}" selected="selected">${item.deiviceDescribe}</option>
								</c:forEach>
							</select>
						</td>
						<td><input size="12" type="text" name="assignments[0].assignmentNum" value="0"></td>
						<td><input size="12" type="text" name="assignments[0].classes"></td>
						<td><input size="12" type="text" name="assignments[0].planTime" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>" class="date"/>
								<a class="inputDateButton" href="javascript:;">选择</a></td>
						<td><a href="javascript:void(0)" class="btnDel ">删除</a></td>
					</tr>
				</tbody>
			</table>
			<%-- <table class="list nowrap itemDetail">
				<thead>
					<tr>
						<th colspan="10">具体任务下达</th>
					</tr>
					<tr>
						<th type="text" name="assignments[#index#].deviceId">设备号</th>
						<th type="text" name="assignments[#index#].assignmentNum">计划任务量</th>
						<th type="text" name="assignments[#index#].classes">班次</th>
						<th type="text" name="assignments[#index#].startTime">起始时间</th>
						<th type="del" width="25">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${deviceInfoList}" varStatus="s">
						<tr class="unitBox">
							<td><input size="5" type="text" name="assignments[${s.index}].deviceId" value="${item.deviceId}" readonly="readonly"></td>
							<td><input size="12" type="text" name="assignments[${s.index}].assignmentNum" value="0" class="required"></td>
							<td><input size="12" type="text" name="assignments[${s.index}].classes" class="required"></td>
							<td><input size="8" type="text" name="assignments[${s.index}].startTime" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>" class="date required"/>
								<a class="inputDateButton" href="javascript:;">选择</a></td>
							<td><a href="javascript:void(0)" class="btnDel ">删除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table> --%>
		</div>
		<c:import url="../../include/formBar.jsp"></c:import>
	</form>
</div>


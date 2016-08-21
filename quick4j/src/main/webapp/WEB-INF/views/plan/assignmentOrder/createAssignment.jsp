<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<script type="text/javascript">

$(".materialId").select2();
//$('.deiviceDescribe').select2();
var materialDescribe;

//得到待下达任务列表
function getSapOrderList(obj){
	var i = 0;
	if(obj == "")
		return false;
	$("#assDetailTab tbody").empty();
	<c:forEach var="sapOrder" items="${sapOrderList}" varStatus="s">
		if("${sapOrder.materialId}" == obj){
			$("#assDetailTab tbody").append($('<tr class="unitBox"><td><input size="15" type="text" name="assignments['+i+'].saleOrderId" value="'+"${sapOrder.saleOrderId}"+'" readonly="readonly"></td><td><input size="8" type="text" value="'+"${sapOrder.saleOrderRow}"+'" readonly="readonly"></td><td><input size="8" type="text" value="'+"${sapOrder.targetSum}"+'" readonly="readonly"></td><td><input size="8" type="text" value="'+"${sapOrder.finishedTotal}"+'" readonly="readonly"></td><td><input size="15" type="text" name="assignments['+i+'].productOrderId" value="'+"${sapOrder.productOrderId}"+'" readonly="readonly"></td><td><input size="20" type="text" name="assignments['+i+'].materialDescribe" value="'+"${sapOrder.materialDescribe}"+'" readonly="readonly"></td><td><input size="10" type="text" name="assignments['+i+'].userSimpleName" value="'+"${sapOrder.userSimpleName}"+'" readonly="readonly"></td><td><a href="javascript:void(0)" class="btnDel ">删除</a></td></tr>'));
			i++;
			materialDescribe = "${sapOrder.materialDescribe}";
		}
	</c:forEach>
	
	var s = materialDescribeToName(materialDescribe);
	autoDeviceDesc(s);
	
	/* 删除按钮点击事件 */
	$(".btnDel").click(function(){
		var $btnDel = $(this);
		var $tbody = $("#assDetailTab tbody");
		if ($btnDel.is("[href^=javascript:]")){
			$btnDel.parents("tr:first").remove();
			initSuffix($tbody);
			return false;
		}
	});
}


function initSuffix($tbody) {
	$tbody.find('>tr').each(function(i){
		$(':input', this).each(function(){
			var $this = $(this), name = $this.attr('name'), val = $this.val();

			if (name) $this.attr('name', name.replaceSuffix(i));

			if (val && val.indexOf("#index#") >= 0) $this.val(val.replace('#index#',i+1));
		});
	});
}


//截取字符串前两位
function materialDescribeToName(obj){
	var s = obj.substring(0,2);
	return s;
}

//根据物料描述联动出相应工序
function autoDeviceDesc(obj){
	if(obj=="钢圈"){
		obj="涂装";
	}
	var reg=new RegExp(obj);
	$(".deiviceDescribe").empty();
	<c:forEach var="deviceInfo" items="${deviceInfoList}" varStatus="s">
		if(reg.test("${deviceInfo.deiviceDescribe}")){
			$(".deiviceDescribe").append($('<option value="'+"${deviceInfo.deviceId}"+'">'+"${deviceInfo.deiviceDescribe}"+'</option>'));
		}
	</c:forEach>
}

</script>

<div class="pageContent">
	<form id="createAssignmentForm" method="post" action="rest/assignment/save?callbackType=closeCurrent" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<!-- <input type="hidden" name="assignment.userSimpleName" class="userSimpleName"/>  -->
		<div class="pageFormContent" layoutH="56">
			<%-- <p>
				<label>生产订单：</label>
				<input name="assignment.productOrderId" readonly="readonly" type="text" value="${sapOrder.productOrderId}" size="30"/>
			</p> --%>
			<p>
				<label>物料编码：</label>
				<select class="materialId required" name="assignment.materialId" style="width:60%" onchange="getSapOrderList(this.value)">
					<c:forEach var="item" items="${materialIdList}" varStatus="s">
						<option value="${item}" selected="selected">${item}</option>
					</c:forEach>
				</select>
			</p>
			<%-- <p>
				<label>物料描述：</label>
				<input name="assignment.materialDescribe" readonly="readonly" type="text" value="${sapOrder.materialDescribe}" size="30"/>
			</p> --%>
			
			<p>
				<label>工序：</label>
				<select class="deiviceDescribe" name="assignment.deviceId" style="width:60%">
					<%-- <c:forEach var="item" items="${deviceInfoList}" varStatus="s">
						<option value="${item.deviceId}" selected="selected">${item.deiviceDescribe}</option>
					</c:forEach> --%>
				</select>
			</p>
			<p>
				<label>下达日期：</label>
				<input name="assignment.cmdTime" type="text" class="date" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>" readonly="false"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p>
				<label>下达人：</label>
				<input name="assignment.cmdPerson" style="width:16%" type="text" value="${userInfo.username}" readonly="readonly" size="30"/>
			</p>
			<%-- <p>
				<label>目标量：</label>
				<input type="text" value="${sapOrder.targetSum}" readonly="readonly" size="30"/>
			</p> --%>
			<div class="divider"></div>
			<table id="assDetailTab" class="list nowrap itemDetail">
				<thead>
					<tr>
						<th colspan="10">待下达任务列表</th>
					</tr>
					<tr>
						<th type="text"  name="assignments[#index#].saleOrderId">销售订单</th>
						<th type="text">销售订单行</th>
						<th type="text">目标量</th>
						<th type="text">完成量</th>
						<th type="text"  name="assignments[#index#].productOrderId">生产订单</th>
						<th type="text"  name="assignments[#index#].materialDescribe">物料描述</th>
						<th type="text"  name="assignments[#index#].userSimpleName">客户简称</th>
						<th type="del" width="25">操作</th>
					</tr>
				</thead>
				<tbody>
						<%-- <tr class="unitBox">
							<td><input size="25" type="text" name="assignments[${s.index}].saleOrderId" readonly="readonly"></td>
							<td><input size="22" type="text" name="assignments[${s.index}].productOrderId" readonly="readonly"></td>
							<td><input size="28" type="text" name="assignments[${s.index}].materialDescribe" readonly="readonly"></td>
							<td><input size="28" type="text" name="assignments[${s.index}].userSimpleName" readonly="readonly"></td>
							<td><a href="javascript:void(0)" class="btnDel ">删除</a></td>
						</tr> --%>
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


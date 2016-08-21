<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../include/include.inc.jsp"%>
<script type="text/javascript">
var scrapTime;

function getMaterialDes(){
	var url="rest/scrap/getMaterialDescribeList";//请求后台的url
	$(".describe").empty();//先置空
	$.post(url,function(data){
		$.each(data,function(i,s){
			//物料描述数据加到下拉框
			$(".describe").append($('<option value="'+s+'">'+s+'</option>'));
		});
	});
	$(".describe").select2();
}

function getUserSimpleName(){
	var url="rest/scrap/getUserSimpleName";//请求后台的url
	$(".customer").empty();//先置空
	$.post(url,function(data){
		$.each(data,function(i,s){
			//用户简称数据加到下拉框
			$(".customer").append($('<option value="'+s+'">'+s+'</option>'));
		});
	});
	$(".customer").select2();
}


</script>

<div class="pageHeader">
	<form onsubmit="return dialogSearch(this);" action="rest/assignment/getAssignmentList" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>报废日期:</label>
				<input type="text" name="startTime" class="date textInput required" datefmt="yyyy-MM-dd HH:mm:ss" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</li>	  
			<li>
				<label>物料描述:</label>
				<select class="describe required textInput" name="materialDescribe" style="width:60%" onfocus="getMaterialDes()">
					<%-- <c:forEach var="item" items="${materialDescribeList}" varStatus="s">
						<option value="${item}" selected="selected">${item}</option>
					</c:forEach> --%>
				</select>
			</li>
			<li>
				<label>客户简称:</label>
				<select class="customer textInput" name="userSimpleName" style="width:60%" onfocus="getUserSimpleName()">
					<%-- <c:forEach var="item" items="${userSimpleNameList}" varStatus="s">
						<option value="${item}" selected="selected">${item}</option>
					</c:forEach> --%>
				</select>
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div id = "btn1" class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a id="new" class="add" target="dialog" mask="true" max="true" width="800" height="470" rel="newScrapNav" warn="请选择一个生产订单" href="rest/sapOrder/query/{slt_objId}?startTime=${startTime}" title="新建报废单"><span>新建报废单</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" id="scrapTab" layoutH="138">
		<thead>
			<tr>
				<th width="25"></th>
				<th orderField="user_simple_name" width="230">客户简称</th>
				<th orderField="sale_order_id" width="230">销售订单</th>
				<th orderField="product_order_id" width="230">生产订单</th>
				<th orderField="material_id" width="230">物料编码</th>
				<th orderField="material_describe" width="230">物料描述</th>
				<th orderField=start_time width="230">订单开始时间</th>
				<th orderField="finished_total" width="230">完成量</th>
				<th orderField="waste_total" width="230">报废量</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${assignmentList}" varStatus="s">
			<tr target="slt_objId" rel="${item.productOrderId}">
				<td>${s.index + 1}</td>
				<td>${item.userSimpleName}</td>
				<td>${item.saleOrderId }</td>
				<td>${item.productOrderId }</td>
				<td>${item.materialId }</td>
				<td>${item.materialDescribe }</td>
				<td><fmt:formatDate value="${item.startTime }" pattern="yyyy-MM-dd"/></td>
				<td>${item.finishedTotal }</td>
				<td>${item.wasteTotal }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="formBar">
		<ul>
			<li>
				<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
			</li>
		</ul>
	</div>
</div>

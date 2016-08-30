<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<form id="pagerForm" method="post" action="rest/assignment/list">
	<input type="hidden" name="pageNo" value="1" />
	<input type="hidden" name="pageSize" value="${page.pageSize}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
	<input type="hidden" name="deviceId" value="${param.deviceId}" />
</form>

<script type="text/javascript">
$(function(){
	var orderField = "${orderField}"||null;
	if(orderField!=null){
		var orderDirection = "${orderDirection}";
		$('th[orderField="'+orderField+'"]').addClass(orderDirection);
	}
});

/* function verify(startTime){
	if(startTime!=null && startTime!=""){
		//alertMsg.warn("该任务已启动，禁止操作！");
		$('.delete').hide();
	}else{
		$('.delete').show();
	}
} */

</script>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="rest/assignment/list" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>关键词：</label>
				<input type="text" name="keywords" value="${keywords}"  alt="模糊搜索"/>
				
				<input type="hidden" name="deviceId" value="${param.deviceId}" />
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<!-- <li><a class="add" target="dialog" mask="true" width="980" height="490" href="rest/assignment/getDeviceInfo"><span>任务下达</span></a></li> -->
			<!-- <li><a class="edit" target="ajaxTodo" warn="请选择一个任务" href="rest/assignment/startAssignment/{slt_objId}" title="你确定要启动该条任务吗?"><span>任务启动</span></a></li> -->
			<li><a class="edit" target="ajaxTodo" warn="请选择一个任务" href="rest/assignment/first/{slt_objId}" title="你确定要优先执行该条任务吗?"><span>优先执行</span></a></li>
			<li><a class="add" target="dialog" mask="true" width="800" height="470" rel="newScrapNav" warn="请选择一条任务" href="<c:url value='rest/assignment/queryAssignmentView/{slt_objId}'/>" title="新建报废单"><span>新建报废单</span></a></li>
			<li><a class="delete" target="ajaxTodo" href="rest/assignment/deleteAssignment/{slt_objId}" warn="请选择一条任务" title="你确定要删除吗?"><span>任务删除</span></a></li>
		</ul>
	</div>
	<table class="table" layoutH="138">
		<thead>
			<tr>
				<th width="20"></th>
				<th orderField="product_order_id" width="120">生产订单号</th>
				<!-- <th width="85">订单类型</th> -->
				<th orderField="sale_order_id" width="100">销售订单号</th>
				<th orderField="sale_order_row" width="80">销售订单行</th>
				<th orderField="user_simple_name" width="80">客户简称</th>
				<th orderField="target_sum" width="50">目标量</th>
				<th orderField="finished_total" width="50">完成量</th>
				<!-- <th width="90">客户简称</th> -->
				<!-- <th width="85">生产版本</th> -->
				<th orderField="material_id" width="120">物料编码</th>
				<th orderField="material_describe" width="180">物料描述</th>
				<th orderField="deivice_describe" width="80">工序</th>
				<th orderField="cmd_person" width="80">下达人</th>
				<!-- <th orderField="assignment_num" width="100">计划任务量</th>
				<th orderField="classes" width="50">班次</th> -->
				<!-- <th width="40">单位</th> -->
				<th orderField="cmd_time" width="90">下达日期</th>
				<th orderField="start_time" width="130">启动时间</th>
				<!-- <th width="40">删除标记</th> -->
				<!-- <th width="150">状态</th> -->
				<!-- <th width="85">新建时间</th> -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${page.result}" varStatus="s">
			<tr target="slt_objId" rel="${item.id}" onclick="verify('${item.startTime}')">
				<td>${s.index + 1}</td>
				<td>${item.productOrderId}</td>
				<%-- <td>${item.productOrderType}</td> --%>
				<td>${item.saleOrderId}</td>
				<td>${item.saleOrderRow}</td>
				<td>${item.userSimpleName}</td>
				<td>${item.targetSum}</td>
				<td>${item.finishedTotal}</td>
				<%-- <td>${item.manufactureVersion}</td> --%>
				<td>${item.materialId}</td>
				<td>${item.materialDescribe}</td>
				<td>${item.deiviceDescribe}</td>
				<%-- <td>${item.assignmentNum}</td>
				<td>${item.classes}</td> --%>
				<%-- <td>${item.unit}</td> --%>
				<td>${item.cmdPerson}</td>
				<td><fmt:formatDate value="${item.cmdTime}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<%-- <td>${item.delRemark}</td> --%>
				<%-- <td>${item.state}</td> --%>
				<%-- <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/></td> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<%-- <div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="pageSize" onchange="navTabPageBreak({numPerPage:this.value})">
				<c:forEach begin="20" end="80" step="20" varStatus="s">
				<option value="${s.index}" ${page.pageSize eq s.index ? 'selected="selected"' : ''}>${s.index}</option>
			</c:forEach>
			</select>
			<span>条，共${page.totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${page.totalCount}" numPerPage="${page.pageSize}" pageNumShown="10" currentPage="${page.pageNo}"></div>

	</div> --%>
	<c:import url="../../include/panelBar.jsp"></c:import>
</div>

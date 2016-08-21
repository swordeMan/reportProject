<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../include/include.inc.jsp"%>
<form id="pagerForm" method="post" action="rest/sapOrder/list">
	<input type="hidden" name="pageNo" value="1" />
	<input type="hidden" name="pageSize" value="${page.pageSize}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<script type="text/javascript">
$(function(){
	var orderField = "${orderField}"||null;
	if(orderField!=null){
		var orderDirection = "${orderDirection}";
		$('th[orderField="'+orderField+'"]').addClass(orderDirection);
	}
});

</script>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="rest/sapOrder/list" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>关键词：</label>
				<input type="text" name="keywords" value="${keywords}" alt="模糊搜索"/>
			</li>
			<!-- <li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li> -->
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<shiro:hasPermission name="order:create">
				<li><a class="add" href="rest/sapOrder/testInsert?navTabId=productOrderNav" target="ajaxTodo" title="你确定要获取生产订单吗?"><span>获取生产订单</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="report:hand">
				<li><a class="edit" target="dialog" mask="true" width="1100" height="470" rel="reportByHandNav" warn="请选择一个销售订单" href="<c:url value='rest/reportYield/reportByHand/{slt_objId}'/>" title="产量填报单"><span>手工上报</span></a></li>
			</shiro:hasPermission>
			<%-- <shiro:hasPermission name="scrap:create">
				<li><a class="add" target="dialog" mask="true" width="800" height="470" rel="newScrapNav" warn="请选择一个生产订单" href="<c:url value='rest/sapOrder/query/{slt_objId}'/>" title="新建报废单"><span>新建报废单</span></a></li>
			</shiro:hasPermission> --%>
			<shiro:hasPermission name="progress:view">
				<li><a class="edit" target="dialog" max="true" warn="请选择一个生产订单" href="<c:url value='rest/sapOrder/orderProcess/{slt_objId}'/>" title="订单进度"><span>订单详情</span></a></li>
			</shiro:hasPermission>
			<%-- <shiro:hasPermission name="task:create">
				<li><a class="add" target="dialog" mask="true" width="800" height="470" rel="newScrapNav" warn="请选择一个生产订单" href="<c:url value='rest/sapOrder/getSapOrderInfo/{slt_objId}'/>" title="任务下达"><span>任务下达</span></a></li>
			</shiro:hasPermission> --%>
			
			<!-- <li><a class="delete" target="ajaxTodo" href="rest/user/delete/{slt_objId}" title="你确定要删除吗?"><span>删除</span></a></li> -->
			<li class="line">line</li>
			<li><a class="icon" href="rest/sapOrder/exportExcle" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			<!-- <li><a class="icon" href="javascript:$.printBox('list_print')" ><span>打印</span></a></li> -->
		</ul>
	</div>
	<div id="list_print">
	<table class="table" layoutH="138">
		<thead>
			<tr>
				<th width="20"></th>
				<th orderField="product_order_id" width="110">生产订单号</th>
				<!-- <th width="85">订单类型</th> -->
				<th orderField="sale_order_id" width="100">销售订单号</th>
				<th width="80">销售订单行</th>
				<th orderField="user_simple_name" width="90">客户简称</th>
				<!-- <th width="85">生产版本</th> -->
				<th orderField="material_id" width="110">物料编码</th>
				<th orderField="material_describe" width="180">物料描述</th>
				<th orderField="target_sum" width="80">目标数量</th>
				<th orderField="finished_total" width="50">完成数</th>
				<th orderField="waste_total" width="80">确认废品数</th>
				<th orderField="relate_scarp" width="80">关联废品数</th>
				<!-- <th width="40">单位</th> -->
				<th orderField="plan_start_date" width="85">计划开始日期</th>
				<th orderField="plan_end_date" width="85">计划完成日期</th>
				<!-- <th width="40">删除标记</th> -->
				<!-- <th orderField="state" width="150">状态</th> -->
				<!-- <th width="85">新建时间</th> -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${page.result}" varStatus="s">
			<tr target="slt_objId" rel="${item.productOrderId}">
				<td>${s.index + 1}</td>
				<td>${item.productOrderId}</td>
				<%-- <td>${item.productOrderType}</td> --%>
				<td>${item.saleOrderId}</td>
				<td>${item.saleOrderRow}</td>
				<td>${item.userSimpleName}</td>
				<%-- <td>${item.manufactureVersion}</td> --%>
				<td>${item.materialId}</td>
				<td>${item.materialDescribe}</td>
				<td>${item.targetSum}</td>
				<td>${item.finishedTotal}</td>
				<td>${item.wasteTotal}</td>
				<td>${item.relateScarp}</td>
				<%-- <td>${item.unit}</td> --%>
				<td><fmt:formatDate value="${item.planStartDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${item.planEndDate}" pattern="yyyy-MM-dd"/></td>
				<%-- <td>${item.delRemark}</td> --%>
				<%-- <td>${item.state}</td> --%>
				<%-- <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/></td> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
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

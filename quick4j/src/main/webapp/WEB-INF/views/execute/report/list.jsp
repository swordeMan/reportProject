<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../include/include.inc.jsp"%>
<form id="pagerForm" method="post" action="rest/reportYield/list">
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

function isReportSW(operation,type) {
	//报工类型，成功与警告
	if((operation=="A"&&type=="S")||(operation=="A"&&type=="W")){
		return true;
	}else{
		return false;
	}
}

function isCancelE(operation,type) {
	if(operation=="B"&&type=="E"){
		return true;
	}else {
		return false;
	}
}

function cancelVerify(operation,type) {
	if(isReportSW(operation,type)||isCancelE(operation,type)){
		$('.delete').show();
	}else {
		$('.delete').hide();
	}

}

</script>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="rest/reportYield/list" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>关键词：</label>
				<input type="text" name="keywords" value="${keywords}"  alt="模糊搜索"/>
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
			<!-- <li><a class="edit" target="navTab" rel="" warn="请选择一个报工" title="进度查看"><span>进度查看</span></a></li> -->
			<shiro:hasPermission name="report:cancle">
				<li><a class="delete" target="ajaxTodo" rel="reportNav" warn="请选择一个报工" href="<c:url value='rest/reportYield/cancelReportYield/{slt_objId}'/>" title="你确定要冲销该条报工吗?"><span>报工冲销</span></a></li>
			</shiro:hasPermission>
			<li class="line">line</li>
			<li><a class="icon" href="rest/reportYield/exportExcle" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" layoutH="138">
		<thead>
			<tr>
				<th width="20"></th>
				<th orderField="message_id" width="120">报工id</th>
				<th orderField="product_order_id" width="120">生产订单</th>
				<th orderField="sale_order_id" width="100">销售订单</th>
				<!-- <th width="80">销售订单行</th> -->
				<th orderField="material_id" width="100">物料编码</th>
				<th orderField="material_describe" width="180">物料描述</th>
				<th orderField="current_yield" width="80">报工量</th>
				<!-- <th orderField="current_waste" width="80">报废量</th> -->
				<th orderField="message_type" width="100">SAP类型</th>
				<th orderField="message" width="300">SAP消息文本</th>
				<th orderField="report_username" width="100">报工人</th>
				<th orderField="account_date" width="150">记账日期</th>
				<th orderField="create_time" width="150">报工时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${page.result}" varStatus="s">
			<tr target="slt_objId" rel="${item.id}" onclick="cancelVerify('${item.operation}','${item.messageType}')">
				<td>${s.index + 1}</td>
				<td>${item.messageId}</td>
				<td>${item.productOrderId}</td>
				<td>
				<c:set var="saleOrderRow" value="${item.saleOrderRow}"/>  
					<c:choose>  
					    <c:when test="${fn:length(saleOrderRow) > 5}">  
					        <c:out value="${item.saleOrderId}/${fn:substring(saleOrderRow, 4, 6)}" />  
					    </c:when>  
					   	<c:otherwise>  
					     	<c:out value="${item.saleOrderId}" />  
					    </c:otherwise>  
					</c:choose>
				</td>
				<%-- <td>${item.saleOrderRow}</td> --%>
				<td>${item.materialId}</td>
				<td>${item.materialDescribe}</td>
				<td>${item.currentYield}</td>
				<%-- <td>${item.currentWaste}</td> --%>
				<td>
					<c:if test="${item.messageType=='S'}">
						成功
					</c:if>
					<c:if test="${item.messageType=='E'}">
						失败
					</c:if>
				</td>
				<td>${item.message}</td>
				<td>${item.reportUsername}</td>
				<td><fmt:formatDate value="${item.accountDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd  HH:mm:ss"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:import url="../../include/panelBar.jsp"></c:import>
</div>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../include/include.inc.jsp"%>
<form id="pagerForm" method="post" action="#rel#">
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
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="rest/correct/list">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>关键词：</label> <input type="text" name="keywords" value="${keywords}" 
					alt="模糊搜索" /></li>
			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
					<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<shiro:hasPermission name="correct:detail">
				<li><a class="edit" target="dialog" mask="true" rel="CorrectDetailtNav" width="815" height="270" warn="请选择一个修正单" href="<c:url value='rest/correct/query/{slt_objId}'/>" title="查看详情"><span>查看详情</span></a></li>
			</shiro:hasPermission>
			<!-- <li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li> -->
		</ul>
	</div>
	<table class="table" layoutH="138">
		<thead>
			<tr>
				<th width="25"></th>
				<th orderField="correct_id" width="120">修正单编号</th>
				<th orderField="material_id" width="120">物料编码</th>
				<th orderField="material_describe" width="80">物料描述</th>
				<th orderField="correct_num" width="80">修正数量</th>
				<th orderField="revoke_personnel" width="80">修正人员</th>
				<th orderField="create_time" width="100">修正日期</th>
				<th orderField="create_reason" width="200">修正原因</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${page.result}" varStatus="s">
			<tr target="slt_objId" rel="${item.correctId}">
				<td>${s.index + 1}</td>
				<td>${item.correctId}</td>
				<td>${item.materialId }</td>
				<td>${item.materialDescribe }</td>
				<td>${item.correctNum }</td>
				<td>${item.revokePersonnel}</td>
				<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${item.createReason }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
		<c:import url="../../include/panelBar.jsp"></c:import>
</div>

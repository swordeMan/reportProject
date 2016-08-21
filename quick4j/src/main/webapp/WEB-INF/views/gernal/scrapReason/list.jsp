<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../include/include.inc.jsp"%>
<form id="pagerForm" method="post" action="rest/scrapReason/list">
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
		action="rest/scrapReason/list">
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
			<shiro:hasPermission name="scrapReason:create">
				<li><a class="add" href="rest/scrapReason/toAdd" mask="true"
				height="350" target="dialog"><span>添加</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="scrapReason:update">
				<li><a class="edit" target="dialog" mask="true" height="310"
				warn="请选择一个报废原因"
				href="<c:url value='rest/scrapReason/qureyById/{slt_objId}'/>"
				title="编辑报废原因"><span>编辑</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="scrapReason:delete">
				<li><a class="delete" target="ajaxTodo"
				href="rest/scrapReason/delete/{slt_objId}" title="你确定要删除吗?"><span>删除</span></a></li>
			</shiro:hasPermission>
			<li class="line">line</li>
			<!-- <li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li> -->
		</ul>
	</div>
	<table class="table" layoutH="138">
		<thead>
			<tr>

				<th width="20"></th>
				<th orderField="material_name" width="80">工序</th>
				<th orderField="s_reason" width="80">报废原因</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${page.result}" varStatus="s">
				<tr target="slt_objId" rel="${item.id}">

					<td>${s.index + 1}</td>
					<td>${item.materialName}</td>
					<td>${item.sReason}</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../include/panelBar.jsp"></c:import>
</div>

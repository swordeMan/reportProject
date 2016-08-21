<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../include/include.inc.jsp"%>
<form id="pagerForm" method="post" action="rest/reworkReason/list">
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
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"	action="rest/reworkReason/list" method="post">
	<div class="searchBar">
			<ul class="searchContent">
				<li><label>关键词：</label>
			    <input type="text" name="keywords"	value="${keywords}" alt="模糊搜索" />
			    </li>
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
			<shiro:hasPermission name="reworkReason:create">
				<li><a class="add" href="rest/reworkReason/toAdd" mask="true"
				height="350" target="dialog"><span>添加</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="reworkReason:update">
				<li><a class="edit" target="dialog" mask="true" height="310"
				warn="请选择一个返修原因"
				href="<c:url value='rest/reworkReason/qureyId/{slt_objId}'/>"
				title="编辑返修原因"><span>编辑</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="reworkReason:delete">
				<li><a class="delete" target="ajaxTodo"
				href="rest/reworkReason/delete/{slt_objId}" title="你确定要删除吗?"><span>删除</span></a></li>
			</shiro:hasPermission>
			
			<li class="line">line</li>
			<!-- <li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li> -->
		</ul>
	</div>
	<table class="table" layoutH="138">
		<thead>
			<tr>

				<th width="20"></th>
				<th orderField="category" width="80">工序</th>
				<th orderField="reason" width="80">返修原因</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${page.result}" varStatus="s">
				<tr target="slt_objId" rel="${item.id}">

					<td>${s.index + 1}</td>
					<td>${item.category}</td>
					<td>${item.reason}</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
<c:import url="../../include/panelBar.jsp"></c:import>
</div>

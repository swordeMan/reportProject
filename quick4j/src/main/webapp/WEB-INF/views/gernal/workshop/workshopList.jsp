<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../include/include.inc.jsp"%>
<form id="pagerForm" method="post" action="rest/workshop/workshopList">
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
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"	action="rest/workshop/workshopList" method="post">
	<div class="searchBar">
			<ul class="searchContent">
				<li><label>关键词：</label>
			    <input type="text" name="keywords" value="${keywords}" 	alt="模糊搜索" />
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
			<shiro:hasPermission name="workshop:create">
				<li><a class="add" href="rest/workshop/toAdd" mask="true" height="350" target="dialog"><span>添加</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="workshop:update">
				<li><a class="edit" target="dialog" mask="true" height="310" warn="请选择一个工厂" href="rest/workshop/qureyId/{slt_objId}" title="编辑车间"><span>编辑</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="workshop:delete">
				<li><a class="delete" target="ajaxTodo" href="rest/workshop/delete/{slt_objId}" title="你确定要删除吗?"><span>删除</span></a></li>
			</shiro:hasPermission>
			<li class="line">line</li>
			<!-- <li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li> -->
		</ul>
	</div>
	<table class="table" layoutH="138">
		<thead>
			<tr>
				<th width="20"></th>
				<th orderField="workshop" width="200">车间</th>
			</tr>
			<tr>
			   <th width="40"></th>
			   <th orderField="print" width="200"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${page.result}" varStatus="s">
				<tr target="slt_objId" rel="${item.id}">

					<td>${s.index + 1}</td>
					<td>${item.workshop}</td>
                    <td>${item.print}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
<c:import url="../../include/panelBar.jsp"></c:import>
</div>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> --%>
<%@ include file="../../include/include.inc.jsp"%>
<form id="pagerForm" method="post" action="rest/role/list">
	<input type="hidden" name="pageNo" value="1" />
	<input type="hidden" name="pageSize" value="${page.pageSize}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="rest/role/list" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>关键词：</label>
				<input type="text" name="keywords" alt="模糊搜索"/>
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
			<shiro:hasPermission name="role:create">
				<li><a class="add" href="rest/role/add" mask="true" height="650" width="750" target="dialog"><span>添加角色</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="role:update">
				<li><a class="edit" target="dialog" mask="true" height="650" width="750" warn="请选择一个角色" href="<c:url value='rest/role/edit/{slt_objId}'/>" title="编辑角色"><span>编辑</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="role:delete">
				<li><a class="delete" target="ajaxTodo" href="rest/role/delete/{slt_objId}" title="你确定要删除吗?"><span>删除</span></a></li>
			</shiro:hasPermission>
			<li class="line">line</li>
			<!-- <li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li> -->
		</ul>
	</div>
	<table class="table" layoutH="138">
		<thead>
			<tr>
				<th width="20"></th>
				<th width="80">角色名</th>
				<th width="420">角色描述</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${page.result}" varStatus="s">
			<tr target="slt_objId" rel="${item.id}">
				<td>${s.index + 1}</td>
				<td>${item.roleName}</td>
				<td>${item.description}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:import url="../../include/panelBar.jsp"></c:import>
</div>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../include/include.inc.jsp"%>
<form id="pagerForm" method="post" action="rest/user/list">
	<input type="hidden" name="pageNo" value="1" />
	<input type="hidden" name="pageSize" value="${page.pageSize}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="rest/user/list" method="post">
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
			<shiro:hasPermission name="user:create">
				<li><a class="add" href="rest/user/add" mask="true" height="450" width="700" target="dialog"><span>添加</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="user:update">
				<li><a class="edit" target="dialog" mask="true" height="450" width="700" rel="userNav" warn="请选择一个用户" href="<c:url value='rest/user/edit/{slt_objId}'/>" title="编辑用户"><span>编辑</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="user:delete">
				<li><a class="delete" target="ajaxTodo" href="rest/user/delete/{slt_objId}" title="你确定要删除吗?"><span>删除</span></a></li>
			</shiro:hasPermission>
			
			<li class="line">line</li>
			<!-- <li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li> -->
		</ul>
	</div>
	<table class="table" layoutH="138">
		<thead>
			<tr>
				<th width="20"></th>
				<th width="80">用户名</th>
				<th width="120">卡号</th>
				<th width="220">所属角色</th>
				<th width="80">性别</th>
				<th width="100">生日</th>
				<th width="120">手机号</th>
				<th width="180">邮箱</th>
				<th width="350">地址</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${page.result}" varStatus="s">
			<tr target="slt_objId" rel="${item.userId}">
				<td>${s.index + 1}</td>
				<td>${item.user.username}</td>
				<td>${item.workNo}</td>
				<td>${item.roles}</td>
				<td>${item.sex}</td>
				<td><fmt:formatDate value="${item.birthday}" pattern="yyyy-MM-dd"/></td>
				<td>${item.phone}</td>
				<td>${item.email}</td>
				<td>${item.address}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:import url="../../include/panelBar.jsp"></c:import>
</div>

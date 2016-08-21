 <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../include/include.inc.jsp"%>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="rest/stock/list">
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
			<shiro:hasPermission name="stock:correct">
				<li><a class="add" target="dialog"  mask="true" width="815" height="270"  rel="addCorrectNav"  warn="请选择一个物料" href="<c:url value='rest/stock/toAddCorrect/{slt_objId}'/>" title="新增产量修正单"><span>产量修正</span></a></li>
			</shiro:hasPermission>
               <%--  <shiro:hasPermission name="stock:rework">
				<li><a class="add" target="dialog"  mask="true" width="815" height="350"  rel="addReworkNav" warn="请选择一个物料"href="<c:url value='rest/stock/toAddRework/{slt_objId}'/>" title="新增返修单"><span>返修处理</span></a></li>
	            </shiro:hasPermission> --%>
			<li class="line">line</li>
			<!-- <li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li> -->
		</ul>	
	</div>
	<table  id="tableId" layoutH="138" class="table">
		<thead>
			<tr>
				<th width="25" onclick="sortAble('tableId',0)">序号</th>
				<th width="120" onclick="sortAble('tableId',1)" >物料编号</th>
				<th width="120" onclick="sortAble('tableId',2)" >物料描述</th>
				<th width="80" onclick="sortAble('tableId',3,'int')" >生产总量</th>
				<th width="80" onclick="sortAble('tableId',4,'int')" >待分配量量</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${page.result}" varStatus="s">
			<tr target="slt_objId" rel="${item.id}">
				<td>${s.index + 1}</td>
				<td>${item.materialId}</td>
				<td>${item.materialDescribe}</td>
				<td>${item.totalProduction }</td>
				<td>${item.stockNum }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:import url="../../include/panelBar.jsp"></c:import>
</div>
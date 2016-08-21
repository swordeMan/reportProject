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
 function chengState(state){
	 if(state==""||state==null){ 
		 document.getElementById("auditing").style.display = "block";
	 }
	 else{
		 document.getElementById("auditing").style.display = "none";
	 }
 };
</script>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="rest/rework/list">
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
			<shiro:hasPermission name="rework:detail">
				<li><a class="edit" target="dialog" mask="true"  width="815" height="470" rel="ReworkDetailNav" warn="请选择一个返修单" href="<c:url value='rest/rework/query/{slt_objId}'/>" title="查看详情"><span>查看详情</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="rework:valide">
				<li><a class="edit"  id="auditing"; target="dialog" mask="true" width="815" height="470" rel="ReworkAuditingNav" warn="请选择一个返修单" href="<c:url value='rest/rework/queryForAuditing/{slt_objId}'/>" title="返修审核"><span>返修审核</span></a></li>
			</shiro:hasPermission>
			<%-- <li><a class="add" target="ajaxTodo" warn="请选择一个返修单" href="<c:url value='rest/rework/printRework/{slt_objId}'/>"><span>打印返修单</span></a> </li> --%>
			
			<li class="line"  >line</li>
			<!-- <li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li> -->
		</ul>
	</div>
	<table class="table" layoutH="138">
		<thead>
			<tr >
				<th width="25"></th>
				<th orderField="rework_id" width="120">返修单编号</th>
				<th orderField="material_id" width="130">物料编码</th>
				<th orderField="material_describe" width="230">物料描述</th>
				<th orderField="production_procedure" width="80">工序</th>
				<th orderField="rework_time" width="110">返修时间</th>
				<th orderField="classes" width="80">班次</th>
				<th orderField="rework_num" width="80">返修数量</th>
				<th orderField="create_personnel" width="80">制表人员</th>
			    <th orderField="create_time" width="150">制单时间</th>
				<th orderField="auditor" width="80">审核人</th>
				<th orderField="auditing_time" width="150">审核时间</th>
				<th orderField="auditing_illustration" width="150">审核说明</th>
				<th orderField="state" width="80">审核状态</th>
				<th orderField="income" width="80">是否进线</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${page.result}" varStatus="s">
			<tr target="slt_objId" rel="${item.reworkId}" onclick="chengState('${item.state}')";>
				<td>${s.index + 1}</td>
				<td>${item.reworkId}</td>
				<td>${item.materialId}</td>
				<td>${item.materialDescribe}</td>
				<td>${item.productionProcedure}</td>
				<td><fmt:formatDate value="${item.reworkTime}" pattern="yyyy-MM-dd"/></td>
				<td>${item.classes}</td>
				<td>${item.reworkNum}</td>
				<td>${item.createPersonnel}</td>
				<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${item.auditor}</td>
				<td><fmt:formatDate value="${item.auditingTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${item.auditingIllustration}</td>
				<td>${item.state}</td>
				<td>${item.income}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:import url="../../include/panelBar.jsp"></c:import>
</div>

</script>
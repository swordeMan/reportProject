<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../include/include.inc.jsp"%>
<form id="pagerForm" method="post" action="rest/scrap/list">
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
function verify(state){
	if(state!=null && state!=""){
		//alertMsg.warn("该报废单已审核，禁止操作！");
		$('.edit').hide();
	}else{
		$('.edit').show();
	}
}


</script>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="rest/scrap/list" method="post">
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
			<shiro:hasPermission name="scrap:valide">
				<li><a class="edit" href="rest/scrap/verify/{slt_objId}" target="dialog" mask="true" width="800" height="470" warn="请选择报废单"><span>报废单审核</span></a></li>
			</shiro:hasPermission>
				<li><a class="add" href="rest/scrap/print/{slt_objId}" target="navtab" mask="true" width="820" height="470" warn="请选择报废单"><span>报废单详情</span></a></li>
			<li><a class="add" target="ajaxTodo" warn="请选择一个报废单" href="<c:url value='rest/scrap/printScrap/{slt_objId}'/>"><span>打印报废单</span></a> </li>	
			<li class="line">line</li>
			<!-- <li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="确实要导出这些记录吗?"><span>导出EXCEL</span></a></li> -->
		</ul>
	</div>
	<table class="table" id="scrapTab" layoutH="138">
		<thead>
			<tr>
				<!-- <th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th> -->
				<th width="25"></th>
				<th orderField="scrap_id" width="120">报废单编号</th>
				<th orderField="material_id" width="120">物料编码</th>
				<th orderField="material_describe" width="120">物料描述</th>
				<th orderField="product_order_id" width="120">生产订单号</th>
				<th orderField="scrap_time" width="100">报废日期</th>
				<th orderField="classes" width="80">班次</th>
				<th orderField="scrap_num" width="80">报废数量</th>
				<th orderField="classification" width="80">报废类型</th>
				<th orderField="production_process" width="120">工序</th>
				<th orderField="inspector" width="80">填单人</th>
				<th orderField="create_time" width="120">制单时间</th>
				<th orderField="message_type" width="60">SAP消息类型</th>
				<th orderField="message" width="120">SAP消息文本</th>
				<th orderField="auditor" width="60">审核人</th>
				<th orderField="auditing_time" width="100">审核时间</th>
				<th orderField="auditing_contents" width="120">审核内容</th>
				<!-- <th orderField="scrap_auditing" width="120">报废核准量</th> -->
				<!-- <th orderField="product_order_id" width="120">原始凭证</th> -->
				<th orderField="state" width="90">审核状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${page.result}" varStatus="s">
			<tr target="slt_objId" rel="${item.scrapId}" onclick="verify('${item.state}')">
				<%-- <td><input name="ids" value="${item.scrapId}" type="checkbox"></td> --%>
				<td>${s.index + 1}</td>
				<td>${item.scrapId}</td>
				<td>${item.materialId }</td>
				<td>${item.materialDescribe }</td>
				<td>${item.productOrderId }</td>
				<td><fmt:formatDate value="${item.scrapTime}" pattern="yyyy-MM-dd"/></td>
				<td>${item.classes }</td>
				<td>${item.scrapNum }</td>
				<td>${item.classification }</td>
				<td>${item.productionProcess }</td>
				<td>${item.inspector }</td>
				<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/></td>
				<td>
					<c:if test="${item.messageType=='S'}">
						成功
					</c:if>
					<c:if test="${item.messageType=='E'}">
						失败
					</c:if>
				</td>
				<td>${item.message }</td>
				<td>${item.auditor }</td>
				<td><fmt:formatDate value="${item.auditingTime}" pattern="yyyy-MM-dd"/></td>
				<td>${item.auditingContents }</td>
				<%-- <td>${item.scrapAuditing }</td> --%>
				<%-- <td>${item.file }</td> --%>
				<td>${item.state}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:import url="../../include/panelBar.jsp"></c:import>
</div>

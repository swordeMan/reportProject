<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/include.inc.jsp"%>

<div class="pageContent">
	<div class="pageFormContent" layoutH="56">
		<table class="list nowrap" width="1000">
			<tr>
				<th colspan="10">订单进度信息</th>
			</tr>
			<tr>
				<th>客户简称:</th>
				<th colspan="9">${sapOrder.userSimpleName}</th>
				
			</tr>
			<tr>
				<th>销售订单:</th>
				<th>${sapOrder.saleOrderId}</th>
				<th>生产订单号:</th>
				<th>${sapOrder.productOrderId}</th>
			</tr>
			<tr>
				<th>物料:</th>
				<th>${sapOrder.materialId}</th>
				<th>物料描述:</th>
				<th>${sapOrder.materialDescribe}</th>
			</tr>
			<tr>
				<th>目标量:</th>
				<th>${sapOrder.targetSum}</th>
				<th>完成率:</th>
				<th>
					<div class="progress">
						<div class="progress-bar" role="progressbar" aria-valuenow="60"
							aria-valuemin="0" aria-valuemax="100" style="width: ${sapOrder.rate}%;">
							<span class="sr-only">${sapOrder.rate}% 完成</span>
						</div>
					</div>
				</th>
			</tr>
			<tr>
				<th>完成量:</th>
				<th>${sapOrder.finishedTotal}</th>
				<th>报废量:</th>
				<th>${sapOrder.wasteTotal}</th>
			</tr>
			<tr>
				<th colspan="10"></th>
			</tr>
		</table>
		<div class="divider"></div>
		<table class="list nowrap" width="1000">
			<tr>
				<th colspan="4">订单报工信息</th>
			</tr>
			<tr>
				<th>报工编号:</th>
				<th>报工数量:</th>
				<th>报工时间:</th>
				<th>报工员:</th>
			</tr>
			<c:forEach var="item" items="${reportList}" varStatus="s">
					<tr>
						<td>${item.messageId}</td>
						<td>${item.currentYield}</td>
						<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${item.reportUsername}</td>
					</tr>
			</c:forEach>
		</table>
		<div class="divider"></div>
		<table class="list nowrap" width="1000">
			<tr>
				<th colspan="4">订单报废报工信息</th>
			</tr>
			<tr>
				<th>报费单编号:</th>
				<th>报废量:</th>
				<th>报废日期:</th>
				<th>报废员:</th>
			</tr>
			<c:forEach var="item" items="${scrapList}" varStatus="s">
					<tr>
						<td>${item.scrapId}</td>
						<td>${item.scrapNum}</td>
						<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${item.inspector}</td>
					</tr>
			</c:forEach>
		</table>
		<div class="divider"></div>
		<table class="list nowrap" width="1000">
			<tr>
				<th colspan="4">报工冲销信息</th>
			</tr>
			<tr>
				<th>冲销单号:</th>
				<th>冲销报工数:</th>
				<th>冲销报废数:</th>
				<th>冲销日期:</th>
			</tr>
			<c:forEach var="item" items="${cancelList}" varStatus="s">
					<tr>
						<td>${item.messageId}</td>
						<td>${item.currentYield}</td>
						<td>${item.currentWaste}</td>
						<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
			</c:forEach>
		</table>
		
		
		<%-- <table class="list nowrap">
			<thead>
				<tr>
					<th colspan="10">订单进度填报</th>
				</tr>
				<tr>
					<th>销售订单行</th>
					<th>订单号</th>
					<th type="text" name="reportYield[#index#].materialId">物料</th>
					<th type="text" name="reportYield[#index#].materialDescribe">物料描述</th>
					<!-- <th type="text" name="reportYield[#index#].targetSum">完成量</th> -->
					<th type="text">目标数量</th>
					<th type="text">完成数</th>
					<th type="text">废品数</th>
					<th type="text" name="reportYield[#index#].currentYield">本次报工量</th>
					<th type="text" name="reportYield[#index#].currentWaste">本次报废量</th>
					<th type="text" name="reportYield[#index#].accountDate">记账日期</th>
					<th type="del" width="25">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${sapOrderList}" varStatus="s">
					<tr class="unitBox">
						<td><input size="5" type="text" value="${item.saleOrderRow}" readonly="readonly"></td>
					</tr>
				</c:forEach>
			</tbody>
		</table> --%>
		
	</div>
	<div class="formBar">
		<ul>
			<li>
				<div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div>
			</li>
		</ul>
	</div>
</div>


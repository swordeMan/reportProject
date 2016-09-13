<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function validateCurrentYield(id){
	var currentWaste = parseInt($("#currentWaste"+id).val());
	var currentYield = parseInt($("#currentYield"+id).val());
	var targetSum = parseInt($("#targetSum"+id).val());
	var finishedTotal = parseInt($("#finishedTotal"+id).val());
	var wasteTotal = parseInt($("#wasteTotal"+id).val());
	if(currentYield>(targetSum-finishedTotal+wasteTotal)){
		alertMsg.warn('本条报工数量不正确！');
		return false;
	}
	
}
function validateCurrentWaste(id){
	var currentWaste = parseInt($("#currentWaste"+id).val());
	var currentYield = parseInt($("#currentYield"+id).val());
	var targetSum = parseInt($("#targetSum"+id).val());
	var finishedTotal = parseInt($("#finishedTotal"+id).val());
	var wasteTotal = parseInt($("#wasteTotal"+id).val());
	if(currentWaste>(finishedTotal-wasteTotal)){
		alertMsg.warn('本条报废数量不正确！');
		return false;
	}
}
</script>

<div class="pageContent">
	<form id="reportByHandForm" method="post" action="<c:url value='rest/reportYield/doReportByHand?callbackType=closeCurrent'/>" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${sapOrder.id}"/>
		<div class="pageFormContent" layoutH="56">
			<table class="list nowrap" width="1000">
				<tr>
					<th colspan="10">销售订单信息</th>
				</tr>
				<tr>
					<th>客户简称:</th>
					<th colspan="9"><input type="text" name="userSimpleName" value="${sapOrderList[0].userSimpleName}"  readonly="readonly"></th>
					
				</tr>
				<tr>
					<th>销售订单</th>
					<th colspan="6"><input type="text" name="saleOrderId" value="${sapOrderList[0].saleOrderId}" readonly="readonly"></th>
					<th>报工人:</th>
					<th colspan="2"><input type="text" value="${userInfo.username}" readonly="readonly"></th>
				</tr>
				<tr>
					<th colspan="10"></th>
				</tr>
			</table>
			<div class="divider"></div>
			<table class="list nowrap itemDetail">
				<thead>
					<tr>
						<th colspan="10">订单进度填报</th>
					</tr>
					<tr>
						<th type="text" name="reportYield[#index#].saleOrderRow">销售订单行</th>
						<th type="text" name="reportYield[#index#].productOrderId">订单号</th>
						<!-- <th type="text" name="sapOrder[#index#].materialDescribe">物料</th> -->
						<th type="text" name="reportYield[#index#].materialId">物料</th>
						<th type="text" name="reportYield[#index#].materialDescribe">物料描述</th>
						<!-- <th type="text" name="reportYield[#index#].targetSum">完成量</th> -->
						<th type="text">目标数量</th>
						<th type="text">完成数</th>
						<th type="text">报废数</th>
						<th type="text" name="reportYield[#index#].currentYield">本次报工量</th>
						<th type="text" name="reportYield[#index#].currentWaste">本次报废量</th>
						<th type="text" name="reportYield[#index#].accountDate">记账日期</th>
						<th type="del" width="25">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${sapOrderList}" varStatus="s">
						<tr class="unitBox">
							<td><input size="5" type="text" name="reportYield[${s.index}].saleOrderRow" value="${item.saleOrderRow}" readonly="readonly"></td>
							<td><input size="12" type="text" name="reportYield[${s.index}].productOrderId" value="${item.productOrderId}" readonly="readonly"></td>
							<td><input size="12" type="text" name="reportYield[${s.index}].materialId" value="${item.materialId}" readonly="readonly"></td>
							<td><input size="28" type="text" name="reportYield[${s.index}].materialDescribe" value="${item.materialDescribe}" readonly="readonly"></td>
							<td><input size="5" type="text" name="reportYield[${s.index}].targetSum" value="${item.targetSum}" class="digits textInput" readonly="readonly" id="targetSum${s.index}"></td>
							<td><input size="5" type="text" value="${item.finishedTotal}" class="digits textInput" readonly="readonly" id="finishedTotal${s.index}"></td>
							<td><input size="5" type="text" value="${item.wasteTotal}" class="digits textInput" readonly="readonly" id="wasteTotal${s.index}"></td>
							<td><input size="6" type="text" name="reportYield[${s.index}].currentYield" class="digits required textInput" id="currentYield${s.index}" onblur="validateCurrentYield(${s.index})" ></td>
							<td><input size="6" type="text" name="reportYield[${s.index}].currentWaste" value="0" class="digits required textInput" id="currentWaste${s.index}" onblur="validateCurrentWaste(${s.index})"></td>
							<td><input size="8" type="text" name="reportYield[${s.index}].accountDate" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>" class="date required" readonly="true"/>
								<a class="inputDateButton" href="javascript:;">选择</a></td>
							<td><a href="javascript:void(0)" class="btnDel ">删除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">报工</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>


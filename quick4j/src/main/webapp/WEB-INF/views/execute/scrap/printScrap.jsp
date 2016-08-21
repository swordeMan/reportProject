<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../include/include.inc.jsp"%>

<div class="pageContent">
	<div class="pageFormContent" layoutH="56">
		<div id="w_list_print">
			<table class="list nowrap" width="790">
				<tr>
					<th colspan="6" style="text-align: center;line-height:48px;font-size: 20px;">报废单</th>
				</tr>
				<tr style="line-height:68px;">
					<th>报废单编号:</th>
					<th>${scrapViewList[0].scrapId}</th>
					<th></th>
					<th></th>
					<th>客户:</th>
					<th>${scrapViewList[0].userSimpleName}</th>
				</tr>
				<tr style="line-height:68px;">
					<th>产品名称:</th>
					<th>${scrapViewList[0].materialDescribe}</th>
					<th>规格:</th>
					<th>${scrapViewList[0].materialId}</th>
					<th>生产订单:</th>
					<th>${scrapViewList[0].productOrderId}</th>
				</tr>
				<tr style="line-height:68px;">
					<th>生产车间/工序:</th>
					<th>${scrapViewList[0].productionProcess}</th>
					<th>报废日期:</th>
					<th><fmt:formatDate value="${scrapViewList[0].scrapTime}" pattern="yyyy-MM-dd"/></th>
					<th>报废数量:</th>
					<th>${scrapViewList[0].wasteTotal}</th>
				</tr>
				<tr style="line-height:68px;">
					<th colspan="2" style="text-align: center">报废原因</th>
					<th colspan="2" style="text-align: center">报废数量</th>
					<th colspan="2" style="text-align: center">备注</th>
				</tr>
				<c:forEach var="item" items="${scrapViewList}" varStatus="s">
					<tr class="unitBox" style="line-height:48px;text-align: center;">
						<td colspan="2">${item.sReason}</td>
					    <td colspan="2">${item.scrapNum}</td>
						<td colspan="2">${item.illustration}</td>
					</tr>
				</c:forEach>
				<tr style="line-height:68px;">
					<th>检验员:</th>
					<th>${scrapViewList[0].auditor}</th>
					<th>审批:</th>
					<th></th>
					<th>日期:</th>
					<th></th>
				</tr>
			</table>
		</div>
	</div>
	<div class="formBar">
		<ul>
			<li>
				<div class="button"><a class="icon" href="javascript:$.printBox('w_list_print')"><span>打印</span></a></div>
			</li>
			<li>
				<div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div>
			</li>
		</ul>
	</div>
</div>


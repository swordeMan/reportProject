<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="pageContent">
        <form  method="post" action="<c:url value='rest/scrap/update?callbackType=closeCurrent'/>" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<input type="hidden" name="saleOrderId" value="${scrapViewList[0].saleOrderId}">
		<input type="hidden" name="saleOrderRow" value="${scrapViewList[0].saleOrderRow}">
			<p>
				<label>报废单号：</label>
				<input name="scrapId" readonly="true"  type="text" value="${scrapViewList[0].scrapId}" size="30"/>
			</p>
			<p>
				<label>生产订单：</label>
				<input name="productOrderId" readonly="true"  type="text" value="${scrapViewList[0].productOrderId}" size="30"/>
			</p>
			<p>
				<label>物料编码：</label>
				<input name="materialId" readonly="true"  type="text" value="${scrapViewList[0].materialId}" size="30"/>
			</p>
			<p>
				<label>物料描述：</label>
				<input name="materialDescribe" readonly="true"  type="text" value="${scrapViewList[0].materialDescribe}" size="30"/>
			</p>
			<p>
				<label>工序：</label>
				<input name="productionProcess"  readonly="true"  type="text" value="${scrapViewList[0].productionProcess}" size="30"/>
			</p>
			 <p>
				<label>报废日期：</label>				
				<input name="scrapTime" readonly="true"  type="text" value="<fmt:formatDate value="${scrapViewList[0].scrapTime}" pattern="yyyy-MM-dd"/>" size="30"/>
			</p>
			<p>
				<label>班次：</label>
				<input name="classes" readonly="true" type="text" value="${scrapViewList[0].classes}" size="30" />
			</p>
			<p>
				<label>报废数量：</label>
				<input name="scrapNum" readonly="true" type="text" value="${scrapViewList[0].wasteTotal}" size="30" />
			</p>
			<p>
				<label>填单人：</label>
				<input name="inspector" readonly="true" type="text" value="${scrapViewList[0].inspector}" size="30" />
			</p>
		    <p>
				<label>制单时间：</label>				
				<input name="createTime" readonly="true"  type="text" value="<fmt:formatDate value="${scrapViewList[0].createTime}" pattern="yyyy-MM-dd"/>" size="30"/>
			</p>
			<p>
				<label>审核状态：</label>
				<input name="state"  type="radio" checked value="审核通过" />审核通过
				<input name="state" type="radio"  value="未通过审核" />未通过审核
			</p>
			<p>
				<label>审核说明：</label>
				<input name="auditingContents" type="text"  size="30" />
			</p>
			<p>
				<label>审核人：</label>
				<input name="auditor" readonly="true" type="text" value="${userInfo.username}" size="30" />
			</p>
			<p>
				<label>审核时间：</label>				
				<input name="auditingTime" readonly="true"  type="text" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>" size="30"/>
			</p>
			<!-- <p>
			    <label>上传凭证</label>
			    <input name="file" type="file" class="valid">
			</p> -->
			
			<div class="divider"></div>
			<table class="list nowrap itemDetail" width="100%">
				<thead>
					<tr>
						<th>报废原因</th>
						<th>报废数量</th>
						<th>备注说明</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="item" items="${scrapViewList}" varStatus="s">
					<tr class="unitBox" target="slt_objId" rel="${item.id}">
						<td>${item.sReason}</td>
					    <td>${item.scrapNum}</td>
						<td>${item.illustration}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<c:import url="../../include/formBar.jsp"></c:import>
		</form>
</div>


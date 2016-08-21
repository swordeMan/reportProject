<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="pageContent">
        <form  id="createAuditing" method="post" action="<c:url value='rest/rework/insertAuditing?callbackType=closeCurrent'/>" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input name="id" type="hidden" value="${rework.id}" size="30"/>
		<div class="pageFormContent" layoutH="56" >
			<p>
				<label>返修单号：</label>
				<input name="reworkId" readonly="true"  type="text" value="${rework.reworkId}" size="30"/>
			</p>
			<p>
				<label>物料编码：</label>
				<input name="materialId" readonly="true"  type="text" value="${rework.materialId}" size="30"/>
			</p>
			<p>
				<label>物料描述：</label>
				<input name="materialDescribe" readonly="true"  type="text" value="${rework.materialDescribe}" size="30"/>
			</p>
			<p>
				<label>工序：</label>
				<input name="productionProcedure"  readonly="true"  type="text" value="${rework.productionProcedure}" size="30"/>
			</p>
<%-- 			<p>
				<label>工厂：</label>
				<input name="factory" readonly="true" type="text" value="${rework.factory}" size="30" />
			</p> --%>
			 <p>
				<label>返修时间：</label>				
				<input name="reworkTime" readonly="true"  type="text" value="<fmt:formatDate value="${rework.reworkTime}" pattern="yyyy-MM-dd"/>" size="30"/>
			</p>
			<p>
				<label>班次：</label>
				<input name="classes" readonly="true" type="text" value="${rework.classes}" size="30" />
			</p>
			<p>
				<label>返修数量：</label>
				<input name="reworkNum" readonly="true" type="text" value="${rework.reworkNum}" size="30" />
			</p>
			<p>
				<label>制表人员：</label>
				<input name="createPersonnel" readonly="true" type="text" value="${rework.createPersonnel}" size="30" />
			</p>
		    <p>
				<label>制单时间：</label>				
				<input name="createTime" readonly="true"  type="text" value="<fmt:formatDate value="${rework.createTime}" pattern="yyyy-MM-dd HH:mm"/>" size="30"/>
			</p>
			<p>
				<label>是否进线：</label>
				<input name="income" readonly="true" type="text" value="${rework.income}" size="30" />
			</p>
			<p>
				<label>审核状态：</label>
				<input name="state"  type="radio" checked value="审核通过" />审核通过
				<input name="state" type="radio"  value="未通过审核" />未通过审核
			</p>
			<p>
				<label>审核说明：</label>
				<input name="auditingIllustration" type="text" maxlength="24" size="30" />
			</p>
			<!-- <p>
			    <label>上传凭证</label>
			    <input name="file" type="file" class="valid">
			</p> -->
			
			<div class="divider"></div>
			<table class="list nowrap itemDetail" width="100%">
				<thead>
					<tr>
						<th >返修原因</th>
						<th >返修数量</th>
						<th >备注说明</th>
					</tr>
				</thead>
				<tbody>
				<%-- <c:forEach var="item" items="${reworkList}" varStatus="s" ${reworkReasonRelationList}></c:forEach> --%>
					<%--	<td>${item.reworkNum}</td>
						<td>${item.illustration}</td> --%>
					<c:forEach var="item" items="${reworkReasonRelationList}" varStatus="loop">
					<tr class="unitBox" target="slt_objId" rel="${item.id}">
						<td>${reworkReasonList[loop.count-1]}</td>
					    <td>${item.reworkNum}</td>
						<td>${item.illustration}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<c:import url="../../include/formBar.jsp"></c:import>
		</form>
</div>


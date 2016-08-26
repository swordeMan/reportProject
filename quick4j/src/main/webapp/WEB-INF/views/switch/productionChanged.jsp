<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../include/include.inc.jsp"%>

<div class="pageContent">
        <form method="post" action="rest/material/change?callbackType=closeCurrent" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input type="hidden" name="deviceId" value="${dd }"/>
			<div class="pageFormContent" layoutH="56" >
				<p>
					<label>物料描述:</label>
					<select class="describe required textInput" name="materialId" style="width:100%">
						<c:forEach var="item" items="${materialMaintainList}" varStatus="s">
							<option value="${item.materialId}" selected="selected">${item.productNickname}+${item.materialDescribe}+${item.materialId}</option>
						</c:forEach>
					</select>
				</p>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
</div>

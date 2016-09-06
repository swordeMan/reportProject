<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="pageContent">
	<form method="post" action="<c:url value='rest/material/insert?callbackType=closeCurrent'/>" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>产品代码:</label>
				<input name="productNickname" class="required" type="text" size="30"/>
			</p>
				<p>
				<label>物料描述:</label>
				<input name="materialDescribe" class="required" type="text" size="30"/>
			</p>
				<p>
				<label>物料编码:</label>
				<input name="materialId" class="required" type="text" size="30"/>
			</p>
			<p>
				<label>工序:</label>
				<select  name="deviceId"  class="required" > 
				<c:forEach var="item" items="${deviceList}" >
				<option value="${item.deviceId}">${item.deiviceDescribe}</option>
				</c:forEach>
				</select>
			</p>
			<p>
				<label>是否启用：</label>
				<input name="state"  type="radio" checked value="1" />是
				<input name="state" type="radio"  value="0" />否
			</p> 
		</div>
		<c:import url="../../include/formBar.jsp"></c:import>
	</form>
</div>

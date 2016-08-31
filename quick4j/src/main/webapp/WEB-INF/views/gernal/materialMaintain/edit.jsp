<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="pageContent">
	<form method="post" action="rest/material/update?callbackType=closeCurrent" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
	
		<div class="pageFormContent" layoutH="56">
		    <p>
				<input name="id" type="hidden" value="${materialMaintain.id}" size="30"/>
			</p>
			<p>
				<label>产品代码:</label>
				<input name="productNickname" class="required" type="text" value="${materialMaintain.productNickname}" size="30"/>
			</p>
				<p>
				<label>物料描述:</label>
				<input name="materialDescribe" class="required" type="text"  value="${materialMaintain.materialDescribe}" size="30"/>
			</p>
				<p>
				<label>物料编码:</label>
				<input name="materialId" class="required" type="text"  value="${materialMaintain.materialId}" size="30"/>
			</p>
				<p>
				<label>工序:</label>
				<input name="deviceDescribe" class="required" type="text"  value="${materialMaintain.deviceDescribe}" size="30"/>
			</p>
		</div>
		<c:import url="../../include/formBar.jsp"></c:import>
	</form>
</div>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="pageContent">
	<form method="post" action="rest/scrapReason/update?callbackType=closeCurrent" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
	<input type="hidden" name="id" value="${scrapReason.id}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>工序</label>
				<input name="materialName" class="required" type="text"  readonly="true" value="${scrapReason.materialName }" size="30"/>
			</p>
			<p>
				<label>报废原因</label>
				<input name="sReason" class="required" type="text" value="${scrapReason.sReason }" size="30"/>
			</p>
			
		</div>
		<c:import url="../../include/formBar.jsp"></c:import>
	</form>
</div>


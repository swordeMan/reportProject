<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="pageContent">
	<form method="post" action="rest/reworkReason/update?callbackType=closeCurrent" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
	<input type="hidden" name="id" value="${reworkReason.id}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>工序</label>
				<input name="category" class="required" type="text" readonly="true" value="${reworkReason.category }" size="30"/>
			</p>
			<p>
				<label>返修原因</label>
				<input name="reason" class="required" type="text" value="${reworkReason.reason }" size="30"/>
			</p>
			
		</div>
		<c:import url="../../include/formBar.jsp"></c:import>
	</form>
</div>


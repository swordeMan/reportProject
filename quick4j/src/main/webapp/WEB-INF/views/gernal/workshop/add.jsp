<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="pageContent">
	<form method="post" action="<c:url value='rest/workshop/insert?callbackType=closeCurrent'/>" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>车间:</label>
				<input name="workshop" class="required" type="text" size="30"/>
			</p>
				<p>
				<label>打印机:</label>
				<input name="print" type="text" size="30"/>
			</p>
		</div>
		<c:import url="../../include/formBar.jsp"></c:import>
	</form>
</div>


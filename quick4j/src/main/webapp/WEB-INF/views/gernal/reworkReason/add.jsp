<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="pageContent">
	<form method="post" action="<c:url value='rest/reworkReason/insert?callbackType=closeCurrent'/>" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>工序:</label>
				<select name="category">                 
                   <option value="轮辐">轮辐</option>
                   <option value="轮辋">轮辋</option>
                   <option value="合成">合成</option>
                   <option value="涂装">涂装</option>
                </select>
			</p>
			<p>
				<label>返修原因:</label>
				<input name="reason" class="required" type="text" size="30"/>
			</p>
		</div>
		<c:import url="../../include/formBar.jsp"></c:import>
	</form>
</div>


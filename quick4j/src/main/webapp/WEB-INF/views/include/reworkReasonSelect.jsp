<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<select class="required" name="reworkReasonRelation[0].reworkreasonId" style="width: 60%">
	<c:forEach var="item" items="${reworkReasonList}" varStatus="s">
		<option value="${item.id}" selected="selected">${item.reason}</option>
	</c:forEach>
</select>
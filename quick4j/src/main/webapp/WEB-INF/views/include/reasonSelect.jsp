<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<select class="required" name="scrapReasonRelation[0].sReasonId" style="width: 60%" onblur="validateIllustration(this)">
	<c:forEach var="item" items="${scrapReasonList}" varStatus="s">
		<option value="${item.id}" selected="selected">${item.sReason}</option>
	</c:forEach>
</select>

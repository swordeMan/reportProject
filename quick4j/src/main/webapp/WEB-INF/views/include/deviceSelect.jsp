<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<select name="assignments[0].deviceId" style="width: 60%">
	<c:forEach var="item" items="${deviceInfoList}" varStatus="s">
		<option value="${item.deviceId}" selected="selected">${item.deviceId}</option>
	</c:forEach>
</select>
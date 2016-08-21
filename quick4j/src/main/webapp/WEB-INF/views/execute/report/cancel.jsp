<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div class="pageContent">
	<form method="post" action="<c:url value='rest/reportYield/insert?navTabId=scrapNav&callbackType=closeCurrent'/>" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
		<div class="pageFormContent" layoutH="56">
			
			<p>
				<label>冲销单号：</label>
				<input name="messageId" readonly="readonly" type="text" value="${reportYield.messageId}" size="30"/>
			</p>
			<p>
				<label>生产订单：</label>
				<input name="productOrderId" readonly="readonly" type="text" value="${reportYield.productOrderId}" size="30"/>
			</p>
			<p>
				<label>冲销完成：</label>
				<input name="currentYield" readonly="readonly" type="text" value="${reportYield.currentYield}" size="30"/>
			</p>
			<p>
				<label>冲销报废：</label>
				<input name="currentWaste" readonly="readonly" type="text" value="${reportYield.currentWaste}" size="30"/>
			</p>
			<p>
				<label>填单人：</label>
				<input name="reportUsername" style="width:16%" type="text" value="${userInfo.username}" readonly="readonly" size="30"/>
			</p>
			<!-- <p>
				<label>填单日期：</label>
				<input type="text" name="createTime" class="date" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p> -->
			<div class="divider"></div>
		</div>
	</form>
</div>


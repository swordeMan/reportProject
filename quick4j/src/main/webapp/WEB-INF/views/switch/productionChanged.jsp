<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../include/include.inc.jsp"%>
<script>
	/*  $("#materialContent div").click(function(){
		$this = $(this);
	});  */
</script>
<style>
#materialContent p {
	float: left;
	display: block;
	width: 180px;
	height: 21px;
	margin: 0;
	padding: 5px 0;
	position: relative;
}

#materialContent div {
	float: left;
	border: 1px solid black;
	width: 190px;
	padding:5px 1px;
	margin-left: 3px;
	color: white;
	text-align: center;
}

form .material {
	border: none;
	text-align: center;
	background-color: transparent;
	color: white;
	font-size: 14px;
	background: none;
}
</style>
<div class="pageContent">
       
			<div class="pageFormContent" layoutH="54" id="materialContent">
				<c:forEach var="item" items="${productingViewList}" varStatus="s">
				<c:choose>
					<c:when test="${item.changeTime==null}">
						<div onclick="document.getElementById('conserve${s.index}').click();" style="background:#183152;">
							<form method="post" action="rest/material/change?navTabId=materialNav" class="pageForm" onsubmit="return validateCallback(this, navTabAjaxDone,'请刷卡提交，切换至${item.productNickname}-${item.materialDescribe}(${item.materialId})',workNo);">
								<input type="hidden" name="deviceId" value="${dd }" style="display: none"/>
								<input id="workNo" name="workNum" style="width:0;height:0;border:none;padding:0" type="text"/>	
								<p>${item.productNickname}</p><br>
								<p>${item.materialDescribe}</p><br>
								<input class="material" type="text" name="materialId" value="${item.materialId}" readonly="readonly"/><br>
								<p>${item.username}&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${item.changeTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
								<button id="conserve${s.index}" type="submit" style="display: none"></button>
							</form>
						</div>
					</c:when>
					<c:otherwise>
						<div style="background:#8ABB12;">
							<form class="pageForm required-validate">
								<input type="hidden" name="deviceId" value="${dd }" style="display: none"/>
								<input id="workNo" name="workNum" style="width:0;height:0;border:none;padding:0" type="text"/>	
								<p>${item.productNickname}</p><br>
								<p>${item.materialDescribe}</p><br>
								<input class="material" type="text" name="materialId" value="${item.materialId}" readonly="readonly"/><br>
								<p>${item.username}&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${item.changeTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
							</form>
						</div>
					</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
			<div class="formBar">
				<ul>
					<li>
						<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
</div>

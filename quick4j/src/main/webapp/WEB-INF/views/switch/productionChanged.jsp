<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../include/include.inc.jsp"%>
<script>
	/* $(".materialInfo").click(function(){
		$this = $(this);
	}); */
</script>
<div class="pageContent">
       
			<div class="pageFormContent" layoutH="54" >
				<c:forEach var="item" items="${materialMaintainList}" varStatus="s">
					<div onclick="document.getElementById('conserve').click();" style="float:left; border:1px solid black; padding:15px 15px; width:260px; margin-left:3px; position:relative; color:white; background:#183152;">
						<form method="post" action="rest/material/change?callbackType=closeCurrent" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone,'请刷卡提交',workNo);">
							<input type="hidden" name="deviceId" value="${dd }" style="display: none"/>
							<input id="workNo" name="workNum" style="width:0;height:0;border:none" type="text"/>	
							<p>${item.productNickname}</p><br>
							<p>${item.materialDescribe}</p><br>
							<input type="text" name="materialId" value="${item.materialId}" readonly="readonly"/><br>
							<button id="conserve" type="submit" style="display: none"></button>
						</form>
					</div>
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

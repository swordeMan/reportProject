<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="pageContent">
	<form method="post" action="<c:url value='rest/correct/insert?callbackType=closeCurrent'/>" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<!-- <p>
				<label>修正单号：</label>
				<input name="correctId" class="required" type="text" size="30"/>
			</p> -->
			<p>
				<label>物料编码：</label>
				<input name="materialId" class="required" type="text" readonly="true" value="${stock.materialId}" size="30"/>
			</p>
			<p>
				<label>物料描述：</label>
				<input name="materialDescribe" class="required" type="text" readonly="true" value="${stock.materialDescribe}" size="30"/>
			</p>
			<p >
				<label>修正数量：</label>
				<input name="correctNum" class="required number"  type="text" maxlength="10" size="15"/>
			    <label style="color:red;width:150px">正数为增加，负数为减少</label>
			</p>
			<p></p>
		<!-- 	<p>
				<label>修正人员：</label>
				<input name="revokePersonnel" class="required" type="text"  size="30" />

			</p>
		    <p>
				<label>制单时间：</label>				
				<input name="createTime" class="required" type="date"  size="30"/>
			</p>-->
		  
			<dl class="nowrap" >  
				<dt style="width:70px">修正原因：</dt>
				<dd  style="width:400px">
				<textarea  name="createReason" cols="90" rows="4" class="required" maxlength="120"></textarea>
			    </dd>
			</dl>
		</div>
		<c:import url="../../include/formBar.jsp"></c:import>
	</form>
</div>
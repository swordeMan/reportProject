<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="pageContent">
	<form id="userInfoForm" method="post" action="<c:url value='rest/user/insert?callbackType=closeCurrent'/>" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			
			<p>
				<label>员工姓名：</label>
				<input name="user.username" class="required" type="text" size="30" alt="姓名"/>
			</p>
			<p>
				<label>登陆密码：</label>
				<input id="passwd" name="user.password" class="required alphanumeric" minlength="6" maxlength="20" type="password" alt="密码"/>
			</p>
			<p>
				<label>密码确认：</label>
				<input type="password" class="required alphanumeric" size="20" alt="确认密码" equalto="#passwd"/>
			</p>
			<p>
				<label>卡号：</label>
				<input name="userInfo.workNo" class="required" type="text" size="15" alt="工号"/>
			</p>
			<%-- <div class="nowrap">
				<label>角色</label>
				<c:forEach var="item" items="${roleList}">
					<label><input type="checkbox" name="role.id" value="${item.id}" />${item.roleName}</label>
				</c:forEach>
			</div> --%>
			<dl class="nowrap">
			<dt>角色：</dt>
				<c:forEach var="item" items="${roleList}" >
					<label><input type="checkbox" name="roleIds" class="required" value="${item.id}" />${item.roleName}</label>
				</c:forEach>
			</dl>
			<p>
				<label>生日：</label>
				<input type="text" name="userInfo.birthday" class="date" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p>
				<label>手机号：</label>
				<input name="phone" class="userInfo.phone" type="text" size="15" alt="手机号"/>
			</p>
			<p>
				<label>邮箱：</label>
				<input name="email" class="userInfo.email" type="text" size="30" alt="邮箱"/>
			</p>
			<dl class="nowrap">
				<dt>地址：</dt>
				<dd><textarea name="userInfo.address" cols="80" rows="2"></textarea></dd>
			</dl>
			
		</div>
		<c:import url="../../include/formBar.jsp"></c:import>
	</form>
</div>


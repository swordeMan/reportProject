<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
$(function(){
	var userId = ${userInfo.userId};
	$.ajax({
	    type: 'GET',
	    url: "rest/user/roleCheckBox",
	    data: {"userId":userId},
		dataType:'json',
		success:function(result){
			if(result!=null){
			 $.each(result,function(n,value) {   
		           $('#checkbox'+value.id).attr("checked", true);
		           });  
			}else{
				alertMsg.info('返回为空！');
			}
		},
		error:function() {  
			alertMsg.error('返回为空！');
			   }
	});
});

</script>

<div class="pageContent">
	<form method="post" action="rest/user/update?callbackType=closeCurrent" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
	<input type="hidden" name="user.id" value="${userInfo.userId}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>员工姓名：</label>
				<input name="user.username" class="required" type="text" size="30" value="${userInfo.user.username}" readonly="readonly"/>
			</p>
			<p>
				<label>登陆密码：</label>
				<input name="user.password" class="required alphanumeric" value="${userInfo.user.password}" minlength="6" maxlength="20" type="text"/>
			</p>
			<p>
				<label>卡号：</label>
				<input name="userInfo.workNo" class="required" type="text" value="${userInfo.workNo}" size="15"/>
			</p>
			<dl class="nowrap">
			<dt>角色：</dt>
				<c:forEach var="item" items="${roleList}">
					<label><input id="checkbox${item.id}" type="checkbox" name="roleIds" class="required" value="${item.id}" />${item.roleName}</label>
				</c:forEach>
			</dl>
			<p>
				<label>生日：</label>
				<input type="text" name="userInfo.birthday" class="date" readonly="true" value="<fmt:formatDate value='${userInfo.birthday}' pattern='yyyy-MM-dd'/>"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p>
				<label>手机号：</label>
				<input name="phone" class="userInfo.phone" type="text" value="${userInfo.phone}" size="15"/>
			</p>
			<p>
				<label>邮箱：</label>
				<input name="email" class="userInfo.email" type="text" value="${userInfo.email}" size="30"/>
			</p>
			<dl class="nowrap">
				<dt>地址：</dt>
				<dd><textarea name="userInfo.address" value="${userInfo.address}" cols="80" rows="2"></textarea></dd>
			</dl>
		</div>
		<c:import url="../../include/formBar.jsp"></c:import>
	</form>
</div>


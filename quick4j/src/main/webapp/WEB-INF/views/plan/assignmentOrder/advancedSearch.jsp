<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="pageContent">
	<form method="post" action="rest/assignment/list?callbackType=closeCurrent" class="pageForm" onsubmit="return navTabSearch(this);">
		<div class="pageFormContent" layoutH="58">
		
			<label style="width:100px">请输入检索条件：</label>
			<div class="divider">divider</div>
			<div class="unit">
				<label>客户：</label>
				<input type="text" size="25" name="userSimpleName"/>
				<span class="inputInfo">关键字或全称</span>
			</div>
			<div class="unit">
				<label>销售订单 ：</label>
				<input type="text" size="25" name="saleOrderId"/>
				<span class="inputInfo">完整的销售订单号</span>
			</div>
			<div class="unit">
				<label>物料编码：</label>
				<input type="text" size="25" name="materialId"/>
				<span class="inputInfo">关键字或全称</span>
			</div>
			<div class="unit">
				<label>物料描述：</label>
				<input type="text" size="25" name="materialDescribe"/>
				<span class="inputInfo">关键字或全称</span>
			</div>
			<div class="unit">
				<label>工序：</label>
				<input type="text" size="25" name="deiviceDescribe"/>
				<span class="inputInfo">关键字或全称</span>
			</div>
			<div class="unit">
				<label>下达人：</label>
				<input type="text" size="25" name="cmdPerson"/>
				<span class="inputInfo">全称</span>
			</div>
			<div class="unit">
				<label>下达日期：</label>
				<input type="text" size="25" name="beginTime" class="date"/>
				<label>到</label>
				<input type="text" size="25" name="stopTime" class="date"/>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">开始检索</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="reset">清空重输</button></div></div></li>
			</ul>
		</div>
	</form>
</div>

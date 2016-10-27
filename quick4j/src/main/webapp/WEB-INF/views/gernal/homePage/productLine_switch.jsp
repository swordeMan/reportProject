<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="pageFormContent" id="main">
	<div id="tabsContent">
		<div style="float:left;">
			<h2 class="contentTitle">一区产线</h2>
			<ul>
				<li><div><a href="rest/material/getMaterialInfo?deviceId=191" max="true" target="navTab" rel="materialNav"><span>一区轮辐</span></a></div></li>
				<li><div><a href="rest/material/getMaterialInfo?deviceId=192" max="true" target="navTab" rel="materialNav"><span>一区轮辋</span></a></div></li>
				<li><div><a href="rest/material/getMaterialInfo?deviceId=193" max="true" target="navTab" rel="materialNav"><span>一区合成</span></a></div></li>
				<li><div><a href="rest/material/getMaterialInfo?deviceId=209" max="true" target="navTab" rel="materialNav"><span>一区涂装</span></a></div></li>
			</ul>
		</div>
		<div style="float:left;">
			<h2 class="contentTitle">二区产线</h2>
			<ul>
				<li><div><a href="rest/material/getMaterialInfo?deviceId=195" max="true" target="navTab" rel="materialNav"><span>二区轮辐</span></a></div></li>
				<li><div><a href="rest/material/getMaterialInfo?deviceId=196" max="true" target="navTab" rel="materialNav"><span>二区轮辋</span></a></div></li>
				<li><div><a href="rest/material/getMaterialInfo?deviceId=197" max="true" target="navTab" rel="materialNav"><span>二区合成</span></a></div></li>
				<li><div><a href="rest/material/getMaterialInfo?deviceId=211" max="true" target="navTab" rel="materialNav"><span>二区涂装</span></a></div></li>
			</ul>
		</div>
		<div style="float:left;">
			<h2 class="contentTitle">三区产线</h2>
			<ul>
				<li><div><a href="rest/material/getMaterialInfo?deviceId=199" max="true" target="navTab" rel="materialNav"><span>三区轮辐</span></a></div></li>
				<li><div><a href="rest/material/getMaterialInfo?deviceId=200" max="true" target="navTab" rel="materialNav"><span>三区轮辋</span></a></div></li>
				<li><div><a href="rest/material/getMaterialInfo?deviceId=201" max="true" target="navTab" rel="materialNav"><span>三区合成</span></a></div></li>
				<li><div><a href="rest/assignment/assignmentView?deviceId=210" max="true" target="navTab" rel="materialNav"><span>三区涂装</span></a></div></li>
			</ul>
		</div>
		<div style="float:left;">
			<h2 class="contentTitle">四区产线</h2>
			<ul>
				<li><div><a href="rest/material/getMaterialInfo?deviceId=206" max="true" target="navTab" rel="materialNav"><span>旋压一</span></a></div></li>
				<li><div><a href="rest/material/getMaterialInfo?deviceId=207" max="true" target="navTab" rel="materialNav"><span>旋压二</span></a></div></li>
				<li><div><a href="rest/material/getMaterialInfo?deviceId=208" max="true" target="navTab" rel="materialNav"><span>旋压三</span></a></div></li>
				<li><div><a href="rest/material/getMaterialInfo?deviceId=212" max="true" target="navTab" rel="materialNav"><span>四区轮辐</span></a></div></li>
				<li><div><a href="rest/material/getMaterialInfo?deviceId=205" max="true" target="navTab" rel="materialNav"><span>四区轮辋</span></a></div></li>
				<li><div><a href="rest/material/getMaterialInfo?deviceId=203" max="true" target="navTab" rel="materialNav"><span>四区合成</span></a></div></li>
				<li><div><a href="rest/material/getMaterialInfo?deviceId=210" max="true" target="navTab" rel="materialNav"><span>四区涂装</span></a></div></li>
			</ul>
		</div>
	</div>
</div>

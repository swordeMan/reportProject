<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="include/include.inc.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>精益生产系统</title>

<link href="themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>

<link href="themes/css/jquery.autocomplete.css" rel="stylesheet" type="text/css"/>
<link href="themes/css/select2.min.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="themes/css/c3.css" rel="stylesheet" type="text/css"/>
<!-- <link href="themes/default/easyui.css" rel="stylesheet" type="text/css" media="screen"/> -->
<!-- <link href="uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/> -->
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lt IE 9]><script src="js/speedup.js" type="text/javascript"></script><script src="js/jquery-1.11.3.min.js" type="text/javascript"></script><![endif]-->
<!--[if gte IE 9]><!--><script src="js/jquery-2.1.4.min.js" type="text/javascript"></script><!--<![endif]-->

<script src="js/jquery.cookie.js" type="text/javascript"></script>
<script src="js/jquery.validate.js" type="text/javascript"></script>
<script src="js/jquery.bgiframe.js" type="text/javascript"></script>
<!-- <script src="xheditor/xheditor-1.2.2.min.js" type="text/javascript"></script>
<script src="xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script> -->

<!-- supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<script type="text/javascript" src="chart/raphael.js"></script>
<script type="text/javascript" src="chart/g.raphael.js"></script>
<script type="text/javascript" src="chart/g.bar.js"></script>
<script type="text/javascript" src="chart/g.line.js"></script>
<script type="text/javascript" src="chart/g.pie.js"></script>
<script type="text/javascript" src="chart/g.dot.js"></script>

<script src="js/dwz.core.js" type="text/javascript"></script>
<script src="js/dwz.util.date.js" type="text/javascript"></script>
<script src="js/dwz.validate.method.js" type="text/javascript"></script>
<script src="js/dwz.barDrag.js" type="text/javascript"></script>
<script src="js/dwz.drag.js" type="text/javascript"></script>
<script src="js/dwz.tree.js" type="text/javascript"></script>
<script src="js/dwz.accordion.js" type="text/javascript"></script>
<script src="js/dwz.ui.js" type="text/javascript"></script>
<script src="js/dwz.theme.js" type="text/javascript"></script>
<script src="js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="js/dwz.navTab.js" type="text/javascript"></script>
<script src="js/dwz.tab.js" type="text/javascript"></script>
<script src="js/dwz.resize.js" type="text/javascript"></script>
<script src="js/dwz.dialog.js" type="text/javascript"></script>
<script src="js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="js/dwz.cssTable.js" type="text/javascript"></script>
<script src="js/dwz.stable.js" type="text/javascript"></script>
<script src="js/dwz.taskBar.js" type="text/javascript"></script>
<script src="js/dwz.ajax.js" type="text/javascript"></script>
<script src="js/dwz.pagination.js" type="text/javascript"></script>
<script src="js/dwz.database.js" type="text/javascript"></script>
<script src="js/dwz.datepicker.js" type="text/javascript"></script>
<script src="js/dwz.effects.js" type="text/javascript"></script>
<script src="js/dwz.panel.js" type="text/javascript"></script>
<script src="js/dwz.checkbox.js" type="text/javascript"></script>
<script src="js/dwz.history.js" type="text/javascript"></script>
<script src="js/dwz.combox.js" type="text/javascript"></script>
<script src="js/dwz.print.js" type="text/javascript"></script>

<!--引入模糊搜索插件  -->
<script src="js/jquery.autocomplete.js" type="text/javascript"></script>

<script src="js/d3.v3.min.js"></script>
<script src="js/c3.min.js" type="text/javascript"></script>
<!-- <script src="js/localdata_admin.js?session=Math.random()" type="text/javascript"></script> -->

<!-- 可以用dwz.min.js替换前面全部dwz.*.js (注意：替换时下面dwz.regional.zh.js还需要引入)-->
<!-- <script src="js/dwz.min.js" type="text/javascript"></script> -->

<script src="js/dwz.regional.zh.js" type="text/javascript"></script>

<script src="js/bootstrap.min.js" type="text/javascript"></script>

<script src="js/select2.min.js" type="text/javascript"></script>

<script type="text/javascript">

$(function(){
	DWZ.init("dwz.frag.xml", {
		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNo", numPerPage:"pageSize", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		keys: {statusCode:"statusCode", message:"message"}, //【可选】
		ui:{hideMode:'offsets'}, //【可选】hideMode:navTab组件切换的隐藏方式，支持的值有’display’，’offsets’负数偏移位置的值，默认值为’display’
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});

</script>
</head>

<body>
	<div id="layout">
		<div id="header">
			<div class="headerNav" >
				<!-- <a class="logo" href="http://j-ui.com">标志</a> -->
				<div style="font-family:SimHei;font-size: 25px; color: white; margin-top: 8px;margin-left: 20px">浙江金固股份有限公司精益生产系统</div>
				<ul class="nav">
					<li><font color="white"><shiro:principal type="java.lang.String"/></font></li>
					<li><a href="rest/user/toUpdatePassword" target="dialog" width="600">密码修改</a></li>
					<li><a href="rest/user/logout">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div></div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2>精益生产 精益管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder expand">
							<li><span>订单计划</span>
								<ul>
									<shiro:hasPermission name="order:view">
										<li><a href="rest/sapOrder/list" target="navTab" rel="productOrderNav">生产订单</a></li>
									</shiro:hasPermission>
									<shiro:hasPermission name="task:view">
										<li><a href="rest/assignment/list" target="navTab" rel="assignmentNav">生产任务</a></li>
									</shiro:hasPermission>
									<!-- <li><a href="http://www.baidu.com" target="navTab" rel="page1">进度上报</a></li> -->
								</ul>
							</li>
							<li><span>订单执行</span>
								<ul>
									<shiro:hasPermission name="scrap:view">
										<li><a href="rest/scrap/list" target="navTab" rel="scrapNav">订单报废</a></li>
									</shiro:hasPermission>
									<shiro:hasPermission name="report:view">
										<li><a href="rest/reportYield/list" target="navTab" rel="reportNav">订单报工</a></li>
									</shiro:hasPermission>
										<li><a href="rest/scrap/scrapExceptionList" target="navTab" rel="scrapExceptionNav">报废异常</a></li>
								</ul>
							</li>
							<li><span>产量采集</span>
								<ul>
									<shiro:hasPermission name="stock:view">
										<li><a href="rest/stock/list" target="navTab" rel="stockNav">待分派量</a></li>
									</shiro:hasPermission>
									<shiro:hasPermission name="correct:view">
										<li><a href="rest/correct/list" target="navTab" rel="correctNav">产量修正</a></li>
									</shiro:hasPermission>
									<shiro:hasPermission name="rework:view">
										<li><a href="rest/rework/list" target="navTab" rel="reworkNav">返修处理</a></li>
									</shiro:hasPermission>
								</ul>
							</li>
							<li><span>分析决策</span>
								<ul>
									<!-- <li><a href="main.html" target="navTab" rel="main">订单执行分析</a></li>
									<li><a href="main.html" target="navTab" rel="main">报废量统计表</a></li> -->
									<li><a href="rest/scrapAnalysis/reasonAnalysis" target="navTab" rel="scrapReasonNav">报废原因分析</a></li>
									<li><a href="rest/reworkAnalysis/reasonAnalysis" target="navTab" rel="reworkReasonNav">返修原因分析</a></li>
									<li><a href="rest/scrapAnalysis/passRateAnalysis" target="navTab" rel="passRateNav">产品合格率分析</a></li>
								</ul>
							</li>
							<li><span>系统配置</span>
								<ul>
									<shiro:hasPermission name="workshop:view">
										<li><a href="rest/workshop/workshopList" target="navTab" rel="workshopNav">车间</a></li>
									</shiro:hasPermission>
									<shiro:hasPermission name="user:view">
										<li><a href="rest/user/list" target="navTab" rel="userLiNav">人员</a></li>
									</shiro:hasPermission>
									<shiro:hasPermission name="role:view">
										<li><a href="rest/role/list" target="navTab" rel="roleNav">角色</a></li>
									</shiro:hasPermission>
									<!-- <li><a href="main.html" target="navTab" rel="main">生成BOM</a></li> -->
								</ul>
							</li>
							<li><span>业务配置</span>
								<ul>
									<shiro:hasPermission name="scrapReason:view">
										<li><a href="rest/scrapReason/list" target="navTab" rel="scrapReasonNav">报废原因</a></li>
									</shiro:hasPermission>
									<shiro:hasPermission name="reworkReason:view">
										<li><a href="rest/reworkReason/list" target="navTab" rel="ReworkReasonNav">返修原因</a></li>
									</shiro:hasPermission>
										<li><a href="rest/material/list" target="navTab" rel="MaterialNav">基础物料</a></li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox" layoutH="0">
					<div class="page unitBox">
						<!-- <div class="accountInfo">
						</div> -->
						<div class="pageFormContent" id="main">
							<!-- <ul>
								<li><div><a class="add" target="dialog" mask="true" max="true" href="rest/scrap/add"><span>新建报废单</span></a></div></li>
								<li><div><a class="add" target="dialog" mask="true" max="true" href="rest/scrap/criteriaQuery"><span>新建报废单</span></a></div></li>
								<li><div><a class="add" target="dialog" mask="true" width="800" height="470" href="rest/assignment/getDeviceInfo"><span>任务下达</span></a></div></li>
								<li><div><a class="add" target="dialog" mask="true" width="870" height="360" max="true" href="rest/rework/insertRework"><span>新建返修单</span></a></div></li>
								<li><div><a href="rest/assignment/list" target="navTab" rel="assignmentNav"><span>任务启动</span></a></div></li>
							</ul> -->
								 <!-- <a class="buttonActive" href="rest/sapOrder/testInsert" target="_blank"><span>点击数据获取</span></a> -->
								<!--<a class="button" href="rest/sapOrder/testReport" target="_blank"><span>点击生产报工</span></a> -->
							<!-- <div class="divider"></div> -->

							<div class="tabs" currentIndex="0" eventType="click">
								<div class="tabsHeader">
									<div class="tabsHeaderContent">
										<ul>
											<li><a href="javascript:;"><span>新建报废单</span></a></li>
											<li><a href="javascript:;"><span>新建返修单</span></a></li>
											<!-- <li><a href="javascript:;"><span>任务启动</span></a></li> -->
											<li><a href="javascript:;"><span>产线切换</span></a></li>
										</ul>
									</div>
								</div>
								<div class="tabsContent" id="tabsContent" style="height:503px;">
									<div>
										<div style="float:left;">
											<h2 class="contentTitle">一区产线</h2>
											<ul>
												<li><div><a class="add" mask="true" href="rest/assignment/assignmentView?deviceId=191" target="dialog" width="670" height="260"><span>一区轮辐</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/assignment/assignmentView?deviceId=192" target="dialog" width="670" height="260"><span>一区轮辋</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/assignment/assignmentView?deviceId=193" target="dialog" width="670" height="260"><span>一区合成</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/assignment/assignmentView?deviceId=209" target="dialog" width="670" height="260"><span>一区涂装</span></a></div></li>
											</ul>
										</div>
										<div style="float:left;">
											<h2 class="contentTitle">二区产线</h2>
											<ul>
												<li><div><a class="add" mask="true" href="rest/assignment/assignmentView?deviceId=195" target="dialog" width="670" height="260"><span>二区轮辐</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/assignment/assignmentView?deviceId=196" target="dialog" width="670" height="260"><span>二区轮辋</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/assignment/assignmentView?deviceId=197" target="dialog" width="670" height="260"><span>二区合成</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/assignment/assignmentView?deviceId=211" target="dialog" width="670" height="260"><span>二区涂装</span></a></div></li>
											</ul>
										</div>
										<div style="float:left;">
											<h2 class="contentTitle">三区产线</h2>
											<ul>
												<li><div><a class="add" mask="true" href="rest/assignment/assignmentView?deviceId=199" target="dialog" width="670" height="260"><span>三区轮辐</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/assignment/assignmentView?deviceId=200" target="dialog" width="670" height="260"><span>三区轮辋</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/assignment/assignmentView?deviceId=201" target="dialog" width="670" height="260"><span>三区合成</span></a></div></li>
												<!-- <li><div><a class="add" mask="true" href="rest/assignment/assignmentView?deviceId=210" target="dialog" width="670" height="260"><span>三区涂装</span></a></div></li> -->
											</ul>
										</div>
										<div style="float:left;">
											<h2 class="contentTitle">四区产线</h2>
											<ul>
												<li><div><a class="add" mask="true" href="rest/assignment/assignmentView?deviceId=206" target="dialog" width="670" height="260"><span>旋压一</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/assignment/assignmentView?deviceId=207" target="dialog" width="670" height="260"><span>旋压二</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/assignment/assignmentView?deviceId=208" target="dialog" width="670" height="260"><span>旋压三</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/assignment/assignmentView?deviceId=212" target="dialog" width="670" height="260"><span>四区轮辐</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/assignment/assignmentView?deviceId=205" target="dialog" width="670" height="260"><span>四区轮辋</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/assignment/assignmentView?deviceId=203" target="dialog" width="670" height="260"><span>四区合成</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/assignment/assignmentView?deviceId=210" target="dialog" width="670" height="260"><span>四区涂装</span></a></div></li>
											</ul>
										</div>
									</div>
									<div>
									 <div style="float:left;">
											<h2 class="contentTitle">一区产线</h2>
											<ul>
												<li><div><a class="add" mask="true" href="rest/rework/insertRework?deviceId=193" target="navTab" width="670" height="360"><span>一区合成</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/rework/insertRework?deviceId=209" target="navTab" width="670" height="260"><span>一区涂装</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/rework/insertRework?deviceId=191" target="navTab" width="670" height="260"><span>一区轮辐</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/rework/insertRework?deviceId=192" target="navTab" width="670" height="260"><span>一区轮辋</span></a></div></li>
											</ul>
										</div>
										<div style="float:left;">
											<h2 class="contentTitle">二区产线</h2>
											<ul>
												<li><div><a class="add" mask="true" href="rest/rework/insertRework?deviceId=197" target="navTab" width="670" height="260"><span>二区合成</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/rework/insertRework?deviceId=211" target="navTab" width="670" height="260"><span>二区涂装</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/rework/insertRework?deviceId=195" target="navTab" width="670" height="260"><span>二区轮辐</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/rework/insertRework?deviceId=196" target="navTab" width="670" height="260"><span>二区轮辋</span></a></div></li>
											</ul>
										</div>
										<div style="float:left;">
											<h2 class="contentTitle">三区产线</h2>
											<ul>
												<li><div><a class="add" mask="true" href="rest/rework/insertRework?deviceId=201" target="navTab" width="670" height="260"><span>三区合成</span></a></div></li>
												<!-- <li><div><a class="add" mask="true" href="rest/rework/insertRework?deviceId=一区涂装" target="navTab" width="670" height="260"><span>三区涂装</span></a></div></li> -->
												<li><div><a class="add" mask="true" href="rest/rework/insertRework?deviceId=199" target="navTab" width="670" height="260"><span>三区轮辐</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/rework/insertRework?deviceId=200" target="navTab" width="670" height="260"><span>三区轮辋</span></a></div></li>
											</ul>
										</div>
										<div style="float:left;">
											<h2 class="contentTitle">四区产线</h2>
											<ul>
												<li><div><a class="add" mask="true" href="rest/rework/insertRework?deviceId=206" target="dialog" width="670" height="260"><span>旋压一</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/rework/insertRework?deviceId=207" target="dialog" width="670" height="260"><span>旋压二</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/rework/insertRework?deviceId=208" target="dialog" width="670" height="260"><span>旋压三</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/rework/insertRework?deviceId=212" target="dialog" width="670" height="260"><span>四区轮辐</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/rework/insertRework?deviceId=205" target="dialog" width="670" height="260"><span>四区轮辋</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/rework/insertRework?deviceId=203" target="dialog" width="670" height="260"><span>四区合成</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/rework/insertRework?deviceId=210" target="dialog" width="670" height="260"><span>四区涂装</span></a></div></li>
											</ul>
										</div>
									</div>
									<!-- <div>
										<div style="float:left;">
											<h2 class="contentTitle">一区产线</h2>
											<ul>
												<li><div><a href="rest/assignment/list?deviceId=191" target="navTab" rel="assignmentNav"><span>一区轮辐</span></a></div></li>
												<li><div><a href="rest/assignment/list?deviceId=192" target="navTab" rel="assignmentNav"><span>一区轮辋</span></a></div></li>
												<li><div><a href="rest/assignment/list?deviceId=193" target="navTab" rel="assignmentNav"><span>一区合成</span></a></div></li>
												<li><div><a href="rest/assignment/list?deviceId=209" target="navTab" rel="assignmentNav"><span>一区涂装</span></a></div></li>
											</ul>
										</div>
										<div style="float:left;">
											<h2 class="contentTitle">二区产线</h2>
											<ul>
												<li><div><a href="rest/assignment/list?deviceId=195" target="navTab" rel="assignmentNav"><span>二区轮辐</span></a></div></li>
												<li><div><a href="rest/assignment/list?deviceId=196" target="navTab" rel="assignmentNav"><span>二区轮辋</span></a></div></li>
												<li><div><a href="rest/assignment/list?deviceId=197" target="navTab" rel="assignmentNav"><span>二区合成</span></a></div></li>
												<li><div><a href="rest/assignment/list?deviceId=211" target="navTab" rel="assignmentNav"><span>二区涂装</span></a></div></li>
											</ul>
										</div>
										<div style="float:left;">
											<h2 class="contentTitle">三区产线</h2>
											<ul>
												<li><div><a href="rest/assignment/list?deviceId=199" target="navTab" rel="assignmentNav"><span>三区轮辐</span></a></div></li>
												<li><div><a href="rest/assignment/list?deviceId=200" target="navTab" rel="assignmentNav"><span>三区轮辋</span></a></div></li>
												<li><div><a href="rest/assignment/list?deviceId=201" target="navTab" rel="assignmentNav"><span>三区合成</span></a></div></li>
												<li><div><a class="add" mask="true" href="rest/assignment/assignmentView?deviceId=210" target="dialog" width="670" height="260"><span>三区涂装</span></a></div></li>
											</ul>
										</div>
										<div style="float:left;">
											<h2 class="contentTitle">四区产线</h2>
											<ul>
												<li><div><a href="rest/assignment/list?deviceId=206" target="navTab" rel="assignmentNav"><span>旋压一</span></a></div></li>
												<li><div><a href="rest/assignment/list?deviceId=207" target="navTab" rel="assignmentNav"><span>旋压二</span></a></div></li>
												<li><div><a href="rest/assignment/list?deviceId=208" target="navTab" rel="assignmentNav"><span>旋压三</span></a></div></li>
												<li><div><a href="rest/assignment/list?deviceId=212" target="navTab" rel="assignmentNav"><span>四区轮辐</span></a></div></li>
												<li><div><a href="rest/assignment/list?deviceId=205" target="navTab" rel="assignmentNav"><span>四区轮辋</span></a></div></li>
												<li><div><a href="rest/assignment/list?deviceId=203" target="navTab" rel="assignmentNav"><span>四区合成</span></a></div></li>
												<li><div><a href="rest/assignment/list?deviceId=210" target="navTab" rel="assignmentNav"><span>四区涂装</span></a></div></li>
											</ul>
										</div>
									</div> -->
									<div>
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
												<!-- <li><div><a href="rest/assignment/assignmentView?deviceId=210" max="true" target="navTab" rel="materialNav"><span>三区涂装</span></a></div></li> -->
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
								<!-- <div class="tabsFooter">
									<div class="tabsFooterContent"></div>
								</div> -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<div id="footer">Copyright &copy; 2016 <a href="demo_page2.html" target="dialog">杭州巨品科技有限公司</a></div>
		
</body>
</html>
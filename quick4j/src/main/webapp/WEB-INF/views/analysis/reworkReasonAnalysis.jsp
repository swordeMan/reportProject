<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../include/include.inc.jsp"%>
<script type="text/javascript">
	var reworkNumList = [];
	var ratioAddList = [];
	var reworkReasonList = [];
	
	<c:forEach var="item" items="${reworkViewList}" varStatus="s">
		reworkNumList.push("${item.reworkNum}");
	</c:forEach>
	
	<c:forEach var="item" items="${ratioAddList}" varStatus="s">
		ratioAddList.push("${item}");
	</c:forEach>
	
	<c:forEach var="item" items="${reworkViewList}" varStatus="s">
		reworkReasonList.push("${item.reason}");
	</c:forEach>
	 
	var chart = c3.generate({
		bindto : '#chart2',
		data : {
			x : 'x',
			json : {
				x : reworkReasonList,
				不合格数量 : reworkNumList,
				累计百分比 : ratioAddList
			},
			axes : {
				累计百分比 : 'y2'
			},
			types : {
				不合格数量 : 'bar'
			}
		},
		bar : {
			width : {
				ratio : 0.1
			// this makes bar width 50% of length between ticks
			}
		},
		axis : {
			x : {
				type : 'category'
			},
			y2 : {
				tick : {
					format : d3.format("%")
				},
				max: 1,
	            min: 0,
				show : true
			}
		}
	});
</script>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="rest/reworkAnalysis/reasonAnalysis" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>月份:</label>
				<input type="text" name="reworkTime" class="date textInput" datefmt="yyyy-MM" value="<fmt:formatDate value="${reworkView.reworkTime}" pattern="yyyy-MM"/>" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</li>	  
			<li>
				<label>组别:</label>
				<select class="describe textInput" name="productionProcedure" style="width:60%">
					<option value="" selected="selected">-请选择-</option>
					<c:forEach var="item" items="${deviceInfoList}" varStatus="s">
						<option value="${item.deiviceDescribe}">${item.deiviceDescribe}</option>
					</c:forEach>
				</select>
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div id="chartTable" layoutH="97">
		<div class="panelBar">
			<h2 class="contentTitle" style="text-align: center"><fmt:formatDate value="${reworkView.reworkTime}" type="date" pattern="yyyy-MM"/>${reworkView.productionProcedure}-返修原因统计表</h2>
		</div>
		<table class="table" id="scrapTab">
			<thead>
				<tr>
					<th width="110">原因</th>
					<c:forEach var="item" items="${reworkViewList}" varStatus="s">
						<th width="110">${item.reason}</th>
					</c:forEach>
					<th width="80">不合格累计</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>不合格数量</td>
					<c:forEach var="item" items="${reworkViewList}" varStatus="s">
						<td>${item.reworkNum}</td>
					</c:forEach>
					<td>${reworkSum}</td>
				</tr>
				<tr>
					<td>比例%</td>
					<c:forEach var="item" items="${ratioList}" varStatus="s">
						<td><fmt:formatNumber value="${item}" type="number" pattern="0.0%" /></td>
					</c:forEach>
					<td></td>
				</tr>
				<tr>
					<td>累计百分比%</td>
					<c:forEach var="item" items="${ratioAddList}" varStatus="s">
						<td><fmt:formatNumber value="${item}" type="number" pattern="0.0%" /></td>
					</c:forEach>
					<td></td>
				</tr>
			</tbody>
		</table>
		<div id="chart2"></div>
	</div>
	<div class="formBar">
		<ul>
			<li>
				<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
			</li>
		</ul>
	</div>
</div>
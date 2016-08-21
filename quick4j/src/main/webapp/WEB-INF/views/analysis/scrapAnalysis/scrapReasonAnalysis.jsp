<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../include/include.inc.jsp"%>
<script type="text/javascript">
	var scrapViewList = [];
	var ratioAddList = [];
	var scrapReasonList = [];
	
	<c:forEach var="item" items="${scrapViewList}" varStatus="s">
		scrapViewList.push("${item.scrapNum}");
	</c:forEach>
	
	<c:forEach var="item" items="${ratioAddList}" varStatus="s">
		ratioAddList.push("${item}");
	</c:forEach>
	
	<c:forEach var="item" items="${scrapViewList}" varStatus="s">
		scrapReasonList.push("${item.sReason}");
	</c:forEach>
	 
	var chart = c3.generate({
		bindto : '#chart1',
		data : {
			x : 'x',
			json : {
				x : scrapReasonList,
				不合格数量 : scrapViewList,
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
	<form onsubmit="return navTabSearch(this);" action="rest/scrapAnalysis/reasonAnalysis" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>月份:</label>
				<input type="text" name="scrapTime" class="date textInput" datefmt="yyyy-MM" value="<fmt:formatDate value="${scrapView.scrapTime}" pattern="yyyy-MM"/>" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</li>	  
			<li>
				<label>组别:</label>
				<select class="describe textInput" name="productionProcess" style="width:60%">
					<option value="" selected="selected">-请选择-</option>
					<c:forEach var="item" items="${deviceInfoList}" varStatus="s">
						<option value="${item.deiviceDescribe}">${item.deiviceDescribe}</option>
					</c:forEach>
				</select>
			</li>
			<li>
				<label>报废类别:</label>
				<select class="textInput" name="classification" style="width:60%">
					<option value="" selected="selected">-请选择-</option>
					<option value="生产报废 ">生产报废</option>
					<option value="技术报废">技术报废</option>
					<option value="材料报废">材料报废</option>
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
			<h2 class="contentTitle" style="text-align: center"><fmt:formatDate value="${scrapView.scrapTime}" type="date" pattern="yyyy-MM"/>${scrapView.productionProcess}-${scrapView.classification}-报废原因统计表</h2>
		</div>
		<table class="table" id="scrapTab">
			<thead>
				<tr>
					<th width="110">原因</th>
					<c:forEach var="item" items="${scrapViewList}" varStatus="s">
						<th width="110">${item.sReason}</th>
					</c:forEach>
					<th width="80">不合格累计</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>不合格数量</td>
					<c:forEach var="item" items="${scrapViewList}" varStatus="s">
						<td>${item.scrapNum}</td>
					</c:forEach>
					<td>${wasteSum}</td>
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
		<div id="chart1"></div>
	</div>
	<div class="formBar">
		<ul>
			<li>
				<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
			</li>
		</ul>
	</div>
</div>
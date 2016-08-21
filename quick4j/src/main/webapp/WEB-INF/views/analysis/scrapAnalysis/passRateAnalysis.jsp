<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="../../include/include.inc.jsp" %>
<script type="text/javascript">

    var productSumList = [];
    var scrapSumList = [];
    var objectiveList = [];
    var scrapRatioList = [];
    var passRateList = [];

    <c:forEach var="item" items="${productSumList}" varStatus="s">
        productSumList.push("${item}");
    </c:forEach>

    <c:forEach var="item" items="${scrapSumList}" varStatus="s">
        scrapSumList.push("${item}");
    </c:forEach>

    <c:forEach var="item" items="${scrapRatioList}" varStatus="s">
        scrapRatioList.push("${item}");
    </c:forEach>

    <c:forEach var="item" items="${passRateList}" varStatus="s">
        passRateList.push("${item}");
    </c:forEach>
    
    for(var i=0;i<10;i++){
    	objectiveList.push("${objective}");
    }
    
	var chart = c3.generate({
		bindto : '#chart',
		data : {
			x : 'x',
			json : {
				x : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月',
						'10月', '11月', '12月' ],
				生产数 : productSumList,
				报废数 : scrapSumList,
				目标值 : objectiveList,
				报废率 : scrapRatioList,
				合格率 : passRateList
			},
			axes : {
				目标值 : 'y2',
				报废率 : 'y2',
				合格率 : 'y2'
			},
			types : {
				生产数 : 'bar',
				报废数 : 'bar'
			}
		},
		bar : {
			width : {
				ratio : 0.3
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
				padding : {
					top : 0,
					bottom : 0
				},
				show : true
			}
		}
	});

	if (productSumList.length == 0) {
		$('chartTable').hide();
	} else {
		$('chartTable').show();
	}
</script>
<%--<div layoutH="0">--%>
<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="rest/scrapAnalysis/passRateAnalysis" method="post">
        <div class="searchBar">
            <ul class="searchContent">
                <li>
                    <label>年份:</label>
                    <input type="text" name="year" class="date textInput" datefmt="yyyy"
                           value="<fmt:formatDate value="${scrapView.year}" pattern="yyyy"/>" readonly="true"/>
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
                <!-- <li>
                    <label>报废类别:</label>
                    <select class="textInput" name="classification" style="width:60%">
                        <option value="" selected="selected">-请选择-</option>
                        <option value="生产报废 ">生产报废</option>
                        <option value="技术报废">技术报废</option>
                        <option value="材料报废">材料报废</option>
                    </select>
                </li> -->
            </ul>
            <div class="subBar">
                <ul>
                    <li>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button id="select" type="submit">查询</button>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </form>
</div>
<div class="pageContent">
    <%--加入loyoutH保证右侧有下拉，97保证下面按钮正常显示--%>
    <div id="chartTable" layoutH="97">
        <div class="panelBar">
            <h2 class="contentTitle" style="text-align: center"><fmt:formatDate value="${scrapView.year}" type="date"
                                                                                pattern="yyyy"/>${scrapView.productionProcess}
                -报废率月别比较推移表</h2>
        </div>
        <table class="table" id="scrapTab">
            <thead>
            <tr>
                <th width="110">月份</th>
                <th width="100">一月</th>
                <th width="100">二月</th>
                <th width="100">三月</th>
                <th width="100">四月</th>
                <th width="100">五月</th>
                <th width="100">六月</th>
                <th width="100">七月</th>
                <th width="100">八月</th>
                <th width="100">九月</th>
                <th width="100">十月</th>
                <th width="100">十一月</th>
                <th width="100">十二月</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>生产数</td>
                <c:forEach var="item" items="${productSumList}" varStatus="s">
                    <td>${item}</td>
                </c:forEach>
            </tr>
            <tr>
                <td>报废数</td>
                <c:forEach var="item" items="${scrapSumList}" varStatus="s">
                    <td>${item}</td>
                </c:forEach>
            </tr>
            <tr>
                <td>目标值</td>
                <td><fmt:formatNumber value="${objective}" type="number" pattern="0.00%"/></td>
                <td><fmt:formatNumber value="${objective}" type="number" pattern="0.00%"/></td>
                <td><fmt:formatNumber value="${objective}" type="number" pattern="0.00%"/></td>
                <td><fmt:formatNumber value="${objective}" type="number" pattern="0.00%"/></td>
                <td><fmt:formatNumber value="${objective}" type="number" pattern="0.00%"/></td>
                <td><fmt:formatNumber value="${objective}" type="number" pattern="0.00%"/></td>
                <td><fmt:formatNumber value="${objective}" type="number" pattern="0.00%"/></td>
                <td><fmt:formatNumber value="${objective}" type="number" pattern="0.00%"/></td>
                <td><fmt:formatNumber value="${objective}" type="number" pattern="0.00%"/></td>
                <td><fmt:formatNumber value="${objective}" type="number" pattern="0.00%"/></td>
                <td><fmt:formatNumber value="${objective}" type="number" pattern="0.00%"/></td>
                <td><fmt:formatNumber value="${objective}" type="number" pattern="0.00%"/></td>
            </tr>
            <tr>
                <td>报废率%</td>
                <c:forEach var="item" items="${scrapRatioList}" varStatus="s">
                    <td><fmt:formatNumber value="${item}" type="number" pattern="0.00%"/></td>
                </c:forEach>
            </tr>
            <tr>
                <td>合格率%</td>
                <c:forEach var="item" items="${passRateList}" varStatus="s">
                    <td><fmt:formatNumber value="${item}" type="number" pattern="0.00%"/></td>
                </c:forEach>
            </tr>
            </tbody>
        </table>
        <div id="chart" style="margin-top: 20px"></div>
    </div>
    <div class="formBar">
        <ul>
            <li>
                <div class="button">
                    <div class="buttonContent">
                        <button type="button" class="close">取消</button>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>
<%--</div>--%>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<script type="text/javascript">
function getScrapNum(){
	var wasteTotal = $("#scrapNum");
	var Sum = 0;
	$("table input[name*='scrapNum']").each(function(){
		if($(this).val()!=""){
			Sum += parseInt($(this).val());
		}
		});
	wasteTotal.val(Sum);
}

function validateIllustration(obj){
	var url="rest/scrapReason/selectByPrimaryKey";//请求后台的url
	if($(obj).val()=="")
	return;//无值，返回 
	$.post(url,{'id':$(obj).val()},function(data){
		if(data.sReason=="其他"){
			$(obj).parents("tr").find("input[name*='illustration']").addClass("required");
		}else{
			$(obj).parents("tr").find("input[name*='illustration']").removeClass("required");
		}
	});
}
	

$(".buttonActive").click(function(){
	getScrapNum();
})
</script>

<div class="pageContent">
	<form id="createScrapForm" method="post" action="<c:url value='rest/scrap/insert?callbackType=closeCurrent'/>" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone,'请刷卡提交',workNo);">
		<%-- <input type="hidden" name="id" value="${sapOrder.id}"/> --%>
		<input type="hidden" name="scrap.saleOrderRow" value="${sapOrder.saleOrderRow}">
		<input type="hidden" name="scrap.createTime" class="date" readonly="readonly" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>"/>
		<div class="pageFormContent" layoutH="56">
			
			<!-- <p>
				<label>报废单：</label>
				<input name="scrap.scrapId" class="required" type="text" size="30"/>
			</p> -->
			<p>
				<label>销售订单：</label>
				<input name="scrap.saleOrderId" readonly="readonly" type="text" value="${ass.saleOrderId}" size="20"/>
			</p>
			<p>
				<label>工序：</label>
				<input name="scrap.productionProcess" readonly="readonly"  type="text" size="20" value="${deviceInfo.deiviceDescribe}"/>
				<%-- <select class="required" name="scrap.productionProcess" style="width:40%">
					<c:forEach var="item" items="${deviceInfoList}">
						<option value="${item.deiviceDescribe}" selected="selected">${item.deiviceDescribe}</option>
					</c:forEach>
				</select> --%>
			</p>
			<p>
				<label>报废量：</label>
				<input name="scrap.scrapNum" class="required" type="text" size="10" value="0" readonly="readonly" id="scrapNum"/>
			</p>
			<p>
				<label>生产订单：</label>
				<input name="scrap.productOrderId" readonly="readonly" type="text" value="${ass.productOrderId}" size="20"/>
			</p>
			<p>
				<label>客户：</label>
				<input name="scrap.userSimpleName" readonly="readonly" type="text" size="20" value="${ass.userSimpleName}"/>
			</p>
			<!-- <p>
				<label>核准量：</label>
				<input name="scrap.scrapAuditing" class="required" type="text" size="30"/>
			</p> -->
			<p>
				<label>物料描述：</label>
				<input name="scrap.materialDescribe" readonly="readonly" type="text" value="${ass.materialDescribe}" size="20"/>
			</p>
			<p>
				<label>报废日期：</label>
				<input type="text" name="scrap.scrapTime" class="date" readonly="readonly" value="<fmt:formatDate value="${assignment.startTime}" pattern="yyyy-MM-dd"/>" size="20"/>
				<!-- <a class="inputDateButton" href="javascript:;">选择</a> -->
			</p>
			<p>
				<label>物料编码：</label>
				<input name="scrap.materialId" readonly="readonly" type="text" value="${ass.materialId}" size="20"/>
			</p>
			<!-- <p>
				<label>工号：</label>
				<input name="workNo" type="text" size="15" alt="工号"/>
			</p> -->
			<p>
				<label>报废分类：</label>
					<select class="required" name="scrap.classification" style="width:40%">
						<option value="">请选择</option>
						<option value="生产报废 ">生产报废</option>
						<option value="技术报废">技术报废</option>
						<option value="材料报废">材料报废</option>
					</select>
			</p>
			<%-- <p>
				<label>制单日期：</label>
				<input type="text" name="scrap.createTime" class="date" readonly="readonly" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>"/>
				<!-- <a class="inputDateButton" href="javascript:;">选择</a> -->
			</p> --%>
			<p>
				<label>班次：</label>
					<select class="required" name="scrap.classes" style="width:40%">
						<option value="">请选择</option>
						<option value="早">早</option>
						<option value="中">中</option>
						<option value="晚">晚</option>
					</select>
			</p>
			<input id="workNo" name="scrap.inspector" style="width:0;height:0;border:none" type="text"/>
				<!-- 隐藏但可以输入的一个input框 -->
			<!-- <p>
				<label>填单人：</label>
				<input id="workNo" name="scrap.inspector" style="width:16%;" type="text"/>
			</p> -->
			<div class="divider"></div>
			<table class="list nowrap itemDetail" addButton="新增原因明细" width="100%">
				<thead>
					<tr>
						<th type="enum" name="scrapReasonRelation[#index#].sReasonId" enumUrl="rest/scrapReason/page?materialDescribe=${ass.materialDescribe}" size="12">报废原因</th>
						<th type="text" fieldclass="required" name="scrapReasonRelation[#index#].scrapNum" size="12">报废数量</th>
						<!-- <th type="enum" name="scrapReasonRelation[#index#].illustration" enumUrl="rest/scrap/page" size="40">备注说明</th> -->
						<th type="text" name="scrapReasonRelation[#index#].illustration" size="40">备注说明</th>
						<th type="del" width="60">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr class="unitBox">
						<td>
							<select class="required" name="scrapReasonRelation[0].sReasonId" style="width:80%" onblur="validateIllustration(this)">
								<c:forEach var="item" items="${scrapReasonList}" varStatus="s">
									<option value="${item.id}" selected="selected">${item.sReason}</option>
								</c:forEach>
							</select>
						</td>
						<td><input type="text" name="scrapReasonRelation[0].scrapNum" size="12" class="required"></td>
						<td><input type="text" name="scrapReasonRelation[0].illustration" size="40"></td>
						<!-- <td>
							<select name="scrapReasonRelation[0].illustration" style="width:80%">
								<option value="生产报废 ">生产报废</option>
								<option value="技术报废">技术报废</option>
								<option value="材料报废">材料报废</option>
							</select>
						</td> -->
						<td><a href="javascript:void(0)" class="btnDel ">删除</a></td>
					</tr>
				</tbody>
			</table>
			<%-- <p>
				<label>报废原因：</label>
				<select class="required" name="sReasonId" style="width:50%">
					<c:forEach var="item" items="${scrapReasonList}" varStatus="s">
						<option value="${item.id}" selected="selected">${item.sReason}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>备注说明：</label>
				<input name="illustration" type="text" size="30"/>
			</p>
			<p>
				<label>报废数量：</label>
				<input name="scrapNum" class="required" type="text" size="30"/>
			</p> --%>
		</div>
		<c:import url="../../include/formBar.jsp"></c:import>
	</form>
	
	<%-- <div id="w_list_print" style="display: none;">
			<table class="list nowrap" width="790">
				<tr>
					<th colspan="6" style="text-align: center;line-height:48px;font-size: 20px;">报废单</th>
				</tr>
				<tr style="line-height:68px;">
					<th>报废单编号:</th>
					<th></th>
					<th></th>
					<th></th>
					<th>客户:</th>
					<th>${ass.userSimpleName}</th>
				</tr>
				<tr style="line-height:68px;">
					<th>产品名称:</th>
					<th>${ass.materialDescribe}</th>
					<th>规格:</th>
					<th>${ass.materialId}</th>
					<th>生产订单:</th>
					<th>${ass.productOrderId}</th>
				</tr>
				<tr style="line-height:68px;">
					<th>生产车间/工序:</th>
					<th>${deviceInfo.deiviceDescribe}</th>
					<th>报废日期:</th>
					<th><fmt:formatDate value="${assignment.startTime}" pattern="yyyy-MM-dd"/></th>
					<th>报废数量:</th>
					<th>${scrapViewList[0].wasteTotal}</th>
				</tr>
				<tr style="line-height:68px;">
					<th colspan="2" style="text-align: center">报废原因</th>
					<th colspan="2" style="text-align: center">报废数量</th>
					<th colspan="2" style="text-align: center">备注</th>
				</tr>
				<c:forEach var="item" items="${scrapViewList}" varStatus="s">
					<tr class="unitBox" style="line-height:48px;text-align: center;">
						<td colspan="2">${item.sReason}</td>
					    <td colspan="2">${item.scrapNum}</td>
						<td colspan="2">${item.illustration}</td>
					</tr>
				</c:forEach>
				<tr style="line-height:68px;">
					<th>检验员:</th>
					<th>${scrapViewList[0].auditor}</th>
					<th>审批:</th>
					<th></th>
					<th>日期:</th>
					<th></th>
				</tr>
			</table>
		</div> --%>
</div>


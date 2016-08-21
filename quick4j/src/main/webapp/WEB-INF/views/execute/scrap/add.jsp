<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">

$(".saleOrder").select2();

var assignmentList;
var saleOrderId;
var i = 0;

/* 方法：销售订单输入后联动物料描述 */
function getMaterialDescribe(obj){
	
		var url="rest/assignment/getMaterialDsc";//请求后台的url
		$(".describe").empty();//先置空
		$(".describe").append($('<option value="">-请选择-</option>'));//加上--请选择--选项
		if($(obj).val()=="")
		return;//无值，返回 
		$.post(url,{'saleOrderId':$(obj).val()},function(data){
			assignmentList = data;
			$.each(data,function(i,s){
				//客户简称加到文本框
				saleOrderId = $(".saleOrder").val();
				if(saleOrderId == s.saleOrderId){
					$(".user").val(s.userSimpleName);
				}
				//物料描述数据加到下拉框
				$(".describe").append($('<option value="'+s.materialDescribe+'">'+s.materialDescribe+'</option>'));
			});
		});
	};
/* 方法：物料描述输入后联动报废原因 */
function getScrapReason(obj){
		/* 根据物料描述和销售订单，将物料和生产订单加到文本框 */
		$.each(assignmentList,function(i,s){
			var materialDes = $(".describe").val();
			if(saleOrderId == s.saleOrderId && materialDes == s.materialDescribe){
				$(".productOrder").val(s.productOrderId);
				$(".materialId").val(s.materialId);
			}
		});
		/* 根据物料描述查询报废原因 */
		var url="rest/scrapReason/getScrapReason";//请求后台的url
		$(".reasonDetail").empty();//先置空
		if(obj=="")
		return;//无值，返回 
		/* $("table tbody").append($('<tr class="unitBox"><td><select class="required reasonDetail" name="scrapReasonRelation['+i+'].sReasonId" style="width:80%"></select></td><td><input type="text" name="scrapReasonRelation['+i+'].scrapNum" size="12" class="required"></td><td><input type="text" name="scrapReasonRelation['+i+'].illustration" size="40"></td><td><a href="javascript:void(0)" class="btnDel">删除</a></td></tr>')); */
		
		$.post(url,{'materialDescribe':obj},function(data){
			$.each(data,function(i,s){
				$(".reasonDetail").append($('<option value="'+s.id+'">'+s.sReason+'</option>'));//后台数据加到下拉框
			});
		});
		
	}
/* 新增原因点击事件	 */
$("#addBtn").click(function(){
	var materialDes = $(".describe").val();
	if(materialDes != "" && materialDes != null){
		i++;
		$("table tbody").append($('<tr class="unitBox"><td><select class="required reasonDetail" name="scrapReasonRelation['+i+'].sReasonId" style="width:80%"></select></td><td><input type="text" name="scrapReasonRelation['+i+'].scrapNum" size="12" class="required"></td><td><input type="text" name="scrapReasonRelation['+i+'].illustration" size="40"></td><td><a href="javascript:void(0)" class="btnDel">删除</a></td></tr>'));
		getScrapReason(materialDes);
	}else{
		return;
	}
	
	/* 删除按钮点击事件 */
	$(".btnDel").click(function(){
		//$(this).parent().parent().remove();
		$(this).parents("tr").remove();
	});
});
/* 方法：根据具体原因得到报废总量 */
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
$(".buttonActive").click(function(){
	getScrapNum();
})

</script>

<div class="pageContent">
	<form id="createScrapForm" method="post" action="<c:url value='rest/scrap/insert?callbackType=closeCurrent'/>" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone,'请刷卡提交',workNo);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>销售订单：</label>
				<select class="saleOrder required" name="scrap.saleOrderId" style="width:60%" onchange="getMaterialDescribe(this)">
					<c:forEach var="item" items="${saleOrderIdList}" varStatus="s">
						<option value="${item}" selected="selected">${item}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>生产订单：</label>
				<input name="scrap.productOrderId" readonly="readonly" type="text" size="30" class="productOrder"/>
			</p>
			<p>
				<label>客户：</label>
				<input name="scrap.userSimpleName" readonly="readonly" type="text" size="30" class="user"/>
			</p>
			<p>
				<label>物料编码：</label>
				<input name="scrap.materialId" readonly="readonly" type="text" size="30" class="materialId"/>
			</p>
			<p>
				<label>物料描述：</label>
				<!-- <input name="materialDescribe" class="required" type="text" size="30"/> -->
				<select class="describe required" name="scrap.materialDescribe" style="width:60%" onchange="getScrapReason(this.value)">
					<!-- <option value="" style="color:#999999">请选择</option> -->
				</select>
			</p>
			<p>
				<label>报废日期：</label>
				<input type="text" name="scrap.scrapTime" class="date" readonly="true" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p>
				<label>报废量：</label>
				<input name="scrap.scrapNum" class="required" type="text" size="10" value="0" readonly="readonly" id="scrapNum"/>
			</p>
			<p>
				<label>工序：</label>
				<!-- <input name="scrap.productionProcess" class="required" type="text" size="30"/> -->
				<select class="required" name="scrap.productionProcess" style="width:60%">
					<c:forEach var="item" items="${deviceInfoList}">
						<option value="${item.deiviceDescribe}" selected="selected">${item.deiviceDescribe}</option>
					</c:forEach>
				</select>
			</p>
				<input id="workNo" name="scrap.inspector" style="width:0;height:0;border:none" type="text"/>
				<!-- 隐藏但可以输入的一个input框 -->
			<p>
				<label>班次：</label>
					<select class="required" name="scrap.classes">
						<option value="">请选择</option>
						<option value="早">早</option>
						<option value="中">中</option>
						<option value="晚">晚</option>
					</select>
			</p>
			<p>
				<label>制单日期：</label>
				<input type="text" name="scrap.createTime" readonly="readonly" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>"/>
				<!-- <a class="inputDateButton" href="javascript:;">选择</a> -->
			</p>
			<div class="divider"></div>
			<input type="button" value="新增原因明细" id="addBtn" />
			<!-- <table class="list nowrap itemDetail" addButton="新增原因明细" width="100%"> -->
			<table class="list nowrap itemDetail" width="100%">
				<thead>
					<tr>
						<th type="enum" name="scrapReasonRelation[#index#].sReasonId" enumUrl="rest/scrapReason/page?materialDescribe=${sapOrder.materialDescribe}" size="12">报废原因</th>
						<th type="text" fieldclass="required" name="scrapReasonRelation[#index#].scrapNum" size="12">报废数量</th>
						<th type="text" name="scrapReasonRelation[#index#].illustration" size="40">备注说明</th>
						<th type="text" width="60">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr class="unitBox">
						<td>
							<select class="required reasonDetail" name="scrapReasonRelation[0].sReasonId" style="width:80%">
								<c:forEach var="item" items="${scrapReasonList}" varStatus="s">
									<option value="${item.id}" selected="selected">${item.sReason}</option>
								</c:forEach>
							</select>
						</td>
						<td><input type="text" name="scrapReasonRelation[0].scrapNum" size="12" class="required"></td>
						<td><input type="text" name="scrapReasonRelation[0].illustration" size="40"></td>
						<td><a href="javascript:void(0)" class="btnDel">删除</a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<c:import url="../../include/formBar.jsp"></c:import>
	</form>
</div>


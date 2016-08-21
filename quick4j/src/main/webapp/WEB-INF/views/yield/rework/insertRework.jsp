<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
  <script type="text/javascript">
  var i= 0;

  /* 方法：物料描述输入后联动返修原因 */
  function getReworkReason(obj){
  		/* 根据物料描述，将物料编码加到文本框 */
  		<c:forEach var="item" items="${stockList}">
  			if(obj == "${item.materialId}")
	//		$(".materialId").val("${item.materialId}");
  			var materialDescribes="${item.materialDescribe}"
		</c:forEach>
  			
  		/* 根据物料描述查询报废原因 */
  		var url="rest/reworkReason/getReworkReason";//请求后台的url
  		$(".reasonDetail").empty();//先置空
  		if(obj=="")
  		return;//无值，返回 
  		/* $("table tbody").append($('<tr class="unitBox"><td><select class="required reasonDetail" name="reworkReasonRelation[0].reworkReasonId" style="width:80%"></select></td><td><input type="text" name="reworkReasonRelation[0].reworkNum" size="12" class="required"></td><td><input type="text" name="reworkReasonRelation[0].illustration" size="40"></td><td><a href="javascript:void(0)" class="btnDel">删除</a></td></tr>')); */
  		
  		$.post(url,{'materialDescribe':materialDescribes},function(data){
  			$.each(data,function(i,s){
  				$(".reasonDetail").append($('<option value="'+s.id+'">'+s.reason+'</option>'));//后台数据加到下拉框
  			});
  		});
  		/* 删除按钮点击事件 */
  		$(".btnDel").click(function(){
  			$(this).parents("tr").remove();
  		});
  	}
  /* 新增原因点击事件	 */
  $("#addBtn").click(function(){
  	var materialDes = $(".describe").val();
  	if(materialDes != "" && materialDes != null){
  		i++;
  		$("table tbody").append($('<tr class="unitBox"><td><select class="required reasonDetail" name="reworkReasonRelation['+i+'].reworkReasonId" style="width:80%"></select></td><td><input type="text" name="reworkReasonRelation['+i+'].reworkNum" size="12" class="required digits"></td><td><input type="text" name="reworkReasonRelation['+i+'].illustration" size="40"></td><td><a href="javascript:void(0)" class="btnDel">删除</a></td></tr>'));
  		getReworkReason(materialDes);
  	}else{
  		return;
  	}
  });
  /* 方法：根据具体原因得到返修总量 */
  function getReworkNum(){
		var wasteTotal = $("#reworkNum");
		var Sum = 0;
		$("table input[name*='reworkNum']").each(function(){
			Sum += parseInt($(this).val());
			});
		wasteTotal.val(Sum);
	}
	$(".buttonActive").click(function(){
		getReworkNum();
	})
  </script>
<div class="pageContent">
	   <form  id="createReworkForm" method="post" action="<c:url value='rest/rework/insert?callbackType=closeCurrent'/>" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone,'请刷卡提交',workNum);">	
	
		<div class="pageFormContent" layoutH="56">
		    <p>
				<label>物料描述：</label>
				<select class="describe required" name="rework.materialId" style="width:60%" onchange="getReworkReason(this.value)">
					<option value="" selected="selected" >--请选择--</option>
					<c:forEach var="item" items="${stockList}" varStatus="s">
						<option value="${item.materialId}"  >${item.materialDescribe}(${item.materialId})</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>工序：</label>
				<input name="rework.productionProcedure" class="required" readonly="readonly"  type="text" size="30"  value="${productionProcedure}"/>
			</p>
			<input id="workNum" name="rework.createPersonnel" style="width:0;height:0;border:none" type="text"/>
				<!-- 隐藏但可以输入的一个input框 -->
			<p>
				<label>返修时间：</label>				
				<input type="text" name="rework.reworkTime" readonly="true" class="date" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p>
				<label>班次：</label>
				<select class="required" name="rework.classes">
						<option value="">请选择</option>
						<option value="早">早</option>
						<option value="中">中</option>
						<option value="晚">晚</option>
					</select>
			</p>
			<p>
				<label>返修数量：</label>
				<input name="rework.reworkNum" class="required digits" type="text" readonly="readonly" size="30" id="reworkNum"/>

			</p>
		
			<div class="divider"></div>
			<input type="button" value="新增原因明细" id="addBtn" />
			<table class="list nowrap itemDetail" width="100%">
				<thead>
					<tr>
						<th type="enum" name="reworkReasonRelation[#index#].id" enumUrl="rest/reworkReason/page?materialDescribe=${stock.materialDescribe}" size="20">返修原因</th>
						<th type="text" name="reworkReasonRelation[#index#].reworkNum" size="12"  class="required digits">返修数量</th>
						<th type="text" name="reworkReasonRelation[#index#].illustration" size="40">备注说明</th>
						<th type="del" width="60">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr class="unitBox">
						<td>
							<select class="reasonDetail required" name="reworkReasonRelation[0].reworkReasonId" style="width:60%" >
								<%-- <c:forEach var="item" items="${reworkReasonList}" varStatus="s">
									<option value="${item.id}" selected="selected">${item.reason}</option>
								</c:forEach> --%>
							</select>
						</td>
						<td><input type="text" name="reworkReasonRelation[0].reworkNum" size="12" class="required digits"></td>
						<td><input type="text" name="reworkReasonRelation[0].illustration" size="40"></td>
						<td><a href="javascript:void(0)" class="btnDel ">删除</a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<c:import url="../../include/formBar.jsp"></c:import>
	</form>
</div>
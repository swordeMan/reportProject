<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
  <script type="text/javascript">
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
	   <form  id="createReworkForm" method="post" action="<c:url value='rest/rework/insert?callbackType=closeCurrent'/>" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		
		<div class="pageFormContent" layoutH="56">
			<!-- <p>
				<label>返修单号：</label>
				<input name="rework.reworkId" class="required"  type="text" size="30"/>
			</p> -->
			<p>
				<label>物料编码：</label>
				<input name="rework.materialId" class="required" readonly="true"  type="text" value="${stock.materialId}" size="30"/>
			</p>
			<p>
				<label>物料描述：</label>
				<input name="rework.materialDescribe" class="required" readonly="true"  type="text" value="${stock.materialDescribe}" size="30"/>
			</p>
			<p>
				<label>工序：</label>
				<select name="rework.productionProcedure"  class="required" >
				<c:forEach var="item" items="${deviceInfoList}" >
				  <option value="${item.deiviceDescribe}">${item.deiviceDescribe}</option>
				</c:forEach>
				</select>
			</p>
			<!-- <p>
				<label>工厂：</label>
				<input name="rework.factory" class="required" type="text"  size="30" value="新工厂"/>
			</p> -->
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
				<input name="rework.reworkNum" class="required digits" type="text"  size="30" id="reworkNum"/>

			</p>
			<%-- <p>
				<label>是否进线：</label>
				<input name="rework.income"  type="radio" checked value="否" />否
				<input name="rework.income" type="radio"  value="是" />是
			</p> 
			<input name="rework.state"  type="hidden" class="required" readonly="true"  value="未审核" size="30" /> --%>
			<div class="divider"></div>
			<table class="list nowrap itemDetail" addButton="新增原因明细" width="100%">
				<thead>
					<tr>
						<th type="enum" name="reworkReasonRelation[#index#].id" enumUrl="rest/reworkReason/page?materialDescribe=${stock.materialDescribe}" size="20">返修原因</th>
						<th type="text" name="reworkReasonRelation[#index#].reworkNum" size="12" fieldclass="required digits">返修数量</th>
						<th type="text" name="reworkReasonRelation[#index#].illustration" size="40">备注说明</th>
						<th type="del" width="60">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr class="unitBox">
						<td>
							<select class="required" name="reworkReasonRelation[0].reworkReasonId" style="width:60%">
								<c:forEach var="item" items="${reworkReasonList}" varStatus="s">
									<option value="${item.id}" selected="selected">${item.reason}</option>
								</c:forEach>
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
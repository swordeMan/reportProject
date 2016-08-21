<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../include/include.inc.jsp"%>
<script type="text/javascript">
/**
 * 报废时的根据链接跳转navtab
 */
function validateHref(form) {
	var $form = $(form);
	var url ="rest/assignment/selectAssignment";
	var newurl = $form.attr("action");
	
	var datas = $form.serializeArray();
	/* var startTime = datas[0].value;
	var materialId = datas[1].value;
	var userSimpleName = datas[2].value;
	url = url+"?startTime="+startTime+"&materialId="+materialId+"&userSimpleName="+userSimpleName; */
	
	if (!$form.valid()) {
		return false;
	}else{
		$.ajax({
			url:url,
			type:'post',
			data:datas,
			success:function(result){
				if(result.length>0){
					navTab.openTab("scrap", newurl,{title:"新建报废单", data:$form.serializeArray(),fresh:true, external:false});
					//event.preventDefault();
					$.pdialog.closeCurrent();
				}else{
					alertMsg.warn("请重新输入条件筛选订单!");
				}
			}
		});
	}
}
</script>
<div class="pageContent">
        <form id="scrapForm" action="rest/assignment/getAssignmentList">
		<div class="pageFormContent" layoutH="56" >
			<p>
				<label>报废日期:</label>
				<input type="text" name="startTime" class="date textInput required" datefmt="yyyy-MM-dd HH:mm:ss" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p>
				<label>物料描述:</label>
				<select class="describe required textInput" name="materialId" style="width:60%">
					<c:forEach var="item" items="${assignmentViewList}" varStatus="s">
						<option value="${item.materialId}" selected="selected">${item.materialDescribe}(${item.materialId})</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>客户简称:</label>
				<select class="customer textInput" name="userSimpleName" style="width:60%">
					<option value="" selected="selected">-请选择-</option>
					<c:forEach var="item" items="${userSimpleList}" varStatus="s">
						<option value="${item}">${item}</option>
					</c:forEach>
				</select>
			</p>
		</div>
		</form>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button onclick="validateHref($('#scrapForm'))">查询</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
</div>

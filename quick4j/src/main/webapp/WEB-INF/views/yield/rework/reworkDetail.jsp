<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="pageContent">
<div class="pageFormContent" layoutH="56" style="heigt:205px;overflow:auto">
       <table  class="list nowrap itemDetail" > 
       <tr><td width="100px">返修单号：</td><td width="250px">"${rework.reworkId}"</td><td width="100px">物料编码：</td><td width="250px">"${rework.materialId}"</td></tr>
       <tr><td>物料描述：</td><td>${rework.materialDescribe}</td><td>工序：</td><td>${rework.productionProcedure}</td></tr>
       <tr><td>工厂：</td><td>${rework.factory}</td><td>返修时间：</td><td><fmt:formatDate value="${rework.reworkTime}" pattern="yyyy-MM-dd"/></td></tr>
       <tr><td>班次：</td><td>${rework.classes}</td><td>返修数量：</td><td>${rework.reworkNum}</td></tr>
       <tr><td>制表人员：</td><td>${rework.createPersonnel}</td><td>制单时间：</td><td><fmt:formatDate value="${rework.createTime}" pattern="yyyy-MM-dd HH:mm"/></td></tr>
       <tr><td>审核人：</td><td>${rework.auditor}</td><td>审核时间：</td><td><fmt:formatDate value="${rework.auditingTime}" pattern="yyyy-MM-dd HH:mm"/></td></tr>
        <tr><td>审核状态：</td><td>${rework.state}</td><td>是否进线：</td><td>${rework.income}</td></tr>
       </table>
       <div class="divider"></div>
			<table class="list nowrap itemDetail" width="100%">
				<thead>
					<tr>
						<th >返修原因</th>
						<th >返修数量</th>
						<th >备注说明</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${reworkReasonRelationList}" varStatus="loop">
					<tr class="unitBox" target="slt_objId" rel="${item.id}">
						<td>${reworkReasonList[loop.count-1]}</td>
					    <td>${item.reworkNum}</td>
						<td>${item.illustration}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			 <dl class="nowrap">  
				<dt style="width:100px">原始凭证：</dt>
				<dd>
				<img src="${rework.file}" alt="返修单原始凭证" >
			    </dd>
			</dl>
		</div>


</div>

</div>
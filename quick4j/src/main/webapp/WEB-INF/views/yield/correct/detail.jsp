<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>修正单号：</label>
				<input name="correctId" readonly="true"  type="text" value="${correct.correctId}" size="30"/>
			</p>
			<p>
				<label>物料编码：</label>
				<input name="materialId" readonly="true"  type="text" value="${correct.materialId}" size="30"/>
			</p>
			<p>
				<label>物料描述：</label>
				<input name="materialDescribe" readonly="true"  type="text" value="${correct.materialDescribe}" size="30"/>
			</p>
			<p>
				<label>修正数量：</label>
				<input name="correctNum"  readonly="true"  type="text" value="${correct.correctNum}" size="30"/>
			</p>
			<p>
				<label>修正人员：</label>
				<input name="revokePersonnel" readonly="true" type="text" value="${correct.revokePersonnel}" size="30" />

			</p>
		    <p>
				<label>制单时间：</label>				
				<input name="createTime" readonly="true"  type="text" value="<fmt:formatDate value="${correct.createTime}" pattern="yyyy-MM-dd HH:mm"/>" size="30"/>
			</p>
			<dl class="nowrap">  
				<dt style="width:70px">修正原因：</dt>
				<dd>
				<textarea  name="createReason" readonly="true"  cols="90" rows="4"  maxlength="120">${correct.createReason}</textarea>
			    </dd>
			</dl>
		</div>
</div>


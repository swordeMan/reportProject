<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="pageContent">
	<form id ="permissionForm" method="post"
		action="<c:url value='rest/role/insert?callbackType=closeCurrent'/>"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">

			<p>
				<label>角色名称：</label> <input name="role.roleName" class="required"
					type="text" size="30" alt="角色名" />
			</p>
			<p>
				<label>描述：</label> <input name="role.description" class="required"
					type="text" size="30" alt="角色描述" />
			</p>
			
			<div
				style="float: left; display: block; margin: 10px; overflow: auto; width: 620px; height: 500px; border: solid 1px #CCC; line-height: 21px; background: #FFF;">
				<ul class="tree expand">
					<li><span>订单计划</span>
						<ul>
							<li>
								<span><label>生产订单</label>
									<input type="checkbox" name="permissions" value="29" />生产订单查看
									<input type="checkbox" name="permissions" value="9" />获取生产订单
									<input type="checkbox" name="permissions" value="10" />手工上报
									<input type="checkbox" name="permissions" value="11" />新建报废单
									<input type="checkbox" name="permissions" value="40" />订单详情
									<input type="checkbox" name="permissions" value="12" />任务下达
								</span>
								<span><label>任务启动</label>
									<label><input type="checkbox" name="permissions" value="30" />生产任务</label>
									<label><input type="checkbox" name="permissions" value="13" />任务启动</label>
								</span>
							</li>
						</ul>
					</li>
					<li><span>订单执行</span>
						<ul>
							<li>
								<span><label>订单报废</label>
									<label><input type="checkbox" name="permissions" value="31" />报废列表查看</label>
									<label><input type="checkbox" name="permissions" value="14" />报废单审核</label>
								</span>
								<span><label>订单报工</label>
									<label><input type="checkbox" name="permissions" value="32" />报工列表查看</label>
									<label><input type="checkbox" name="permissions" value="15" />报工冲销</label>
								</span>
							</li>
						</ul>
					</li>
					<li><span>产量采集</span>
						<ul>
							<li>
								<span><label>待分派量</label>
									<label><input type="checkbox" name="permissions" value="33" />产量查看</label>
									<label><input type="checkbox" name="permissions" value="16" />产量修正</label>
									<label><input type="checkbox" name="permissions" value="17" />返修处理</label>
								</span>
								<span><label>产量修正</label>
									<label><input type="checkbox" name="permissions" value="34" />修正列表查看</label>
									<label><input type="checkbox" name="permissions" value="18" />查看详情</label>
								</span>
								<span><label>返修处理</label>
									<label><input type="checkbox" name="permissions" value="35" />返修列表查看</label>
									<label><input type="checkbox" name="permissions" value="19" />查看详情</label>
									<label><input type="checkbox" name="permissions" value="20" />返修审核</label>
								</span>
							</li>
						</ul>
					</li>
					<li><span>系统配置</span>
						<ul>
							<li>
								<span><label>车间</label>
									<label><input type="checkbox" name="permissions" value="36" />查看</label>
									<label><input type="checkbox" name="permissions" value="37" />添加</label>
									<label><input type="checkbox" name="permissions" value="38" />编辑</label>
									<label><input type="checkbox" name="permissions" value="39" />删除</label>
								</span>
								<span><label>人员</label>
									<label><input type="checkbox" name="permissions" value="4" />查看</label>
									<label><input type="checkbox" name="permissions" value="1" />添加</label>
									<label><input type="checkbox" name="permissions" value="2" />编辑</label>
									<label><input type="checkbox" name="permissions" value="3" />删除</label>
								</span>
								<span><label>角色</label>
									<label><input type="checkbox" name="permissions" value="8" />查看</label>
									<label><input type="checkbox" name="permissions" value="5" />添加</label>
									<label><input type="checkbox" name="permissions" value="6" />编辑</label>
									<label><input type="checkbox" name="permissions" value="7" />删除</label>
								</span>
							</li>
						</ul>
					</li>
					<li><span>业务配置</span>
						<ul>
							<li>
								<span><label>报废原因</label>
									<label><input type="checkbox" name="permissions" value="24" />查看</label>
									<label><input type="checkbox" name="permissions" value="21" />添加</label>
									<label><input type="checkbox" name="permissions" value="22" />编辑</label>
									<label><input type="checkbox" name="permissions" value="23" />删除</label>
								</span>
								<span><label>返修原因</label>
									<label><input type="checkbox" name="permissions" value="28" />查看</label>
									<label><input type="checkbox" name="permissions" value="25" />添加</label>
									<label><input type="checkbox" name="permissions" value="26" />编辑</label>
									<label><input type="checkbox" name="permissions" value="27" />删除</label>
								</span>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
		<c:import url="../../include/formBar.jsp"></c:import>
	</form>
</div>


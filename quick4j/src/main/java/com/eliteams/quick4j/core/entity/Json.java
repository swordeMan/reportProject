package com.eliteams.quick4j.core.entity;

import java.io.Serializable;

/**
 * Result : 响应的结果对象
 *
 * @author StarZou
 * @since 2014-09-27 16:28
 */
public class Json implements Serializable {
	private static final long serialVersionUID = 6288374846131788743L;

	private String statusCode;

	private String message;

	private String confirmMsg;

	private String navTabId;

	private String rel;

	private String callbackType;

	private String forwardUrl;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getConfirmMsg() {
		return confirmMsg;
	}

	public void setConfirmMsg(String confirmMsg) {
		this.confirmMsg = confirmMsg;
	}

	public String getNavTabId() {
		return navTabId;
	}

	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getCallbackType() {
		return callbackType;
	}

	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public Json ajaxConfirmMsg(String statusCode, String message, String forwardUrl) {
		this.setStatusCode(statusCode);
		this.setConfirmMsg(message);
		this.setForwardUrl(forwardUrl);
		return this;
	}
	
	public Json ajaxDone(String statusCode, String message, String forwardUrl) {
		this.setStatusCode(statusCode);
		this.setMessage(message);
		this.setForwardUrl(forwardUrl);
		return this;
	}
	
	public Json ajaxDoneSuccess() {
		return ajaxDone("200", "操作成功", "");
	}

	public Json ajaxDoneError() {
		return ajaxDone("300", "操作失败", "");
	}
	
	public Json ajaxDoneSuccess(String message) {
		return ajaxDone("200", message, "");
	}
	
	public Json ajaxDoneError(String message) {
		return ajaxDone("300", message, "");
	}
	
	public Json ajaxDoneSuccess(String message,String forwardUrl) {
		return ajaxDone("200", message, forwardUrl);
	}
	
	public Json ajaxDoneError(String message,String forwardUrl) {
		return ajaxDone("300", message, forwardUrl);
	}
	
	public Json ajaxConfirmSuccess(String message) {
		return ajaxConfirmMsg("200", message, "");
	}
	
	public Json ajaxConfirmError(String message) {
		return ajaxConfirmMsg("300", message, "");
	}
}

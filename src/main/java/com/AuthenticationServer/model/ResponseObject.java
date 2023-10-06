package com.AuthenticationServer.model;

public class ResponseObject {
	
	private Boolean validToken;
	private String insCode;
	private String roleId;
	public ResponseObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResponseObject(Boolean validToken, String insCode, String roleId) {
		super();
		this.validToken = validToken;
		this.insCode = insCode;
		this.roleId = roleId;
	}
	public Boolean getValidToken() {
		return validToken;
	}
	public void setValidToken(Boolean validToken) {
		this.validToken = validToken;
	}
	public String getInsCode() {
		return insCode;
	}
	public void setInsCode(String insCode) {
		this.insCode = insCode;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "ResponseObject [validToken=" + validToken + ", insCode=" + insCode + ", roleId=" + roleId + "]";
	}
	
	

}

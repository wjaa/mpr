package br.com.wjaa.mpr.vo;

import com.google.gson.annotations.Expose;

public class EmailServerConfigVO {

	@Expose
	private String smtp;
	@Expose
	private Integer port;
	@Expose
	private String user;
	@Expose
	private String pass;
	@Expose
	private Boolean ssl;



	public Boolean getSsl() {
		return ssl;
	}
	public void setSsl(Boolean ssl) {
		this.ssl = ssl;
	}
	public String getSmtp() {
		return smtp;
	}
	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
}

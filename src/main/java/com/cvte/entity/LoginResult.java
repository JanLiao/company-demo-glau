package com.cvte.entity;

import java.util.List;

/**
* @author jan
* @data 2018年8月13日 下午2:01:30
*/
public class LoginResult<T> {
	private boolean error;
	
	private String user;
	
	private String token;
	
	private String terminalId;
	
	private String terminalPsw;
	
	private List<T> results;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getTerminalPsw() {
		return terminalPsw;
	}

	public void setTerminalPsw(String terminalPsw) {
		this.terminalPsw = terminalPsw;
	}

	public LoginResult(boolean error, String user, String token) {
		super();
		this.error = error;
		this.user = user;
		this.token = token;
	}

	public LoginResult() {
		super();
	}
	
}

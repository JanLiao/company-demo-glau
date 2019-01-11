package com.cvte.entity;

import java.io.Serializable;
import java.util.List;

/**
* @author jan
* @data 2018年8月21日 上午3:13:05
*/
public class RegisterResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5187142028160592903L;
	private boolean error;
	private String msg;
	private List<T> results;
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<T> getResults() {
		return results;
	}
	public void setResults(List<T> results) {
		this.results = results;
	}
}

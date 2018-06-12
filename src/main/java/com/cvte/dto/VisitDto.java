package com.cvte.dto;

import java.util.List;

/** 
* @author: jan 
* @date: 2018年4月20日 下午4:39:43 
*/
public class VisitDto {

	private String name;
	
	private List<String> date;
	
	private List<Integer> data;
	
	private int total;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getDate() {
		return date;
	}

	public void setDate(List<String> date) {
		this.date = date;
	}

	public List<Integer> getData() {
		return data;
	}

	public void setData(List<Integer> data) {
		this.data = data;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public VisitDto(String name, List<String> date, List<Integer> data, int total) {
		super();
		this.name = name;
		this.date = date;
		this.data = data;
		this.total = total;
	}

	public VisitDto() {
		super();
	}

	@Override
	public String toString() {
		return "VisitDto [name=" + name + ", date=" + date + ", data=" + data + ", total=" + total + "]";
	}

}

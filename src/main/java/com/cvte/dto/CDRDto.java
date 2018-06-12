package com.cvte.dto;

import java.util.List;

/** 
* @author: jan 
* @date: 2018年4月21日 上午9:23:13 
*/
public class CDRDto {

	private String name;
	
	private List<String> data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

	public CDRDto(String name, List<String> data) {
		super();
		this.name = name;
		this.data = data;
	}

	public CDRDto() {
		super();
	}

	@Override
	public String toString() {
		return "CDRDto [name=" + name + ", data=" + data + "]";
	}

}

package com.cvte.dto;
/** 
* @author: jan 
* @date: 2018年4月20日 上午11:48:06 
*/
public class QulityDto {

	private String name;
	
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public QulityDto(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public QulityDto() {
		super();
	}

	@Override
	public String toString() {
		return "QulityDto [name=" + name + ", value=" + value + "]";
	}

}

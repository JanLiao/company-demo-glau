package com.cvte.util;

import java.util.Map;

/** 
* @author: jan 
* @date: 2018年4月13日 下午12:02:38 
*/
public class HqlString {

	public static String generateHql(String param, String className) {
		
		String hql = "";

		if("".equals(param)) {
			hql = "from " + className;
		}else {
			String[] str = param.split(",");
			//StringBuffer hql = new StringBuffer("");
			hql = "from " + className + " where ";
			int len = str.length / 3;
			for(int i = 0; i < len; i++) {
				if(i == 0) {
					hql = hql + str[i * 3] + " " + str[i * 3 + 1] + "  " + str[i * 3 + 2];
				}else {
					hql = hql + " and " + str[i * 3] + " " + str[i * 3 + 1] + "  " + str[i * 3 + 2];
				}
			}
		}

		System.out.println("产生的hql=" + hql);
		return hql;
	}

	
	
}

package com.cvte.dao.impl;

import com.cvte.entity.Terminal;

/** 
* @author: jan 
* @date: 2018年6月14日 上午11:15:55 
*/
public interface TerminalDao {

	Terminal queryByAccount(String account, String psw);

}

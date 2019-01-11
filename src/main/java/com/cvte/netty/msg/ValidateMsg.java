package com.cvte.netty.msg;


public class ValidateMsg extends BaseMsg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5348451732729954821L;
	private String account; // 登录的账号 此处指终端的序列号或者其它的唯一验证信息
	private String password; // 验证的密码


	public ValidateMsg(String account, String password) {
		this.account = account;
		this.password = password;
		setMsgType(MsgType.Validate);
	}


	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ValidateMsg [account=" + account + ", password=" + password + "]";
	}

}

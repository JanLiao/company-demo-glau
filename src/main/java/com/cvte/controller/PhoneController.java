package com.cvte.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cvte.entity.Account;
import com.cvte.entity.Appoint;
import com.cvte.entity.HospitalDescBean;
import com.cvte.entity.LoginResult;
import com.cvte.entity.Message;
import com.cvte.entity.RegisterResult;
import com.cvte.entity.Report;
import com.cvte.entity.ReportResult;
import com.cvte.entity.Terminal;
import com.cvte.msg.PhoneResultMsg;
import com.cvte.service.impl.PhoneService;
import com.cvte.util.SendMessageUtil;

/**
* @author jan
* @data 2018年8月4日 上午11:35:58
*/
@Controller
public class PhoneController {
	
	@Autowired
	private PhoneService phoneService;
	
	private Logger logger = Logger.getLogger(PhoneController.class);

	@RequestMapping(value="/allhospital")
	@ResponseBody
	public PhoneResultMsg<HospitalDescBean> hospitalList(){
		List<HospitalDescBean> list = phoneService.queryHospital();
		PhoneResultMsg<HospitalDescBean> result = new PhoneResultMsg<HospitalDescBean>();
		result.setError(false);
		result.setResults(list);
		result.setCount(list.size());
		return result;
	}
	
	@RequestMapping(value="/hospitaldetail")
	@ResponseBody
	public PhoneResultMsg<HospitalDescBean> hospitalDetail(){
		List<HospitalDescBean> list = phoneService.queryHospital();
		PhoneResultMsg<HospitalDescBean> result = new PhoneResultMsg<HospitalDescBean>();
		result.setError(false);
		result.setResults(list);
		result.setCount(list.size());
		return result;
	}
	
	@RequestMapping(value="/hospitalserver")
	@ResponseBody
	public static PhoneResultMsg<String> hospitalServer(String serverType, String serverTime){
		System.out.println("收到信息=" + serverType + "=" + serverTime);
		PhoneResultMsg<String> result = new PhoneResultMsg<String>();
		result.setError(false);
		return result;
	}
	

	@RequestMapping(value="/accountInfo")
	@ResponseBody
	public RegisterResult<String> saveUserInfo(String account, String sex, String age){
		System.out.println(sex + "=" + age);
		RegisterResult<String> result = new RegisterResult<String>();
		phoneService.updateAccount(account, sex, age);
		result.setError(true);
		result.setMsg("个人信息完善成功");
		return result;
	}
	
	@RequestMapping(value="/registerAccount")
	@ResponseBody
	public RegisterResult<String> registerAccount(String account, String password, String phone){
		System.out.println(account + "=" + password + "=" + phone);
		RegisterResult<String> result = new RegisterResult<String>();
		Account act = phoneService.queryOnlyAccount(account);
		if(act != null) {
			result.setError(false);
			result.setMsg("该用户名已被注册,请重新输入");
			return result;
		}else {
			Account act1 = phoneService.queryAccountByPhone(phone);
			if(act1 != null) {
				result.setError(false);
				result.setMsg("该手机已被注册,请重新输入");
				return result;
			}else {
				
				// 先生成一个终端长连接账号
				int terminalId = phoneService.saveTerminal();
				
				// 注册用户
				phoneService.registerAccount(account, password, phone, terminalId);
				System.out.println("注册成功");
				logger.info("注册成功");
				result.setError(true);
				result.setMsg("注册成功");
				return result;
			}
		}
		
	}
	
	@RequestMapping(value="/loginAccount")
	@ResponseBody
	public LoginResult<String> loginAccount(String account, String password) {
		System.out.println(account + "=" + password + "=");
		LoginResult<String> result = new LoginResult<String>();
		if("".equals(account) || "".equals(password)) {
			result.setError(false);
			return result;
		}
		Account act = phoneService.queryAccount(account, password);
		if(act == null) {
			result.setError(false);
		}else {
			result.setError(true);
			result.setToken(act.getToken());
			result.setUser(account);
			Terminal terminal = phoneService.getTerminal(act.getTerminalId());
			result.setTerminalId(terminal.getTid());
			result.setTerminalPsw(terminal.getPassword());
		}
		return result;
	}
	
	@RequestMapping(value="/uploadImage")
	@ResponseBody
    public void uploadImage(@RequestParam("file") CommonsMultipartFile[] files, 
    		String description, HttpServletRequest request, 
    		HttpServletResponse response) throws IOException{  
        logger.info("description = " + description + "=" + files.length);
		System.out.println("description = " + description + "=" + files.length);
		String rootPath = this.getClass().getResource("/").getPath().replaceAll("%20", "");
		rootPath = rootPath.substring(0, rootPath.indexOf("WEB-INF")) + "appImg/" + description.substring(1, description.length() - 1) + "/";
		if(!new File(rootPath).exists()) {
			new File(rootPath).mkdirs();
		}
		int lId = 0;
		int rId = 0;
		List<String> listName = new ArrayList<String>();
		for (int i = 0; i < files.length; i++) {
			System.out.println("fileName---------->" + files[i].getOriginalFilename());

			if (!files[i].isEmpty()) {
				int pre = (int) System.currentTimeMillis();
				try {
					// 拿到输出流，同时重命名上传的文件
					FileOutputStream os = new FileOutputStream(rootPath + files[i].getOriginalFilename());
					logger.info("path = " + rootPath + files[i].getOriginalFilename());
					// 拿到上传文件的输入流
					FileInputStream in = (FileInputStream) files[i].getInputStream();
					// 以写字节的方式写文件
					int b = 0;
					while ((b = in.read()) != -1) {
						os.write(b);
					}
					os.flush();
					os.close();
					in.close();
					int finaltime = (int) System.currentTimeMillis();
					System.out.println(finaltime - pre);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("上传出错");
				}
			}
			listName.add(files[i].getOriginalFilename());
		}
		
		PrintWriter pw = response.getWriter();
		pw.println(true);
		pw.flush();
		pw.close();
		
		for(int i = 0, len = listName.size(); i < len; i++) {
			int id = phoneService.saveImgData(listName.get(i), rootPath + listName.get(i));
			String[] str = listName.get(i).split("_");
			if("L".equals(str[1])) {
				lId = id;
			}else if("R".equals(str[1])) {
				rId = id;
			}
		}
		
		Account act = phoneService.queryByAccount(description.substring(1, description.length() - 1));
		// 保存report信息
		phoneService.saveReport(lId, rId, act.getId());
		
		// 生成消息对象
		Message message = new Message();
		message.setAccountId(act.getId());
		message.setGenerateTime(new Date());
		message.setIconUrl("pic/shiyuan.jpg");
		message.setMessageId(1);
		message.setMessageSrc("小源");
		message.setTitle("有新的报告待查看");
		message.setType(1);
		phoneService.saveMessage(message);
		Terminal terminal = phoneService.getTerminal(act.getTerminalId());
		
		logger.info("开始通知APP有消息= " + message + "=" + terminal.getTid());
		// 通知APP有消息
		SendMessageUtil.sendMessage(terminal.getTid());
    }
	
	@RequestMapping(value="/appointList")
	@ResponseBody
	public PhoneResultMsg<Appoint> getAllAppoint(String account){
		System.out.println("account = " + account);
		List<Appoint> list = phoneService.getAllAppoint(account);
		if(list.size() != 0) {
			System.out.println("" + list.get(0));
		}
		PhoneResultMsg<Appoint> msg = new PhoneResultMsg<Appoint>();
		msg.setCount(list.size());
		msg.setError(true);
		msg.setResults(list);
		return msg;
	}
	
	@RequestMapping(value="/appointService")
	@ResponseBody
	public void addAppointService(String serverTime, String serverType, String account
			, HttpServletResponse response) throws IOException {
		System.out.println("param=" + serverTime + "=" + serverType + "=" + account);
		logger.info("param=" + serverTime + "=" + serverType + "=" + account);
		boolean flag = phoneService.saveAppointService(serverTime, serverType, account);
		PrintWriter pw = response.getWriter();
		if(flag) {
			pw.println(true);
			pw.flush();
			pw.close();
		}else {
			pw.println(false);
			pw.flush();
			pw.close();
		}
		
		Account act = phoneService.queryByAccount(account);
		// 生成消息对象
		Message message = new Message();
		message.setAccountId(act.getId());
		message.setGenerateTime(new Date());
		message.setIconUrl("pic/shiyuan.jpg");
		message.setMessageId(1);
		message.setMessageSrc("小源");
		message.setTitle("你已预约成功,待医院确定,记得准时到医院检查");
		message.setType(2);
		phoneService.saveMessage(message);
		
		Terminal terminal = phoneService.getTerminal(act.getTerminalId());

		System.out.println("开始通知APP有消息= " + message + "=" + terminal.getTid());
		logger.info("开始通知APP有消息= " + message + "=" + terminal.getTid());
		// 通知APP有消息
		SendMessageUtil.sendAppointMessage(terminal.getTid());
	}
	
	@RequestMapping(value="/reportList")
	@ResponseBody
	public PhoneResultMsg<Report> getAllReport(String account){
		System.out.println("account = " + account);
		List<Report> list = phoneService.getAllReport(account);
		if(list.size() != 0) {
			System.out.println("" + list.get(0));
		}
		PhoneResultMsg<Report> msg = new PhoneResultMsg<Report>();
		msg.setCount(list.size());
		msg.setError(true);
		msg.setResults(list);
		return msg;
	}
	
	@RequestMapping(value="/reportDetail")
	@ResponseBody
	public ReportResult getReportDetail(String reportId){
		System.out.println("reportId = " + reportId);
		ReportResult result = phoneService.getReportDetail(reportId);
		System.out.println("real Result = " + result);
		return result;
	}
	
	@RequestMapping(value="/messageList")
	@ResponseBody
	public PhoneResultMsg<Message> getAllMessage(String account){
		System.out.println("account = " + account);
		List<Message> list = phoneService.getAllMessage(account);
		PhoneResultMsg<Message> msg = new PhoneResultMsg<Message>();
		msg.setCount(list.size());
		msg.setError(true);
		msg.setResults(list);
		return msg;
	}

}

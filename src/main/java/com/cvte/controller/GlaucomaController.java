package com.cvte.controller;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cvte.dao.impl.ImageDao;
import com.cvte.dto.CDRDto;
import com.cvte.dto.PersonDto;
import com.cvte.dto.QulityDto;
import com.cvte.dto.VisitDto;
import com.cvte.entity.EyeInfo;
import com.cvte.entity.ImageResult;
import com.cvte.entity.Person;
import com.cvte.entity.RiskDto;
import com.cvte.msg.PageResultMsg;
import com.cvte.msg.Result;
import com.cvte.service.impl.PatientService;
import com.cvte.service.impl.VisitService;
import com.cvte.util.ProcessImage;

@Controller
public class GlaucomaController {
	
	@Autowired
	private ImageDao imgDao;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private VisitService visitService;
	
	private Logger logger = Logger.getLogger(GlaucomaController.class);
	
	@RequestMapping("/view")
	public String viewTest() {
		imgDao.testDao();
		return "test1";
	}
	
	@RequestMapping("/data")
	public String imgList(Model model, HttpServletRequest request) {
		return "data";
	}

	@RequestMapping("/result")
	public String fileList(Model model, HttpServletRequest request) {
		return "test1";
	}
	
	@RequestMapping("/cdr")
	public String cdrAnaly() {
		return "cdrAnalysis";
	}
	
	@RequestMapping("/cdrAnalysis")
	@ResponseBody
	public List<CDRDto> cdrAnalysis(){
		List<CDRDto> list = visitService.getAllCdr();
		return list;
	}
	
	@RequestMapping("visit")
	public String visit() {
		//测试
		
		return "visitGraph";
	}
	
	@RequestMapping("visitAnalysis")
	@ResponseBody
	public List<VisitDto> visitAnalysis(String date, String sex){
		System.out.println(date + "=" + sex);
		List<String> terminal = patientService.getAllTerminal();
		List<VisitDto> list = visitService.visitAnalysis(terminal, date, sex);
		return list;
	}
	
	@RequestMapping("/qulity")
	public String qulityAnalysis() {
		//测试
//		List<QulityDto> list = patientService.getQulityBySex("0");
//		System.out.println(list.get(0));
//		System.out.println(list.get(1));
//		System.out.println(list.get(2));
		return "qulityAnalysis";
	}
	
	@RequestMapping("qulityAnalysis")
	@ResponseBody
	public List<QulityDto> qulityAnaly(String sex){
		List<QulityDto> list = patientService.getQulityBySex(sex);
		return list;
	}
	
	@RequestMapping("/risk")
	public String riskAnalysis() {
		//测试
		//List<RiskDto> list = patientService.riskAnalysis("1,0,0,0", "0");
		//System.out.println(list);
		return "riskAnalysis";
	}
	
	@RequestMapping("/riskAnalysis")
	@ResponseBody
	public List<RiskDto> riskAnaly(String param, String sex){
		logger.info("param=" + param + ",sex=" + sex);
		List<RiskDto> list = patientService.riskAnalysis(param, sex);
		return list;
	}
	
	@RequestMapping("/imgResultList")
	public String imgResultList(HttpServletRequest request) {
		List<String> list = patientService.getAllTerminal();
		request.getSession().setAttribute("terminal", list);
		return "resultList";
	}
	
	@RequestMapping("/statistics")
	public String statistics(Model model, HttpServletRequest request) {
		List<String> list = patientService.getAllTerminal();
		request.getSession().setAttribute("terminalList", list);
		return "statisticsList";
	}
	
	@RequestMapping("/userInfo")
	public void userInfo(HttpServletResponse response) throws IOException {
		System.out.println("查看个人信息");
		logger.info("查看个人信息");
		PrintWriter out = response.getWriter();
		out.print("true");
		out.flush();
		out.close();
	}
	
	@RequestMapping("/imgUserInfo")
	@ResponseBody
	public Result<String> imgUserInfo(String tid, String imgName){
		Result<String> result = new Result<String>();
		String pid = patientService.queryByTidAndUid(tid, imgName);
		if("".equals(pid)) {
			result.setFail(1);
		}else {
			result.setFail(0);
			result.setMsg(pid);
		}
		return result;
	}
	
	@RequestMapping("/downloadPDFOld")
	public ResponseEntity<byte[]> downloadPDF(String pid, String pdfUrl, HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		System.out.println(pid + "=" + pdfUrl);
		logger.info("start to download PDF");
		Person p = patientService.queryById(pid);
		String[] str = p.getLrpdf().split("/");
		String filename = str[str.length - 1];
		// 读取pdf文件到字节里
	    Path path = Paths.get(p.getLrpdf());
	    byte[] contents = Files.readAllBytes(path);

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    headers.setContentDispositionFormData("attachment", new String(filename.getBytes("utf-8"), "ISO8859-1"));
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    ResponseEntity<byte[]> respon = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
	    return respon;
	}
	
	@SuppressWarnings("resource")
	@RequestMapping("/downloadPDF")
	public ResponseEntity<byte[]> downloadPDFOld(String pid, String pdfUrl, HttpServletRequest request) throws IOException {
		//下载文件路径
        System.out.println(pid + "=pdfUrl=" + pdfUrl);
        //String[] str = pdfUrl.split("/");
        
        Person p = patientService.queryById(pid);
        String[] str = p.getLrpdf().split("/");
        System.out.println("person=" + p);
        System.out.println("url=" + p.getLrpdf());
        logger.info("person=" + p);
        logger.info(pid + "=" + pdfUrl);
        System.out.println("filename=" + str[str.length - 1]);
		InputStream in = new FileInputStream(new File(p.getLrpdf()));// 将该文件加入到输入流之中
		byte[] body = null;
		body = new byte[in.available()];// 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
		in.read(body);// 读入到输入流里面
		String fileName = new String(str[str.length - 1].getBytes("gbk"), "iso8859-1");// 防止中文乱码
		HttpHeaders headers = new HttpHeaders();// 设置响应头
		headers.add("Content-Disposition", "attachment;filename=" + fileName);
		//headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType((MediaType.parseMediaType("application/pdf")));
		HttpStatus statusCode = HttpStatus.OK;// 设置响应吗
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
		return response;
	}
	
	@RequestMapping("/downloadOnly")
	public ResponseEntity<byte[]> uploadImgDownloadPDF(HttpServletRequest request) throws IOException {
		String pdfUrl = "F:/eclipse-workspace-new1/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/"
				+ "Glaucoma/csvReport/086_20180403230525LR.pdf";
		//下载文件路径
        System.out.println("=pdfUrl=" + pdfUrl);
        //String[] str = pdfUrl.split("/");
        
        String[] str = pdfUrl.split("/");
        System.out.println("filename=" + str[str.length - 1]);
		InputStream in = new FileInputStream(new File(pdfUrl));// 将该文件加入到输入流之中
		byte[] body = null;
		body = new byte[in.available()];// 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
		in.read(body);// 读入到输入流里面
		String fileName = new String(str[str.length - 1].getBytes("gbk"), "iso8859-1");// 防止中文乱码
		HttpHeaders headers = new HttpHeaders();// 设置响应头
		headers.add("Content-Disposition", "attachment;filename=" + fileName);
		//headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType((MediaType.parseMediaType("application/pdf")));
		HttpStatus statusCode = HttpStatus.OK;// 设置响应吗
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
		return response;
	}
	
	@RequestMapping("/uploadOnly")
	public void uploadOnly( @RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
		System.out.println("name = " + file.getOriginalFilename());
		if (!file.isEmpty()) {
			try {
				// 文件存放服务端的位置
				String rootPath = "D:/tmpPDF";
				File dir = new File(rootPath + File.separator);
				if (!dir.exists())
					dir.mkdirs();
				// 写文件到服务器
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				file.transferTo(serverFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping("/uploadImgDownloadPDF")
	public ResponseEntity<byte[]> uploadImgDownloadPDF( @RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
		System.out.println("name = " + file.getOriginalFilename());
		if (!file.isEmpty()) {
			try {
				// 文件存放服务端的位置
				String rootPath = "D:/tmpPDF";
				File dir = new File(rootPath + File.separator);
				if (!dir.exists())
					dir.mkdirs();
				// 写文件到服务器
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				file.transferTo(serverFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		String pdfUrl = "F:/eclipse-workspace-new1/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/"
				+ "Glaucoma/csvReport/086_20180403230525LR.pdf";
		//下载文件路径
        System.out.println("=pdfUrl=" + pdfUrl);
        //String[] str = pdfUrl.split("/");
        
        String[] str = pdfUrl.split("/");
        System.out.println("filename=" + str[str.length - 1]);
		InputStream in = new FileInputStream(new File(pdfUrl));// 将该文件加入到输入流之中
		byte[] body = null;
		body = new byte[in.available()];// 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
		in.read(body);// 读入到输入流里面
		String fileName = new String(str[str.length - 1].getBytes("gbk"), "iso8859-1");// 防止中文乱码
		HttpHeaders headers = new HttpHeaders();// 设置响应头
		headers.add("Content-Disposition", "attachment;filename=" + fileName);
		//headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType((MediaType.parseMediaType("application/pdf")));
		HttpStatus statusCode = HttpStatus.OK;// 设置响应吗
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
		return response;
	}
	
	@SuppressWarnings("resource")
	@RequestMapping(value = "downloadPDFNew")  
	public void downloadPDFNew(String pid, String pdfUrl, HttpServletRequest request, 
			final HttpServletResponse response) throws IOException{  
		Person entity = patientService.queryById(pid);
        String[] str = entity.getLrpdf().split("/");
        FileInputStream in = new FileInputStream(new File(entity.getLrpdf()));// 将该文件加入到输入流之中
		byte[] body = new byte[in.available()]; 
	    String fileName = str[str.length - 1];  
	    fileName = URLEncoder.encode(fileName, "UTF-8");  
	    response.reset();  
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");  
	    response.addHeader("Content-Length", "" + body.length);  
	    response.setContentType("application/pdf;charset=UTF-8");  
	    OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());  
	    outputStream.write(body);  
	    outputStream.flush();  
	    outputStream.close();  
	}
	
	@RequestMapping("/searchImgResult")
	@ResponseBody
	public PageResultMsg<ImageResult> searchImgResult(int page, int limit, String param, String dateTime){
		System.out.println(param + "=" + dateTime);
		PageResultMsg<ImageResult> msg = new PageResultMsg<ImageResult>();
		Result<ImageResult> result = patientService.getResultByParam(param, dateTime, page, limit, "ImageResult");
		msg.setCode(0);
		msg.setCount(result.getCount());
		msg.setMsg(result.getMsg());
		msg.setData(result.getList());
		msg.setFail(result.getFail());
		return msg;
	}
	
	
	@RequestMapping("/searchPatient")
	@ResponseBody
	public PageResultMsg<PersonDto> searchPatient(int page, int limit, String param, String dateTime) {
		System.out.println(page + "=" + limit);
		System.out.println(param + "=" + dateTime);
		
		PageResultMsg<PersonDto> resultMsg = new PageResultMsg<PersonDto>();
		Result<PersonDto> result = patientService.saveToMap(param, dateTime, page, limit, "ImageResult");
		resultMsg.setCode(0);
		resultMsg.setCount(result.getCount());
		resultMsg.setFail(result.getFail());
		resultMsg.setMsg(result.getMsg());
		resultMsg.setData(result.getList());
		System.out.println("resultMsg=" + resultMsg);
		return resultMsg;
	}
	
	@RequestMapping("/pdf")
	public String pdfList(Model model, HttpServletRequest request) {
		return "aa";
	}
	
	public List<EyeInfo> getInfo(){
        List<EyeInfo> list = new ArrayList<EyeInfo>();
		
		Logger logger = Logger.getLogger(GlaucomaController.class);
		logger.info("开始----");
		//test
		EyeInfo info1 = new EyeInfo();
		info1.setFlag(0);
		info1.setCdr("0.666");
		info1.setCupconf("1.00");
		info1.setE_result1("../img/full_u13_p13_2018_R_CON_1.jpg");
		info1.setE_result2("../img/crop_u13_p13_2018_R_CON_1.jpg");
		info1.setE_url("../img/u13_p13_2018_R_CON_1.jpg");
		String[] str = "u13_p13_2018_L_CON_1.jpg".split("_");
		System.out.println("uid=" + str[0]);
		
		info1.setEid("U0001");
		info1.setFullconf("0.76");
		info1.setQulity(1);
		info1.setPercent1("4%");
		info1.setPercent2("5%");
		info1.setPercent3("3%");
		info1.setPercent4("90%");
		
		EyeInfo info2 = new EyeInfo();
		info2.setFlag(1);
		info2.setCdr("0.7777");
		info2.setCupconf("1.00");
		info2.setE_result1("../img/full_u13_p13_2018_L_CON_1.jpg");
		info2.setE_result2("../img/crop_u13_p13_2018_L_CON_1.jpg");
		info2.setE_url("../img/u13_p13_2018_L_CON_1.jpg");
		info2.setEid("U0001");
		info2.setFullconf("0.8989");
		info2.setQulity(0);
		info2.setPercent1("5%");
		info2.setPercent2("4%");
		info2.setPercent3("3%");
		info2.setPercent4("80%");
		
		list.add(info1);
		list.add(info2);
		System.out.println("list=" + list);
		return list;
	}
	
	@RequestMapping("/img")
	@ResponseBody
	public List<EyeInfo> dataCheck(Model model, HttpServletRequest request) {
		System.out.println("in====");	
		Logger logger = Logger.getLogger(GlaucomaController.class);
		List<EyeInfo> list = ProcessImage.getListInfo();
		logger.info("start===");
		logger.info("list=" + list);
		return list;
	}
	
	@RequestMapping("/eyeInfo")
	@ResponseBody
	public List<EyeInfo> eyeInfo(String pid){
		logger.info("获取eyeinfo详细");
		List<EyeInfo> list = patientService.queryByPid(pid);
		System.out.println(list.get(0));
		return list;
	}
	
	@RequestMapping("/watch")
	@ResponseBody
	public List<EyeInfo> stateCheck(Model model, HttpServletRequest request, String pid) {
		System.out.println("in====");	
		//Logger logger = Logger.getLogger(GlaucomaController.class);
		List<EyeInfo> list = getInfo();
		return list;
//		if(Constant.L_Queue.size() == 0) {
//			return list;
//		}else {
////			list = AutoImage.getData();
//			list = ImageGenerate.getData();
//			System.out.println("list=" + list);
//			return list;
//		}
		
		//测试
//		list = ImageGenerateTest.getData();
//		logger.info("start===");
//		System.out.println("list=" + list);
//		return list;
		
//		List<EyeInfo> list = ImageGenerate.getData();
//		logger.info("start===");
//		logger.info("list=" + list);
//		return list;
	}
	
	@RequestMapping(value = "/upload")
	@ResponseBody
	public void upload(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response, String tid) throws IOException {
		String fileName = file.getOriginalFilename();
		String ffname = file.getName();
		System.out.println(ffname);
		System.out.println(fileName);
		System.out.println("tid = " + tid);
		String name = (String) request.getSession().getAttribute("userName");
		int a = 2;
		if(name == null || "".equals(name)) {
			PrintWriter out = response.getWriter();
			out.print("false");
			out.flush();
			out.close();
		}else {
			if(a == 2) {
				PrintWriter out = response.getWriter();
				out.print(1);
				out.flush();
				out.close();
			}else {
				Calendar ca = Calendar.getInstance();
				String year = "" + ca.get(Calendar.YEAR);
				//String savePath = "D:/materialFile/" + year;
				String type = fileName.substring(fileName.lastIndexOf(".") + 1);
				String fName = System.currentTimeMillis() + "." + type;
				
				String rootPath = this.getClass().getResource("/").getPath().replaceAll("%20", "");
				rootPath = rootPath.substring(0, rootPath.indexOf("WEB-INF")) + "media/" + year;
				File ff = new File(rootPath);
				if(!ff.exists()) {
					ff.mkdir();
				}
				rootPath = rootPath + "/" + fName;
				//String path = savePath + "/" + fName;
				try {
					//方式1
//					File f = new File(rootPath);
//					BufferedInputStream in = new BufferedInputStream(file.getInputStream());
//					BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f)); 
//			        byte[] bb = new byte[1024*1024*10];// 用来存储每次读取到的字节数组  
//			        int n = 0;// 每次读取到的字节数组的长度  
//			        while ((n = in.read(bb)) != -1) {
//			            out.write(bb, 0, n);// 写入到输出流 
//			            out.flush();
//			        }
//			        out.close();// 关闭流  
//			        in.close();
			        
			        
//			        FileInputStream is = (FileInputStream) file.getInputStream();
//					FileOutputStream fos = new FileOutputStream(f);
//					byte[] by = new byte[1024*1024*10];
//					int leng = 0;
//					while((leng = is.read(by))>0){
//						fos.write(by, 0, leng);
//					}
//					fos.flush();
//					fos.close();
//					is.close();
					
					//方式2
//					File f = new File(rootPath);
//					FileOutputStream fos = new FileOutputStream(f);
//					InputStream is = file.getInputStream();
//					byte[] bts = new byte[1024*1024];
//		            //一个一个字节的读取并写入
//		            while(is.read(bts)!=-1)
//		            {
//		                fos.write(bts);
//		            }
//		           fos.flush();
//		           fos.close();
//		           is.close();
					
					//方式3
					File f = new File(rootPath);
					file.transferTo(f);
				}catch(IOException e) {
					e.printStackTrace();
				}
				if("".equals(tid) || tid.charAt(0) == 'p' || tid == null) {
					tid = "";
				}
//				Material material = setMaterialParameter(fileName, fName, name, request, tid);
//				materialService.saveFile(material);
				PrintWriter out = response.getWriter();
				out.print("true");
				out.flush();
				out.close();
			}
		}
	}
	
	@SuppressWarnings("unused")
	public void setMaterialParameter(String fileName, String fName, String name,
			HttpServletRequest request, String tid) throws IOException {
		Calendar ca = Calendar.getInstance();
		String year = "" + ca.get(Calendar.YEAR);
		//String savePath = "D:/materialFile/" + year;
		String type = fileName.substring(fileName.lastIndexOf(".") + 1);
		//String path = savePath + "/" + fName;
		String rootPa = request.getSession().getServletContext().getRealPath("");
		System.out.println("rootPa==" + rootPa);
		String rootPath = this.getClass().getResource("/").getPath().replaceAll("%20", "");
		System.out.println("root==" + rootPath);
		rootPath = rootPath.substring(0, rootPath.indexOf("WEB-INF")) + "media/" + year;
		System.out.println("rootPath=" + rootPath);
		File f = new File(rootPath);
		if(!f.exists()) {
			f.mkdir();
		}
		rootPath = rootPath + "/" + fName;
		//rootPath1 = rootPath;
		int durTime = 1;
//		BigInteger bigIntMD5 = FileUtil.getMD5(rootPath);
//		String md = bigIntMD5.toString(16);
//		//savePath = savePath + "/" + fName;
//		Material material = new Material();
//		material.setDeleted(0);
//		material.setTis("0");
//		material.setFileName(fileName);
//		material.setFilePath(rootPath);
//		String materialName = fileName.substring(0, fileName.lastIndexOf("."));
//		System.out.println(materialName);
//		material.setMaterialName(materialName);
//		material.setMd5(md);
//		material.setUploadName(name);
//		material.setStatusId("1");
//		material.setInfo("0");
//		material.setUsedNum(0);
		if(!"".equals(tid)) {
//			Terminal terminal = commonService.getTerminalById(tid.substring(1));
//			material.setTerminal(terminal);
		}
//		material.setUploadTime(new Timestamp(new Date().getTime()));
//		int typeId = judgeType(type);
//		String resolution = "1X1";
//		String size = "0kb";
//		if (typeId == 0) {
//			material.setFileType("vedio");
//			durTime = FileUtil.getDurTime(rootPath);
//			resolution = FileUtil.getResolution(rootPath);
//			size = FileUtil.getSize(rootPath);
//			material.setResolution(resolution);
//			material.setSize(size);
//			material.setDuration(durTime);
//		} else if (typeId == 1) {
//			material.setFileType("picture");
//			material.setDuration(durTime);
//			material.setResolution(resolution);
//			material.setSize(size);
//		} else {
//			material.setFileType("other");
//			material.setDuration(durTime);
//			material.setSize(size);
//			material.setResolution(resolution);
//		}
	}
	
	public int judgeType(String type){
		String str = type.toLowerCase();
		String[] vedio = new String[]{"wmv", "asf", "rm","rmvb","mov","avi","dat","mpg","mpeg","mp4","dmv","amv","3gp"
				,"mtv","mkv","mpe","m2v","vob","divx","flv","wmvhd","3g2","qt","ogg","ogv","oga","mod"};
		String[] picture = new String[]{"jpg","png","bmp","gif","psd","jpeg","ilbm","iff","tif","tiff","mng","xpm"
				,"sai","psp","ufo","xcf","pcx","ppm","webp","wdp","tga","tpic","pct","pic","pict","jp2","j2c","ima"
				,"cdr","ai"};
		for(String s:vedio){
			if(s.equals(str)){
				return 0;
			}
		}
		for(String st:picture){
			if(st.equals(str)){
				return 1;
			}
		}
		return 3;
	}
}

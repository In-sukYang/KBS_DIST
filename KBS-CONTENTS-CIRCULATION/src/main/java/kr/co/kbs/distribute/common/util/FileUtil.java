package kr.co.kbs.distribute.common.util;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import kr.co.kbs.distribute.common.vo.FileVo;

/**
 *	
 * @Author : yhkim
 * @Date   : 2017. 4. 10.
 * @클래스 설명 : File 관련 Util 
 */
/**
 * 
 * @Author : yhkim
 * @Date : 2017. 4. 10.
 */
public class FileUtil {

	public static final Logger log = LoggerFactory.getLogger(Runnable.class.getName()); 
	
	/**
	 * <pre>
	 * 1.개요 : 
	 * 2. 처리내용 : 
	 *    - Biz Logic 처리
	 *    	1)
	 *		2)
	 *		3)
	 *
	 * </pre>
	 * 
	 * @Method Name : upladFile
	 * @작성일 : 2017. 4. 10.
	 * @변경이력 : :
	 * @param req
	 * @param res
	 * @param model
	 * @throws Exception
	 */
	public static FileVo uploadFile(MultipartFile file, String savePath) throws Exception {
		FileVo fileVo = new FileVo();

		String strFileOrgName = file.getOriginalFilename();
		int idx = strFileOrgName.lastIndexOf(".");
		String _fileName = strFileOrgName.substring(idx, strFileOrgName.length());
		if ((strFileOrgName != null)
				&& ( (".csv".equals(_fileName.toLowerCase())) || (".xls".equals(_fileName.toLowerCase())) || (".xlsx".equals(_fileName.toLowerCase()))	)
				&& (!"".equals(savePath))) {
			String saveFileName = UUID.randomUUID().toString().replaceAll("-", "");
			String saveFullPathFile = savePath + "/" +saveFileName + _fileName;
			createDir(savePath);

			file.transferTo(new File(tranceCheckUpFolderPath(saveFullPathFile)));

			fileVo.setOriginFilenm(strFileOrgName);
			fileVo.setSaveFilenm(saveFileName + _fileName);
			
			return fileVo;
		} else {
			throw new Exception();
		}
	}


	/**
	 * <pre>
	 * 1.개요 : 
	 * 2. 처리내용 :
	 *    - Biz Logic 처리
	 *    	1)
	 *		2)
	 *		3)
	 *
	 * </pre>
	 * 
	 * @Method Name : createDir
	 * @작성일 : 2017. 4. 10.
	 * @변경이력 : :
	 * @param strPath
	 */
	public static void createDir(String strPath) {
		File dir = new File(tranceCheckUpFolderPath(strPath));
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
	}

	/**
	 * <pre>
	 * 1.개요 : 
	 * 2. 처리내용 :
	 *    - Biz Logic 처리
	 *    	1)
	 *		2)
	 *		3)
	 *
	 * </pre>
	 * 
	 * @Method Name : tranceCheckUpFolderPath
	 * @작성일 : 2017. 4. 10.
	 * @변경이력 : :
	 * @param strPath
	 * @return
	 */
	public static String tranceCheckUpFolderPath(String strPath) {
		String strReturnPath = strPath;
		if ((strReturnPath != null) && (!"".equals(strReturnPath))) {
			strReturnPath = StringUtil.replaceAll(strReturnPath, "..\\", "");
			strReturnPath = StringUtil.replaceAll(strReturnPath, "../", "");
		}
		return strReturnPath;
	}

	/**
	 * <pre>
	 * 1.개요 : 
	 * 2. 처리내용 :
	 *    - Biz Logic 처리
	 *    	1)
	 *		2)
	 *		3)
	 *
	 * </pre>
	 * 
	 * @Method Name : fileDelete
	 * @작성일 : 2017. 4. 10.
	 * @변경이력 : :
	 * @param req
	 * @param res
	 * @param model
	 * @param fileName
	 * @param id
	 * @throws Exception
	 */
	public static void fileDelete(String fileName , String savePath) throws Exception {
		if ((fileName != null) && (fileName.length() > 0)) {
			File f = new File(savePath+ "/" + fileName);
			if (!f.delete()) {
				throw new Exception();
			}
		} else {
			throw new Exception();
		}
	}
	
//	public static void downFile(ProjectFileVo vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		
//		String downFileName = "";
//		String orgFileName = "";
//
//		if (vo.getFDbSaveName() == null) {
//			downFileName = "";
//		} else {
//			downFileName = vo.getFDbSaveName();
//		}
//
//		if (vo.getFOriginalName()== null) {
//			orgFileName = "";
//		} else {
//			orgFileName = vo.getFOriginalName();
//		}
//
//		orgFileName = orgFileName.replaceAll("\r", "").replaceAll("\n", "");
//
//		File file = new File(tranceCheckUpFolderPath(downFileName));
//
//		if (!file.exists()) {
//			throw new Exception(downFileName);
//		}
//
//		if (!file.isFile()) {
//			throw new Exception(downFileName);
//		}
//
//		int fSize = (int) file.length();
//		
//		BufferedInputStream in = null;
//		BufferedOutputStream out = null;
//		
//		if (fSize > 0) {
//			try {
//				String mimetype = Files.probeContentType(file.toPath());
//				String encoding = getFileEncoding(downFileName);
//				
//				if(encoding != null){
//					response.setContentType(mimetype+"; charset="+encoding);
//				}else{
//					response.setContentType(mimetype);
//				}
//				
//				//response.setContentType("application/x-msdownload");
//				
//				setDisposition(orgFileName , request, response);
//				response.setHeader("Content-Transfer-Encoding", "binary");
//				response.setContentLength(fSize);
//				
//				in = new BufferedInputStream(new FileInputStream(file));
//				out = new BufferedOutputStream(response.getOutputStream());
//				
//				FileCopyUtils.copy(in, out);
//	
//			} catch (IOException ex) {
//				// 다음 Exception 무시 처리
//				// Connection reset by peer: socket write error
//				log.error("IO Exception {}", ex);
//				throw new IOException();
//			} finally {
//				ResourceCloseHelper.close(in,out);
//			}
//		}
//	}
//	
	
	/**
	 * Disposition 지정하기.
	 *
	 * @param filename
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public static void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String browser = getBrowser(request);

		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}
	
	/**
	 * 브라우저 구분 얻기.
	 *
	 * @param request
	 * @return
	 */
	public static String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
			return "Trident";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
	}
	
//	public static String getFileEncoding(String fileFullName) throws Exception{
//		byte[] buf = new byte[4096];
//		String fileName = fileFullName;
//		FileInputStream fis = new FileInputStream(fileName); 
//		UniversalDetector detector = new UniversalDetector(null); 
//		int nread; 
//		while ((nread = fis.read(buf)) > 0 && !detector.isDone()) { 
//			detector.handleData(buf, 0, nread); 
//		} 
//		detector.dataEnd(); 
//		String encoding = detector.getDetectedCharset(); 
//		if (encoding != null) { 
//			System.out.println("Detected encoding = " + encoding); 
//		} else {
//			System.out.println("No encoding detected."); 
//		} 
//		detector.reset();
//		
//		ResourceCloseHelper.close(fis);
//		
//		return encoding;
//	}
}

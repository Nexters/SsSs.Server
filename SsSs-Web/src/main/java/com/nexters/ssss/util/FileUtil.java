package com.nexters.ssss.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;

import sun.misc.BASE64Decoder;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 파일 유틸
 * @author limjuhyun
 *
 */
public class FileUtil {
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * base64 String 값을 파일로 변환하여 저장한다.
	 * @param base64File base64 String Value
	 * @param outputFolder 저장 경로
	 * @param extensionType 확장자 타입
	 * @return
	 */
	public String base64StringToFile (String base64File, String outputFolder, String extensionType) {
		BASE64Decoder decoder = new BASE64Decoder();
		FileOutputStream fos = null;
		
		try {
			File newFile = new File(outputFolder + File.separator + generateUniqueFileName()+"."+extensionType);
			byte[] decodedBytes = decoder.decodeBuffer(base64File);
			fos = new FileOutputStream(newFile);
			fos.write(decodedBytes);
			logger.debug("file binary::"+new String(decodedBytes));
			fos.close();
			
			return newFile.getPath();
		} catch (IOException ex) {
			logger.error("Exception :: ", ex);
		} finally {
			try { if(fos!=null) fos.close(); } catch (Exception e) {};
		}
		
		return "";
	}
	
	/**
	 * Unique한 파일 이름을 가져온다. 
	 * @return String
	 */
	public String generateUniqueFileName(){
	    String filename="";
	    long millis=System.currentTimeMillis();
	    String datetime=new Date().toGMTString();
	    datetime=datetime.replace(" ", "");
	    datetime=datetime.replace(":", "");
	    String rndchars=RandomStringUtils.randomAlphanumeric(16);
	    filename=rndchars+"_"+datetime+"_"+millis;
	    return filename;
	}
	
	/**
	 * 압축 파일을 해제한다.
	 * @param zipFile 압축 파일 경로
	 * @param outputFolder 압축 해제할 폴더
	 * @param isDeleteOriginFile 기존 압축 파일 삭제 유무
	 * @return 압축 해제한 파일들 경로
	 */
	public ArrayList<String> unZip(String zipFile, String outputFolder, boolean isDeleteOriginFile) {
		byte[] buffer = new byte[1024];
		ArrayList<String> listFiles = new ArrayList<String>();
		try{
			 
	    	//create output directory is not exists
	    	File folder = new File(outputFolder);
	    	if(!folder.exists()){
	    		folder.mkdir();
	    	}
	 
	    	//get the zip file content
	    	ZipInputStream zis = 
	    		new ZipInputStream(new FileInputStream(zipFile));
	    	//get the zipped file list entry
	    	ZipEntry ze = zis.getNextEntry();
	 
	    	while(ze!=null){
	 
	    	   String fileName = ze.getName();
	           File newFile = new File(outputFolder + File.separator + fileName);
	           listFiles.add(newFile.getName());
	           
	           logger.debug("file unzip : "+ newFile.getName());
	 
	            //create all non exists folders
	            //else you will hit FileNotFoundException for compressed folder
	            new File(newFile.getParent()).mkdirs();
	 
	            FileOutputStream fos = new FileOutputStream(newFile);             
	 
	            int len;
	            while ((len = zis.read(buffer)) > 0) {
	       		fos.write(buffer, 0, len);
	            }
	            
	            fos.close();   
	            ze = zis.getNextEntry();
	    	}
	    	
	    	
	        zis.closeEntry();
	    	zis.close();
	    	
	    	if(isDeleteOriginFile==true){
	    		boolean isDelete = deleteFile(zipFile);
		    	logger.debug("압축 해제 후 파일 삭제 유무 :: "+isDelete);
	    	}
	    	
	    }catch(IOException ex){
	    	logger.error("Exception :: ", ex);
	    }
		
		return listFiles;
	}
	
	/**
	 * 압축 파일 해제 (기존 파일 무조건 삭제)
	 * @param zipFile
	 * @param outputFolder
	 * @return 압축 해제한 파일들 경로
	 */
	public ArrayList<String> unZip(String zipFile, String outputFolder) {
		return unZip(zipFile, outputFolder, true);
	}

		
	/**
	 * 파일 삭제
	 * @param fileLocation 삭제할 파일 경
	 * @return 삭제 유무(T/F)
	 */
	public boolean deleteFile (String fileLocation) {
		try {
			// 압축 해제가 끝나면 압축 파일을 삭제한다.
	    	File tempFile = new File(fileLocation);
	    	
	    	if(tempFile.exists()) {
	    		if(tempFile.delete())
	    			return true;
	    		else
	    			return false;
	    	} else {
	    		logger.debug("파일 삭제", "파일이 존재하지 않습니다.");
	    		return false;
	    	}
		} catch(Exception e) {
			logger.error("파일 삭제 중 오류", e);
			return false;
		}
		
	}
}
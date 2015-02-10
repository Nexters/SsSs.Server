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

public class FileUtil {
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	public String base64StringToFile (String base64File, String outputFolder, String extensionType) {
		BASE64Decoder decoder = new BASE64Decoder();
		FileOutputStream fos = null;
		
		try {
			File newFile = new File(outputFolder + File.separator + generateUniqueFileName()+"."+extensionType);
			byte[] decodedBytes = decoder.decodeBuffer(base64File);
			fos = new FileOutputStream(newFile);
			fos.write(decodedBytes);
			fos.close();
			
			return newFile.getPath();
		} catch (IOException ex) {
			logger.error("Exception :: ", ex);
		} finally {
			try { if(fos!=null) fos.close(); } catch (Exception e) {};
		}
		
		return "";
		
	}
	
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
	
	public ArrayList unZip(String zipFile, String outputFolder) {
		byte[] buffer = new byte[1024];
		ArrayList listFiles = new ArrayList();
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
	           listFiles.add(newFile.getAbsoluteFile());
	           
	           logger.debug("file unzip : "+ newFile.getAbsoluteFile());
	 
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
	    }catch(IOException ex){
	    	logger.error("Exception :: ", ex);
	    }
		
		return listFiles;
	}
	
}
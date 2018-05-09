package cn.mt.hzh.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


import cn.mt.hzh.util.logging.SimpleLog;

public class FileUtils {


	
	public static boolean printContentToFile(String filePath,StringBuilder content){
		try {
			boolean append=false;
			if(FileUtils.fileEixsts(filePath)){
				append=true;
			}
			BufferedWriter	writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath,append)));
			writer.append(content.toString());
			writer.flush();
			writer.close();
			return true;
		} catch (IOException e) {
			SimpleLog.formatLog(e.getMessage());
		}
		return false;
	}
	/**
	 * @param ·��
	 * pathֵ��null�򲻴��ڴ��ļ�����false ���� true
	 * */
     public static boolean fileEixsts(String path){
    	 if(path!=null&&new File(path).exists()){
    		 return true;
    	 }else{
    		 return false;
    	 }
     }
     /**
      * @param path �ļ�·��
      * @return null�������ڴ��ļ���path=null   
      * */
     public static File createFile(String path){
    	 if(fileEixsts(path)){
    		 return new File(path);
    	 }else{
    		 return null;
    	 }
     }
     /**
      * @param path �ļ�·��
      * @param exists �Ƿ����ж��ļ��Ƿ�Ϊ�� Ĭ��false
      * */
     public static File createFile(String path,boolean exists){
    	 if(!exists){
    		 if(fileEixsts(path)){
        		 return new File(path);
        	 }else{
        		 return null;
        	 }
    	 }else{
    		 return new File(path);
    	 }
    	
     }
}

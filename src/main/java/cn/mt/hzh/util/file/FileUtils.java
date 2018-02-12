package cn.mt.hzh.util.file;

import java.io.File;

public class FileUtils {

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

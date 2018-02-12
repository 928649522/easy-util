package cn.mt.hzh.util.file;

import java.io.File;

public class FileUtils {

	/**
	 * @param 路径
	 * path值若null或不存在此文件返回false 否则 true
	 * */
     public static boolean fileEixsts(String path){
    	 if(path!=null&&new File(path).exists()){
    		 return true;
    	 }else{
    		 return false;
    	 }
     }
     /**
      * @param path 文件路径
      * @return null：不存在此文件或path=null   
      * */
     public static File createFile(String path){
    	 if(fileEixsts(path)){
    		 return new File(path);
    	 }else{
    		 return null;
    	 }
     }
     /**
      * @param path 文件路径
      * @param exists 是否开启判断文件是否为空 默认false
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

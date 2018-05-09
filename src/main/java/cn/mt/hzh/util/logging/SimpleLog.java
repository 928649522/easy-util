package cn.mt.hzh.util.logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleLog {
	
	/**
	 * @param msg log��Ϣ
	 * @param pattern  log�ļ���ӡ���ɵ�·�� xxx.log
	 * format�����۵Ŀ���log
	 * log Level is All
	 * */
	public static void formatLog(String msg,String pattern) throws IOException{  
        Logger log = Logger.getLogger("tesglog");  
        log.setLevel(Level.ALL);  
        FileHandler fileHandler = new FileHandler(pattern);  
        fileHandler.setLevel(Level.ALL);  
        fileHandler.setFormatter(new LogFormatter());  
        log.addHandler(fileHandler);  
        log.info("-------"+msg+"----------");
    }
	/**
	 * @param msg log��Ϣ
	 * log Level is All
	 * */
	public static void formatLog(String msg){  
        Logger log = Logger.getLogger("tesglog");  
        log.setLevel(Level.ALL);  
        log.info("-------"+msg+"----------");
    }  
	/**
	 * @param msg log��Ϣ
	 * log Level is All
	 * */
	public static void log(String msg) {  
        Logger log = Logger.getLogger("tesglos");  
        log.setLevel(Level.ALL);  
        log.info(msg); 
    }
}


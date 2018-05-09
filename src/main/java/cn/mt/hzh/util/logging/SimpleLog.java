package cn.mt.hzh.util.logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleLog {
	
	/**
	 * @param msg log消息
	 * @param pattern  log文件打印生成的路径 xxx.log
	 * format更显眼的看到log
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
	 * @param msg log消息
	 * log Level is All
	 * */
	public static void formatLog(String msg){  
        Logger log = Logger.getLogger("tesglog");  
        log.setLevel(Level.ALL);  
        log.info("-------"+msg+"----------");
    }  
	/**
	 * @param msg log消息
	 * log Level is All
	 * */
	public static void log(String msg) {  
        Logger log = Logger.getLogger("tesglos");  
        log.setLevel(Level.ALL);  
        log.info(msg); 
    }
}


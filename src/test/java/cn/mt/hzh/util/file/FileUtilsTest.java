package cn.mt.hzh.util.file;

import static org.junit.Assert.*;

import org.junit.Test;

public class FileUtilsTest {

	@Test
	public void testPrintContentToFile() {
		StringBuilder sbu=new StringBuilder("huang ");
		sbu.append(" ze");
	
		for(int i=0;i<10;i++){
			FileUtils.printContentToFile("e:hzh.txt", sbu);
		}
		
	}

}

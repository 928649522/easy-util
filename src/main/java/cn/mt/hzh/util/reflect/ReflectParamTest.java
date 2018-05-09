package cn.mt.hzh.util.reflect;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReflectParamTest {
	 public static void main(String[] args) throws ParseException {
		   Teacher teacher =new Teacher();
		   SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		   
		   Object value=format.parse("1997-10-29");
		   try {
			   String s="d";
			teacher=(Teacher) SetPropertiesUtil.reflectSetValue(teacher, "cha", s);
		 System.out.println(teacher.toString());
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

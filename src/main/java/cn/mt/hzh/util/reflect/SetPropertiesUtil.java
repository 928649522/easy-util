package cn.mt.hzh.util.reflect;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.mt.hzh.util.logging.SimpleLog;

public class SetPropertiesUtil {
	/**
	 * @param source 原来的参数类型
	 * @param target 所要接收值的目标对象
	 * 将String
	 * 只考虑了target为String的情况
	 * */
	private static Object Convert(String source,Object target){
		if(!(target instanceof String)){
			SimpleLog.log(SetPropertiesUtil.class.getName()
					+":不支持转化String以外的类型");
			SimpleLog.log(SetPropertiesUtil.class.getName()
					+":请检查target类型是否为String");
			return null;
		}
		 if("class java.lang.Integer".equals(source)){
			return Integer.valueOf( target.toString());
		}else if("class java.lang.Long".equals(source)){
			return Long.valueOf( target.toString());
		}else if("class java.lang.String".equals(source)){
			return String.valueOf(target);
		}else if("class java.lang.Char".equals(source)){
			System.out.println("-------------------schar");
			return "s";
		}else if("class java.util.Date".equals(source)){
			String formatStr="yyyy-MM-dd";
			SimpleDateFormat format;
			if(target.toString().contains(":")){
				formatStr="yyyy-MM-dd HH:mm:ss";
			}
			 format=new SimpleDateFormat(formatStr);
			try {
				System.out.println(format.parse(target.toString()));
				return format.parse(target.toString());
			} catch (ParseException e) {
				SimpleLog.log(SetPropertiesUtil.class.getName()
						+":Date格式不支持");
			}
			
		}else {
		   return null;
		}
		return target;
	}
	
		/**
		 * 
		 * @param target 所要接收值的目标对象
		 * 
		 * @param atrributeName 参数名
		 * @param Object 参数对应值
		 * 通过一个对象 和这个对象里的参数 为这个参数传值
		 * 只考虑了target为String的情况
		 * */
        public static Object reflectSetValue(Object target,
        		String atrributeName,Object value)throws Exception{
        	  @SuppressWarnings("rawtypes")
			Class cls=target.getClass();
        	  for(Field field:cls.getDeclaredFields()){
        		  if(field.getName().equals(atrributeName)){
        			  field=cls.getDeclaredField(atrributeName);
        			  System.out.println(field.getGenericType().toString());
        			  value=Convert(field.getGenericType().toString(),value);
        			  field.setAccessible(true);
        			  field.set(target, value);
        		  }
             }
        	  return target;
        }
}

class Teacher{
	  private String name ;
	  private Integer age;
	  private Date date;
	  private char cha;
	  
	public char getCha() {
		return cha;
	}
	public void setCha(char cha) {
		this.cha = cha;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Teacher [name=" + name + ", age=" + age + ", date=" + date + "]";
	}
	
	  
}

package cn.mt.hzh.util.judge.collection;

import java.util.Collection;

public class JudgeOneOrMore {
	/**
	 * @param target 需要判断的对象
	 * @return true：代表为集合  
	 *         false：集合以外的对象
	 * */
	public static boolean  isCollection(Object target){
		if(target instanceof Collection){
			return true;
		}else{
			return false;
		}
	}
}

package cn.mt.hzh.util.judge.collection;

import java.util.Collection;

public class JudgeOneOrMore {
	/**
	 * @param target ��Ҫ�жϵĶ���
	 * @return true������Ϊ����  
	 *         false����������Ķ���
	 * */
	public static boolean  isCollection(Object target){
		if(target instanceof Collection){
			return true;
		}else{
			return false;
		}
	}
}

package com;

import java.util.HashMap;
import java.util.Map;

//Map中key值不可重复的测试
public class TestMapKeyEquals {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String s1=new String("abc");
        String s2=new String("abc");
        
        Map map=new HashMap();
        map.put(s1, "abc123");
        map.put(s2, "ABC456");//第二个会覆盖第一个元素
        //注意：map中key值不可重复，直接根据比较的是equals,只有equals相同则覆盖
        System.out.println(map.size());
        System.out.println(map.get(s1));
	}

}

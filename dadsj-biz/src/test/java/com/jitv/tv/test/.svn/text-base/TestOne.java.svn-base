package com.jitv.tv.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Test;

/**
 * @author xiaominghui@9ikandian.com
 * @date 2018-3-9 上午10:33:22
 * @describe
 */
public class TestOne {
	

	public static String reverse(String originStr) {
		if (originStr == null || originStr.length() < 2) {
			return originStr;
		} else {
			return reverse(originStr.substring(1)) + originStr.charAt(0);
		}
	}

	@Test
	public void test4() {
		System.out.println(reverse("123456fgh"));
	}

	@Test
	public void test1() {
		String s1 = new StringBuilder("go").append("od").toString();
		System.out.println(s1.intern() == s1);
		String s2 = new StringBuilder("ja").append("va").toString();
		System.out.println(s2.intern() == s2);
	}

	@Test
	public void test2() {
		String s1 = "Programming";
		String s2 = new String("Programming");
		String s3 = "Program";
		String s4 = "ming";
		String s5 = "Program" + "ming";
		String s6 = s3 + s4;
		System.out.println(s1 == s2);
		System.out.println(s1 == s5);
		System.out.println(s1 == s6);
		System.out.println(s1 == s6.intern());
		System.out.println(s2 == s2.intern());
	}

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T clone(T obj) throws Exception {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bout);
		oos.writeObject(obj);

		ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bin);
		return (T) ois.readObject();

		// 说明：调用ByteArrayInputStream或ByteArrayOutputStream对象的close方法没有任何意义
		// 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外部资源（如文件流）的释放
	}

}

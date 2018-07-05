package com.exam.online.util;

import java.security.MessageDigest;


public class DataUtil {
	/**
	 * 采用MD5加密
	 */
	public static String md5(String src) {
		try {
			StringBuffer buffer = new StringBuffer();
			// 首先初始化一个字符数组，用来存放每个16进制字符
			char[] chars = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
			// 拿到一个MD5转换器
			MessageDigest md = MessageDigest.getInstance("MD5");
			 // 输入的字符串转换成字节数组，转换并返回结果，也是字节数组，包含16个元素
			byte[] data = md.digest(src.getBytes());
			// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
			for (byte b : data) {
				//高四位
				buffer.append(chars[(b >> 4) & 0x0F]);
				//低四位
				buffer.append(chars[b & 0x0F]);
			}
			 // 字符数组组合成字符串返回
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

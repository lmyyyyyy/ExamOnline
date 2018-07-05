package com.exam.online.util;

public class SplitUtil {
	public static String[] splitString(String str) {
		String[] arr = str.split("_");
		for (int i=0; i<arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
		return arr;
	}
	
	public static String splitArr(String[] arr) {
		if (arr == null || "".equals(arr)) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<arr.length; i++) {
			sb.append(arr[i]);
		}
		String str = sb.toString();
		return str;
	}
	
	public static void main(String[] args) {
		//SplitUtil.splitString("A_A_A_A_B_B_B_A_D_A_B_!_!_!_!_!_A_A_A_C_");
		String[] arr = {"A","B","C","D"};
		System.out.println(SplitUtil.splitArr(arr));
	}
}

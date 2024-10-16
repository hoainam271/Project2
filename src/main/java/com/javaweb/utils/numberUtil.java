package com.javaweb.utils;

public class numberUtil {
	public static boolean checkNumber(String value){
		try {
			Long number = Long.parseLong(value);
		}
		catch(NumberFormatException e){
			return false;
		}
		return true;
	}
}

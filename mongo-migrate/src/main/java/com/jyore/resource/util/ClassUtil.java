package com.jyore.resource.util;

public class ClassUtil {

	
	public static boolean isPresent(String name) {
		try {
			Thread.currentThread().getContextClassLoader().loadClass(name);
			return true;
		} catch (Throwable t) {
			return false;
		}
	}
}

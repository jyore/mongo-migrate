package com.jyore.resource.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class UrlUtil {

	
	public static String toFilePath(URL url) throws UnsupportedEncodingException {
		String filePath = new File(URLDecoder.decode(url.getPath().replace("+", "%2b"), "UTF-8")).getAbsolutePath();
		if(filePath.endsWith("/")) {
			return filePath.substring(0,filePath.length()-1);
		}
		return filePath;
	}
}

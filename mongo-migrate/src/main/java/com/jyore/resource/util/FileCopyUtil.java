package com.jyore.resource.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class FileCopyUtil {

	public static String copyToString(Reader in) throws IOException {
		StringWriter out = new StringWriter();
		copy(in,out);
		String str = out.toString();
		
		if(str.startsWith("\ufeff")) {
			return str.substring(1);
		}
		
		return str;
	}
	
	
	public static void copy(Reader in, Writer out) throws IOException {
		try {
			char[] buffer = new char[4096];
			int bytesRead;
			while((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer,0,bytesRead);
			}
			
			out.flush();
		} finally {
			try {
				in.close();
			} catch(IOException e) {
				//noop
			}
			
			try {
				out.close();
			} catch(IOException e) {
				//noop
			}
		}
	}
}

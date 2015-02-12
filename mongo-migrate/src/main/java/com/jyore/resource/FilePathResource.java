package com.jyore.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import com.jyore.resource.util.FileCopyUtil;

public class FilePathResource implements Resource, Comparable<Resource> {

	private final File file;
	
	public FilePathResource(File file) {
		this.file = file;
	}
	
	public String load(String encoding) throws IOException {
		InputStream is = new FileInputStream(file);
		Reader reader = new InputStreamReader(is,Charset.forName(encoding));
		return FileCopyUtil.copyToString(reader);
	}

	public String getLocation() {
		return file.getName();
	}
	
	@Override
	public int hashCode() {
		return file.hashCode();
	}
	
	public int compareTo(Resource r) {
		String s1 = getLocation();
		String s2 = r.getLocation();
		
		if(s1.contains("/") && s2.contains("/")) {
			s1 = s1.substring(s1.lastIndexOf("/") + 1);
			s2 = s2.substring(s2.lastIndexOf("/") + 1);
		}
		
		return s1.compareTo(s2);
	}

}

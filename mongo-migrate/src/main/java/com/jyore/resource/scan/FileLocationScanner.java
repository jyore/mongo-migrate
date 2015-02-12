package com.jyore.resource.scan;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import com.jyore.resource.FilePathResource;
import com.jyore.resource.Resource;

public class FileLocationScanner implements LocationScanner {

	public Collection<Resource> findResources(String location, URL url) throws IOException {
		return loadFiles(new File(location));
	}

	private Collection<Resource> loadFiles(File directory) throws IOException {
		Set<Resource> resources = new TreeSet<Resource>();
		if(!directory.isDirectory()) {
			throw new IOException("Location is not a directory " + directory.getName());
		}
		
		for(File file : directory.listFiles()) {
			if(file.isDirectory()) {
				resources.addAll(loadFiles(file));
			} else {
				resources.add(new FilePathResource(file));
			}
		}
		
		return resources;
	}

}

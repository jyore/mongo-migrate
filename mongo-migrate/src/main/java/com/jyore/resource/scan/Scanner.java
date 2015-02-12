package com.jyore.resource.scan;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jyore.resource.Resource;
import com.jyore.resource.exception.ResourceLoadException;
import com.jyore.resource.util.ClassUtil;

public class Scanner {

	private static final Logger log = LoggerFactory.getLogger(Scanner.class);
	
	public static Collection<Resource> findResources(String location) throws ResourceLoadException {
		log.info("Finding resources for location {}",location);
		try {
			return new FileLocationScanner().findResources(location, null);
		} catch(IOException|NullPointerException e) {
			throw new ResourceLoadException("Unable to scan location: " + location, e);
		}
	}
	
	public static Collection<Resource> findResources(String location, URL url) throws ResourceLoadException {
		log.info("Finding resources for location {} and url {}",location,url.toString());
		try {
			return getScanner(url.getProtocol()).findResources(location,url);
		} catch(IOException|NullPointerException e) {
			throw new ResourceLoadException("Unable to scan location: " + location, e);
		}
	}

	private static LocationScanner getScanner(String protocol) {
		
		if(protocol.equals("file")) {
			return new FileLocationScanner();
		}
		
		if(protocol.equals("jar") || protocol.equals("zip") || protocol.equals("wsjar")) {
			return new JarFileLocationScanner();
		}
		
		if(ClassUtil.isPresent("org.jboss.vfs.VFS") && protocol.equals("vfs")) {
			return new JBossVFSv3LocationScanner();
		}
		
		if(ClassUtil.isPresent("org.osgi.framework.Bundle") && (protocol.equals("bundle") || protocol.equals("bundleresource"))) {
			return new OsgiLocationScanner();
		}
		
		return null;
	}
	
	
}

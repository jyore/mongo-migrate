package com.jyore.resource.scan;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

import com.jyore.resource.Resource;
import com.jyore.resource.exception.ResourceLoadException;
import com.jyore.resource.util.ClassUtil;

public class Scanner {

	public static Collection<Resource> findResources(String location, URL url) throws ResourceLoadException {
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

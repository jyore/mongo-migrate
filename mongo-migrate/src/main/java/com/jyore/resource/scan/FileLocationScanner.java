package com.jyore.resource.scan;

import java.net.URL;
import java.util.Collection;

import com.jyore.resource.Resource;

public class FileLocationScanner implements LocationScanner {

	public Collection<Resource> findResources(String location, URL url) {
		return null;
		/*
		 LOG.debug("Scanning for filesystem resources at '" + path + "' (Prefix: '" + prefix + "', Suffix: '" + suffix + "')");

        if (!new File(path).isDirectory()) {
            throw new FlywayException("Invalid filesystem path: " + path);
        }

        Set<Resource> resources = new TreeSet<Resource>();

        Set<String> resourceNames = findResourceNames(path, prefix, suffix);
        for (String resourceName : resourceNames) {
            resources.add(new FileSystemResource(resourceName));
            LOG.debug("Found filesystem resource: " + resourceName);
        }

        return resources.toArray(new Resource[resources.size()]);
		 */
		/*
		if(!new File(location).isDirectory()) {
			//exception
		}
		
		Set<Resource> resources = new TreeSet<Resource>();
		
		
		return resources;*/
	}

}

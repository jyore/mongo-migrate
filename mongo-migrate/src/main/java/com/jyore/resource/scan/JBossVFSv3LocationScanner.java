package com.jyore.resource.scan;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import org.jboss.vfs.VFS;
import org.jboss.vfs.VirtualFile;
import org.jboss.vfs.VirtualFileFilter;

import com.jyore.resource.ClassPathResource;
import com.jyore.resource.Resource;
import com.jyore.resource.util.UrlUtil;

public class JBossVFSv3LocationScanner implements LocationScanner {

	public Collection<Resource> findResources(String location, URL url) throws IOException {
		String filePath = UrlUtil.toFilePath(url);
		String cp = filePath.substring(0,filePath.length() - location.length());
		
		if(!cp.endsWith("/")) {
			cp += "/";
		}
		
		Collection<Resource> resources = new TreeSet<Resource>();
		
		List<VirtualFile> files = VFS.getChild(filePath).getChildrenRecursively(new VirtualFileFilter() {
			public boolean accepts(VirtualFile file) {
				return file.isFile();
			}
		});
		
		for(VirtualFile file : files) {
			resources.add(new ClassPathResource(file.getPathName().substring(cp.length())));
		}
		
		return resources;
	}

}

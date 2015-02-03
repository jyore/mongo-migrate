package com.jyore.resource.scan;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

import com.jyore.resource.Resource;


public interface LocationScanner {
	public Collection<Resource> findResources(String location, URL url) throws IOException;
}

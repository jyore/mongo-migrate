package com.jyore.resource;

import java.io.IOException;

public interface Resource {
	String load(String encoding) throws IOException;
	String getLocation();
}

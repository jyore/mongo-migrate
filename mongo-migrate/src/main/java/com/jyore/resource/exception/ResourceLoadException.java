package com.jyore.resource.exception;

public class ResourceLoadException extends Exception {

	private static final long serialVersionUID = 5118609019807963080L;

	public ResourceLoadException() {
		super();
	}
	
	public ResourceLoadException(String message) {
		super(message);
	}
	
	public ResourceLoadException(Throwable t) {
		super(t);
	}
	
	public ResourceLoadException(String message,Throwable t) {
		super(message,t);
	}
}

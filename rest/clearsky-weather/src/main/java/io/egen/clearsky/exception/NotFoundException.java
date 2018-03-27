package io.egen.clearsky.exception;

public class NotFoundException extends RuntimeException{
	
	public NotFoundException(String msg){
		super(msg);
	}
	public NotFoundException(String msg, Throwable cause){
		super(msg, cause);
	}
}

package com.blogger.exceptions;

//this class used for handling userService Exception
public class UserServiceException extends RuntimeException {
	private static final long serialVersionUID = 8453020984608283074L;
	
	public UserServiceException(String message) 
	{
		super(message);
	}
}

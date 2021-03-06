package com.blogger.security;

import com.blogger.SpringApplicationContext;

public class SecurityConstants {
	//Token expiration time 
	public static final long EXPIRATION_TIME = 864000000; //10days
	public static final String TOKEN_PREFIX  = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users";

	public static String getTokenSecret()
	{
		//access the bean properties
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
		return appProperties.getTokenSecret();
	}
	
}

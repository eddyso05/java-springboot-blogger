package com.blogger.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

//Components that read the property file
@Component
public class AppProperties {
	@Autowired
	private Environment env;
	
	public String getTokenSecret()
	{
		//read the token sercret from application properties file
		return env.getProperty("tokenSecret");
	}
	
}

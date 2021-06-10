package com.blogger.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blogger.SpringApplicationContext;
import com.blogger.service.UserService;
import com.blogger.shared.dto.UserDto;
import com.blogger.ui.model.request.UserLoginRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	//this class will uses authenticationManager and UsernamePasswordAuthenticationFilter from spring security
	
	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	//when client passed the username and password, it will trigger this method for get authentication token
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {

		try {
			//UserLoginRequestModel is our class that we have created to payload json which container username and password
			UserLoginRequestModel creds = new ObjectMapper()
					.readValue(req.getInputStream(),
					UserLoginRequestModel.class);
			
			//authenticate user email and password 
			//authenticationManager will lookup database and check 
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							creds.getEmail(), 
							creds.getPassword(), 
							new ArrayList<>()));
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	//when username and password have match, it will apply this method
	protected void successfulAuthentication(
			HttpServletRequest req,
			HttpServletResponse res,
			FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		
		String userName = ((User) auth.getPrincipal()).getUsername();
		
		//use JSON web token a library 
		String token = Jwts.builder()
					.setSubject(userName)
					.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
					.signWith(SignatureAlgorithm.HS512,SecurityConstants.getTokenSecret())
					.compact();
		
		//the name of the beans is userServiceImpl with LOWERCASE
		//userServiceImpl created by spring framework, the name of the bean need to start with LOWERCASE
		UserService userService = (UserService)SpringApplicationContext.getBean("userServiceImpl");
		//call UserService method from here
		UserDto userDto = userService.getUser(userName);
		
		//will add token to header, for authorize user browser header
		res.addHeader(SecurityConstants.HEADER_STRING,SecurityConstants.TOKEN_PREFIX + token);
		res.addHeader("UserId", userDto.getUserId());
	}
}

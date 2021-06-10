package com.blogger.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.blogger.shared.dto.UserDto;

//this interface will contain  many information (all user details)
public interface UserService extends UserDetailsService{
	UserDto createUser(UserDto user);
	UserDto getUser(String email);
	UserDto getUserByUserId(String userId);
	UserDto updateUser(String id,UserDto user);
	void deleteUser(String userId);
	List<UserDto> getUsers(int page, int limit);
}

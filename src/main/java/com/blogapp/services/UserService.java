package com.blogapp.services;

import java.util.List;

import com.blogapp.entities.User;
import com.blogapp.payloads.UserDto;

public interface UserService {
	
	public UserDto createUser(UserDto user);
	public UserDto updateUser(UserDto user, Integer userId);
	public UserDto getUserById(Integer userId);
	public List<UserDto> getUsers();
	void deleteUser(Integer userId);
	
}

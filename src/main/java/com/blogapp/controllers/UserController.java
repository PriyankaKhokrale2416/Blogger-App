package com.blogapp.controllers;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.payloads.UserDto;
import com.blogapp.services.UserService;




@RestController
@RequestMapping("api/users")
public class UserController {
	
	
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
	{
		UserDto createdUserDto = this.userService.createUser(userDto);
		
		logger.info("Inside create user: "+createdUserDto);
		
		return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
		
	}
	
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer uid)
	{
		UserDto updatedUser = this.userService.updateUser(userDto, uid);
		
		
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer uid)
	{
		this.userService.deleteUser(uid);
		
		
		return new ResponseEntity<>(Map.of("message", "user deleted successfully"), HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getUsers()
	{
		List<UserDto> users = this.userService.getUsers();
		return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer uid)
	{
		UserDto user = this.userService.getUserById(uid);
		return new ResponseEntity<UserDto>(user, HttpStatus.OK);
		
	}



}

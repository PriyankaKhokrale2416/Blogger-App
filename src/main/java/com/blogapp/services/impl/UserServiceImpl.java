package com.blogapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.payloads.UserDto;
import com.blogapp.repositories.UserRepo;
import com.blogapp.services.UserService;
import com.blogapp.entities.User;
import com.blogapp.exceptions.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = this.userDtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToUserDto(savedUser);
		
		return userDto1;
			
		
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = userRepo.save(user);
		
		return this.userToUserDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		
		User user = this.userRepo.findById(userId)
				 .orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		return this.userToUserDto(user);
	}

	@Override
	public List<UserDto> getUsers() {
		// TODO Auto-generated method stub
		
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtoList = users.stream().map(e->this.userToUserDto(e)).collect(Collectors.toList());
		return userDtoList;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		
		this.userRepo.delete(user);
	}
	
	public User userDtoToUser(UserDto userDto)
	{
		User user = this.modelMapper.map(userDto, User.class);
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;		
	}
	
	public UserDto userToUserDto(User user)
	{
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		return userDto;		
	}

}

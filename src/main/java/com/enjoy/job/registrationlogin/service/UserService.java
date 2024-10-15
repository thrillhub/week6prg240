package com.enjoy.job.registrationlogin.service;

import java.util.List;

import com.enjoy.job.registrationlogin.dto.UserDto;
import com.enjoy.job.registrationlogin.entity.User;


public interface UserService {
    void saveUser(UserDto userDto);  
    User findUserByEmail(String email);
    User findUserById(Long id); 
    List<UserDto> findAllUsers();  
    void updateUser(Long userId, UserDto userDto);
    void deleteUser(Long id);
}

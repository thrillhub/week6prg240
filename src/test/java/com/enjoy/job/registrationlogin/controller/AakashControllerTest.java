package com.enjoy.job.registrationlogin.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.enjoy.job.registrationlogin.dto.UserDto;
import com.enjoy.job.registrationlogin.entity.User;
import com.enjoy.job.registrationlogin.service.UserService;

public class AakashControllerTest {
    @InjectMocks
    private AakashController userController;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setName("Aakash Yadav");
        user.setEmail("aakash.yadav@gmail.com");
    }

    @Test
    public void testEditUser_UserNotFound() {
        when(userService.findUserById(1L)).thenReturn(null);

        String viewName = userController.editUser(1L, model);

        verify(model).addAttribute("errorMessage", "User not found");
        assertEquals("error", viewName);
    }

    @Test
    public void testUpdateUser_Success() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Aakash");
        userDto.setLastName("Yadav");
        userDto.setEmail("aakash.yadav@gmail.com");

        doNothing().when(userService).updateUser(1L, userDto);
        
        String viewName = userController.updateUser(1L, userDto, model);

        verify(model).addAttribute("successMessage", "User updated successfully!");
        assertEquals("redirects:/users", viewName);
    }

    @Test
    public void testUpdateUser_Failure() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Aakash");
        userDto.setLastName("Yadav");
        userDto.setEmail("aakash.yadav@gmail.com");

        doThrow(new RuntimeException("Update failed")).when(userService).updateUser(1L, userDto);
        
        String viewName = userController.updateUser(1L, userDto, model);

        verify(model).addAttribute("errorMessage", "Failed to update user: Update failed");
        assertEquals("users/editUser", viewName);
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userService).deleteUser(1L);
        String viewName = userController.deleteUser(1L);
        verify(userService).deleteUser(1L);
        assertEquals("redirect:/users", viewName);
    }
}
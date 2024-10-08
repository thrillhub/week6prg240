package com.enjoy.job.registrationlogin.service;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.enjoy.job.registrationlogin.entity.User;
import com.enjoy.job.registrationlogin.repository.UserRepository;

public class UserServiceImplUnitTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userMainService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); 
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;
        User mockUser = new User();
        mockUser.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        userMainService.deleteUser(userId);
        verify(userRepository).deleteById(userId);
    }
}

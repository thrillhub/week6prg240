package com.enjoy.job.registrationlogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enjoy.job.registrationlogin.dto.UserDto;
import com.enjoy.job.registrationlogin.entity.User;
import com.enjoy.job.registrationlogin.service.UserService;

@Controller
@RequestMapping("/user")
public class AakashController {

    private final UserService userService;

    public AakashController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        if (user == null) {
            model.addAttribute("errorMessage", "User not found");
            return "error"; 
        }
        UserDto userDto = new UserDto();
        String[] name = user.getName().split(" ");
        userDto.setFirstName(name[0]);
        userDto.setLastName(name.length > 1 ? String.join(" ", java.util.Arrays.copyOfRange(name, 1, name.length)) : "");
        userDto.setEmail(user.getEmail());
        model.addAttribute("user", userDto);
        model.addAttribute("userId", id);
        return "users/editUser"; 
    }
    @PostMapping("/update/{id}")
public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") UserDto userDto, Model model) {
    try {
        userService.updateUser(id, userDto);
        model.addAttribute("successMessage", "User updated successfully!");
        return "redirects:/users";  
    } catch (Exception e) {
        model.addAttribute("errorMessage", "Failed to update user: " + e.getMessage());
        return "users/editUser";
    }
}
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}

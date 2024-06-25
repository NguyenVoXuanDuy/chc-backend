package com.xuanduy.chc_backend.controller;


import com.xuanduy.chc_backend.dto.request.UserUpdateRequest;
import com.xuanduy.chc_backend.dto.response.ApiResponse;
import com.xuanduy.chc_backend.entity.User;
import com.xuanduy.chc_backend.repository.UserRepository;
import com.xuanduy.chc_backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    

    @GetMapping("")
    ApiResponse<List<User>> getAllUsers() {
        return ApiResponse.<List<User>>builder()
                .message("All users")
                .result(userService.getAllUsers())
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<User> getUserById(@PathVariable("userId") String userId) {
        return ApiResponse.<User>builder()
                .message("UserResponse")
                .result(userService.getUserById(userId))
                .build();
    }

    @PutMapping("/{userId}")
    ApiResponse<User> updateUser(@PathVariable("userId") String userId, @RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        return ApiResponse.<User>builder()
                .message("UserResponse updated")
                .result(userService.updateUser(userId, userUpdateRequest))
                .build();
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(@PathVariable("userId") String userId) {
        userRepository.deleteById(userId);
        return ApiResponse.<String>builder()
                .message("UserResponse deleted")
                .build();
    }

    @DeleteMapping("")
    String deleteAllUsers() {
        userRepository.deleteAll();
        return "All users deleted";
    }
}

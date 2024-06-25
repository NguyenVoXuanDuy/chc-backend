package com.xuanduy.chc_backend.service;

import com.xuanduy.chc_backend.dto.request.UserCreationRequest;
import com.xuanduy.chc_backend.dto.request.UserUpdateRequest;
import com.xuanduy.chc_backend.entity.User;

import java.util.List;


public interface UserService {
    User createUser(UserCreationRequest request);

    List<User> getAllUsers();

    User getUserById(String id);

    User updateUser(String userId, UserUpdateRequest userUpdateRequest);

    void deleteUser(String userId);
}

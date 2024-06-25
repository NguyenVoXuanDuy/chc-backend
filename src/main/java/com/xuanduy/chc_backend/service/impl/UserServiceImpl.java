package com.xuanduy.chc_backend.service.impl;

import com.xuanduy.chc_backend.dto.request.UserCreationRequest;
import com.xuanduy.chc_backend.dto.request.UserUpdateRequest;
import com.xuanduy.chc_backend.entity.Cart;
import com.xuanduy.chc_backend.entity.Role;
import com.xuanduy.chc_backend.entity.User;
import com.xuanduy.chc_backend.enums.RoleEnum;
import com.xuanduy.chc_backend.exception.AppException;
import com.xuanduy.chc_backend.exception.ErrorCode;
import com.xuanduy.chc_backend.mapper.UserMapper;
import com.xuanduy.chc_backend.repository.CartRepository;
import com.xuanduy.chc_backend.repository.RoleRepository;
import com.xuanduy.chc_backend.repository.UserRepository;
import com.xuanduy.chc_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final CartRepository cartRepository;

    @Override
    public User createUser(UserCreationRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(RoleEnum.USER.getName())
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND)));
        user.setRoles(roles);

        user = userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);

        return user;
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    //todo make url "/getUserById" later that use information from SecurityContextHolder to get userId
    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    //todo make url "/updateUser" later that use information from SecurityContextHolder to get userId
    public User updateUser(String userId, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND, "User"));

        userMapper.updateUser(user, userUpdateRequest);
        user.setRoles(new HashSet<>(roleRepository.findAllById(userUpdateRequest.getRoleIds())));

        return userRepository.save(user);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}

package com.xuanduy.chc_backend.service.impl;

import com.xuanduy.chc_backend.dto.request.AuthenticationRequest;
import com.xuanduy.chc_backend.dto.request.UserCreationRequest;
import com.xuanduy.chc_backend.dto.response.AuthenticationResponse;
import com.xuanduy.chc_backend.entity.Role;
import com.xuanduy.chc_backend.entity.User;
import com.xuanduy.chc_backend.enums.RoleEnum;
import com.xuanduy.chc_backend.exception.AppException;
import com.xuanduy.chc_backend.exception.ErrorCode;
import com.xuanduy.chc_backend.mapper.UserMapper;
import com.xuanduy.chc_backend.repository.RoleRepository;
import com.xuanduy.chc_backend.repository.UserRepository;
import com.xuanduy.chc_backend.security.JwtService;
import com.xuanduy.chc_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    //private final TokenService tokenService;
    private final JwtService jwtService;

    
    @Value("${jwt.signerKey}")
    private String SIGNER_KEY;

    @Override
    public AuthenticationResponse register(UserCreationRequest userCreationRequest) {
        if (userRepository.existsByEmail(userCreationRequest.getEmail())) {
            throw new AppException(ErrorCode.FIELD_EXISTED, "Email");
        }

        User user = userMapper.toUser(userCreationRequest);

        user.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(RoleEnum.USER.getName())
                .orElseThrow(() -> new AppException(ErrorCode.FIELD_NOT_FOUND, "Role")));
        user.setRoles(roles);

        userRepository.save(user);
        return null;
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        //explanation for me to understand:
        //authenticationManager.authenticate() is a method that will authenticate
        //provided by AuthenticationProvider that we have configured

        //if auth failed, it will throw an exception, workflow will stop here
        Authentication auth = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken
                        (authenticationRequest.getEmail(), authenticationRequest.getPassword()));

        //if auth success, auth object will contain the user details
        User user = (User) auth.getPrincipal();
        Map<String, Object> extraClaims = Map.of("fullName", user.getFullName());
        String token = jwtService.generateToken(extraClaims, user);
        System.out.println("token: " + token);
        return AuthenticationResponse.builder()
                .token(token)
                .isSuccessful(true)
                .build();
    }
}

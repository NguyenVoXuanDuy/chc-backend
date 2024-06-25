package com.xuanduy.chc_backend.controller;

import com.xuanduy.chc_backend.dto.request.AuthenticationRequest;
import com.xuanduy.chc_backend.dto.request.UserCreationRequest;
import com.xuanduy.chc_backend.dto.response.ApiResponse;
import com.xuanduy.chc_backend.dto.response.AuthenticationResponse;
import com.xuanduy.chc_backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor

public class AuthController {
    private final AuthService authService;


    @PostMapping("/register")
    public ApiResponse<AuthenticationResponse> register(@RequestBody @Valid UserCreationRequest userCreationRequest) {
        System.out.println("AuthController.register");
        return ApiResponse
                .<AuthenticationResponse>builder()
                .result(
                        authService.register(userCreationRequest)
                )
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        return ApiResponse
                .<AuthenticationResponse>builder()
                .result(
                        authService.login(authenticationRequest)
                )
                .build();
    }
}

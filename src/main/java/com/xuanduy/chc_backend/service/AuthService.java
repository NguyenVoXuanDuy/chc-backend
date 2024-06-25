package com.xuanduy.chc_backend.service;

import com.xuanduy.chc_backend.dto.request.AuthenticationRequest;
import com.xuanduy.chc_backend.dto.request.UserCreationRequest;
import com.xuanduy.chc_backend.dto.response.AuthenticationResponse;

public interface AuthService {


    AuthenticationResponse register(UserCreationRequest userCreationRequest);

    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}

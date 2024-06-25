package com.xuanduy.chc_backend.mapper;


import com.xuanduy.chc_backend.dto.request.UserCreationRequest;
import com.xuanduy.chc_backend.dto.request.UserUpdateRequest;
import com.xuanduy.chc_backend.dto.response.UserResponse;
import com.xuanduy.chc_backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserCreationRequest request);
    
    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
        //Ignore roles because UserUpdateRequest roles is list of string but User roles is list of Role
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
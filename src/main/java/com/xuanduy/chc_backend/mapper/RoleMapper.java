package com.xuanduy.chc_backend.mapper;


import com.xuanduy.chc_backend.dto.request.RoleRequest;
import com.xuanduy.chc_backend.dto.response.RoleResponse;
import com.xuanduy.chc_backend.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
    
    //Ignore permissions because RoleCreationRequest permissions is list of string but User roles is list of Permission
    void updateRole(@MappingTarget Role role, RoleRequest request);
}
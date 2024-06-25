package com.xuanduy.chc_backend.service;

import com.xuanduy.chc_backend.dto.request.RoleRequest;
import com.xuanduy.chc_backend.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse createRole(RoleRequest roleRequest);

    List<RoleResponse> getAllRoles();

    void deleteRole(String roleId);

    void deleteAllRoles();

    RoleResponse updateRole(String roleId, RoleRequest roleRequest);
}

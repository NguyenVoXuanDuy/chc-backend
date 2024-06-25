package com.xuanduy.chc_backend.controller;

import com.xuanduy.chc_backend.dto.request.RoleRequest;
import com.xuanduy.chc_backend.dto.response.ApiResponse;
import com.xuanduy.chc_backend.dto.response.RoleResponse;
import com.xuanduy.chc_backend.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController()
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping("")
    ApiResponse<RoleResponse> createRole(@RequestBody @Valid RoleRequest roleRequest) {

        return ApiResponse.<RoleResponse>builder()
                .message("Role created")
                .result(roleService.createRole(roleRequest))
                .build();
    }

    @GetMapping("")
    ApiResponse<List<RoleResponse>> getAllRoles() {
        return ApiResponse.<List<RoleResponse>>builder()
                .message("All roles")
                .result(roleService.getAllRoles())
                .build();

    }

    @DeleteMapping("/{roleId}")
    ApiResponse<?> deleteRole(@PathVariable("roleId") String roleId) {
        roleService.deleteRole(roleId);
        return ApiResponse.<String>builder()
                .message("Role deleted")
                .build();
    }

    @DeleteMapping("")
    ApiResponse<?> deleteAllRoles() {
        roleService.deleteAllRoles();
        return ApiResponse.<String>builder()
                .message("All roles deleted")
                .build();
    }

    @PutMapping("/{roleId}")
    ApiResponse<RoleResponse> updateRole(@PathVariable("roleId") String roleId, @RequestBody @Valid RoleRequest roleRequest) {
        return ApiResponse.<RoleResponse>builder()
                .message("Role updated")
                .result(roleService.updateRole(roleId, roleRequest))
                .build();
    }
}

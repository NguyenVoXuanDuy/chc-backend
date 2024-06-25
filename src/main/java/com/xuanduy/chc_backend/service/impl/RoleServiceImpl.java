package com.xuanduy.chc_backend.service.impl;

import com.xuanduy.chc_backend.dto.request.RoleRequest;
import com.xuanduy.chc_backend.dto.response.RoleResponse;
import com.xuanduy.chc_backend.entity.Role;
import com.xuanduy.chc_backend.exception.AppException;
import com.xuanduy.chc_backend.exception.ErrorCode;
import com.xuanduy.chc_backend.mapper.RoleMapper;
import com.xuanduy.chc_backend.repository.RoleRepository;
import com.xuanduy.chc_backend.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;


    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public RoleResponse createRole(RoleRequest roleRequest) {
        if (roleRepository.existsByName(roleRequest.getName())) {
            throw new AppException(ErrorCode.ROLE_EXISTED);
        }
        Role role = roleMapper.toRole(roleRequest);
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<RoleResponse> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toRoleResponse).toList();
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteRole(String roleId) {
        roleRepository.deleteById(roleId);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteAllRoles() {
        roleRepository.deleteAll();
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public RoleResponse updateRole(String roleId, RoleRequest roleRequest) {
        if (roleRepository.existsByName(roleRequest.getName())) {
            throw new AppException(ErrorCode.ROLE_EXISTED);
        }
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        roleMapper.updateRole(role, roleRequest);
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }
}

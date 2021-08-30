package com.crud.rest313.dto.mapper;

import com.crud.rest313.dto.RoleDto;
import com.crud.rest313.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Role toRole(RoleDto roleDto);

    RoleDto toDTO(Role role);
}

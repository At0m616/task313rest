package com.crud.rest313.dto.mapper;

import com.crud.rest313.dto.UserDto;
import com.crud.rest313.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserDto userDto);

    UserDto toDTO(User user);


}

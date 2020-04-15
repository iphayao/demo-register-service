package com.iphayao.demo.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User convertToEntity(UserDto userDto);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "matchingPassword", ignore = true)
    UserDto convertToDto(User user);
}

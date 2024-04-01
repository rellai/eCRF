package ru.rellai.ecrf.auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Service;
import ru.rellai.ecrf.auth.dto.RoleDto;
import ru.rellai.ecrf.auth.entity.Role;
import ru.rellai.ecrf.auth.entity.User;
import ru.rellai.ecrf.auth.dto.UserDto;

import java.util.List;

@Mapper(componentModel = "spring")
@Service
public interface UserMapper {


    List<RoleDto> mapRoles(List<Role> roles);

    RoleDto mapRole(Role role);

    List<Role> mapRolesDto(List<RoleDto> roles);


    Role mapRoleDto(RoleDto roleDto);

    @Mappings({
            @Mapping(target = "password", ignore = true)
    })
    UserDto toDto(User user);


    User toEntity(UserDto userDto);

}

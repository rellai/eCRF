package ru.rellai.ecrf.auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Service;
import ru.rellai.ecrf.auth.entity.Role;
import ru.rellai.ecrf.auth.entity.User;
import ru.rellai.ecrf.auth.dto.UserDto;

import java.util.List;

@Mapper(componentModel = "spring")
@Service
public interface UserMapper {


    List<String> mapRoles(List<Role> roles);

    String mapRole(Role role);

    List<Role> mapSrings(List<String> values);

    @Mappings({
      @Mapping(target = "name", source = "value"),
      @Mapping(target = "id", ignore = true)
    })
    Role mapSring(String value);

    UserDto toDto(User user);


    User toEntity(UserDto userDto);

}

package ru.rellai.ecrf.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;
import ru.rellai.ecrf.dto.UserDto;
import ru.rellai.ecrf.entity.User;

@Mapper(componentModel = "spring")
@Service
public interface UserMapper {

    UserDto toDto(User user);

    User toModel(UserDto userDto);

}

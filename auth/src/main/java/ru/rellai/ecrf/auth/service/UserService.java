package ru.rellai.ecrf.auth.service;

import ru.rellai.ecrf.auth.dto.UserDto;
import ru.rellai.ecrf.auth.entity.User;

import java.util.List;


public interface UserService {

    List<UserDto> findAll();

    List<UserDto> findAllByUsername(String username);

    UserDto findById(Long id);

    void deleteById(Long id);

    UserDto save(UserDto userDto);

    UserDto saveUserWithRoles(User user, List<Long> roles);


}

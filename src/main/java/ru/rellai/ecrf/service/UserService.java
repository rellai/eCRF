package ru.rellai.ecrf.service;

import ru.rellai.ecrf.dto.UserDto;

import java.util.List;


public interface UserService {

    List<UserDto> findAll();

    List<UserDto> findAllByUsername(String username);

    UserDto findById(Long id);

    void deleteById(Long id);

    UserDto save(UserDto userDto);


}

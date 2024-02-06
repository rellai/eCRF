package ru.rellai.ecrf.service;

import ru.rellai.ecrf.dto.UserDto;

import java.util.List;


public interface UserService {

    List<UserDto> findAll();

}

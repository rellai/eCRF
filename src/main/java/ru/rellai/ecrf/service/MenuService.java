package ru.rellai.ecrf.service;

import ru.rellai.ecrf.dto.MenuDto;
import ru.rellai.ecrf.dto.UserDto;

import java.util.List;


public interface MenuService {

    List<MenuDto> findAllbyMenuId(Long id);

}

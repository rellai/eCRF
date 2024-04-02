package ru.rellai.ecrf.auth.service;

import ru.rellai.ecrf.auth.dto.MenuDto;

import java.util.List;


public interface MenuService {

    List<MenuDto> findAllbyMenuId(Long menuId);

}

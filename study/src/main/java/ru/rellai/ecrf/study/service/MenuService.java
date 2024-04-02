package ru.rellai.ecrf.study.service;

import ru.rellai.ecrf.study.dto.MenuDto;

import java.util.List;


public interface MenuService {

    List<MenuDto> findAllbyMenuId(Long menuId);

}

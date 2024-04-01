package ru.rellai.ecrf.auth.service;


import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.rellai.ecrf.auth.repository.MenuRepository;
import ru.rellai.ecrf.auth.dto.MenuDto;
import ru.rellai.ecrf.auth.mapper.MenuMapper;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    private final MenuMapper menuMapper;

    @Override
    @Cacheable(value = "menus")
    public List<MenuDto> findAllbyMenuId(Long menuId) {
        return menuRepository.findAllByMenuId(menuId).stream().map(menuMapper::toDto).toList();
    }


}

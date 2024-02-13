package ru.rellai.ecrf.service;


import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.rellai.ecrf.dto.MenuDto;
import ru.rellai.ecrf.mapper.MenuMapper;
import ru.rellai.ecrf.repository.MenuRepository;

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

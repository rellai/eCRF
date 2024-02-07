package ru.rellai.ecrf.service;

import lombok.RequiredArgsConstructor;
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
    public List<MenuDto> findAllbyMenuId(Long id) {
        return menuRepository.findAllByMenuId(id).stream().map(menuMapper::toDto).toList();
    }


}

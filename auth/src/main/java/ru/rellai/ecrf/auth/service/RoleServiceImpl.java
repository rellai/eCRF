package ru.rellai.ecrf.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rellai.ecrf.auth.dto.RoleDto;
import ru.rellai.ecrf.auth.mapper.RoleMapper;
import ru.rellai.ecrf.auth.repository.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository ;

    private final RoleMapper roleMapper ;

    @Override
    public List<RoleDto> findAll() {

        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toList());

    }
}

package ru.rellai.ecrf.auth.service;

import ru.rellai.ecrf.auth.dto.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> findAll();

}

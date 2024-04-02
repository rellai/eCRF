package ru.rellai.ecrf.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public final class MenuDto {

    private final Long id;

    private final Long menuId;

    private final String name;

    private final String url;

    private final String status;

    private final List<MenuDto> childs;

    private boolean active;
}
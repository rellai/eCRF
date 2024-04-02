package ru.rellai.ecrf.auth.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link ru.rellai.ecrf.auth.entity.Role}
 */
@AllArgsConstructor
@Getter
public class RoleDto implements Serializable {

    @NotNull
    private final Long id;

    private final String name;


}
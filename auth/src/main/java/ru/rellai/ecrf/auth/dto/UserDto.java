package ru.rellai.ecrf.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import ru.rellai.ecrf.auth.entity.User;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link User}
 */
public record UserDto(
        @NotNull(message = "Id is required")
        long id,
        @NotEmpty(message = "Username is required")
        String username,
        @NotEmpty(message = "password is required")
        String password,
        List<RoleDto> roles,
        String email
) implements Serializable {
}
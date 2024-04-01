package ru.rellai.ecrf.auth.dto;

import ru.rellai.ecrf.auth.entity.User;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link User}
 */
public record UserDto(long id,
                      String username,
                      String password,
                      List<String> roles,
                      String email
) implements Serializable {
}
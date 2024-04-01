package ru.rellai.ecrf.study.dto;

import ru.rellai.ecrf.study.entity.User;

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
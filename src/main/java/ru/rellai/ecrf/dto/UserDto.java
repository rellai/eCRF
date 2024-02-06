package ru.rellai.ecrf.dto;

import ru.rellai.ecrf.entity.User;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
public record UserDto(long id, String username, String password, String authorities) implements Serializable {
}
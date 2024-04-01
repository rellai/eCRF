package ru.rellai.ecrf.auth.service;

import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rellai.ecrf.auth.exceptions.NotFoundException;
import ru.rellai.ecrf.auth.repository.RoleRepository;
import ru.rellai.ecrf.auth.repository.UserRepository;
import ru.rellai.ecrf.auth.entity.Role;
import ru.rellai.ecrf.auth.entity.User;
import ru.rellai.ecrf.auth.dto.UserDto;
import ru.rellai.ecrf.auth.mapper.UserMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    @Override
    @Cacheable("users")
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    @Override
    @Cacheable("users")
    public List<UserDto> findAllByUsername(String username) {

        return userRepository.findByUsernameContainingIgnoreCase(username).stream().map(userMapper::toDto).toList();
    
    }

    @Override
    @Cacheable("users")
    public UserDto findById(Long id) {
        return userRepository.findById(id).map(userMapper::toDto)
                .orElseThrow(() -> new NotFoundException("User with id %d not found".formatted(id))
        );

    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public UserDto save(UserDto userDto) {

        User user = new User();

        if (userDto.id() != 0) {
         user = userRepository.findById(userDto.id()).
                orElseThrow(() -> new NotFoundException("User with id %d not found".formatted(userDto.id())));
        }

        user.setUsername(userDto.username());


        user.setRoles((userDto.roles().stream().map(s -> {
            Role role = new Role();
            role.setName(s);
            return role;
        }).toList()));

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public UserDto saveUserWithRoles(User user, List<Long> roles) {
        Set<Role> assignedRoles = roles.stream()
                .map(roleId -> roleRepository.findById(roleId).orElseThrow())
                .collect(Collectors.toSet());
        user.setRoles(assignedRoles);
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }


}

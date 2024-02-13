package ru.rellai.ecrf.service;

import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.rellai.ecrf.dto.UserDto;
import ru.rellai.ecrf.entity.User;
import ru.rellai.ecrf.exceptions.NotFoundException;
import ru.rellai.ecrf.mapper.UserMapper;
import ru.rellai.ecrf.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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

        if (userDto.id()!= 0) {
         user = userRepository.findById(userDto.id()).
                orElseThrow(() -> new NotFoundException("User with id %d not found".formatted(userDto.id())));
        }

        user.setUsername(userDto.username());
        user.setAuthorities(userDto.authorities());

        return userMapper.toDto(userRepository.save(user));
    }


}

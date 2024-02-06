package ru.rellai.ecrf.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rellai.ecrf.dto.UserDto;
import ru.rellai.ecrf.mapper.UserMapper;
import ru.rellai.ecrf.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }


}

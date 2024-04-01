package ru.rellai.ecrf.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rellai.ecrf.auth.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);

    List<User> findByUsernameContainingIgnoreCase(String username);

}

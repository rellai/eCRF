package ru.rellai.ecrf.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rellai.ecrf.study.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}

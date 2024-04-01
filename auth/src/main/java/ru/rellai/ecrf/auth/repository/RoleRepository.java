package ru.rellai.ecrf.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rellai.ecrf.auth.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}

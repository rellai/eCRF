package ru.rellai.ecrf.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rellai.ecrf.auth.entity.Menu;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findAllByMenuId(Long menuId);
}
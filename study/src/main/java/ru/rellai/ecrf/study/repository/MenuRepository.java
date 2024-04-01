package ru.rellai.ecrf.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rellai.ecrf.study.entity.Menu;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findAllByMenuId(Long menuId);
}
package ru.rellai.ecrf.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rellai.ecrf.entity.Menu;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findAllByMenuId(Long menuId);
}
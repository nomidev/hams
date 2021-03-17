package com.huneth.hams.admin.repository;

import java.util.List;

import com.huneth.hams.admin.model.Menu;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findByUseFlag(Boolean useFlag, Sort sort);
}

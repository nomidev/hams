package com.huneth.hams.admin.repository;

import java.util.List;

import com.huneth.hams.admin.model.Menu;

import com.huneth.hams.common.commonEnum.YnFlag;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findByUseFlag(YnFlag ynFlag, Sort sort);

    Menu findByMenuUrlAndUseFlag(String menuUrl, YnFlag ynFlag);

    List<Menu> findByParentIdAndUseFlag(String parentId, YnFlag ynFlag, Sort sort);
}

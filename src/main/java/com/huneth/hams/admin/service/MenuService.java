package com.huneth.hams.admin.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.huneth.hams.admin.dto.MenuDto;
import com.huneth.hams.admin.model.Menu;
import com.huneth.hams.admin.repository.MenuRepository;
import com.huneth.hams.config.auth.PrincipalDetails;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    /**
     * 메뉴 조회
     * @return
     */
    public Map<MenuDto, List<MenuDto>> retrieveEnableMenuList() {

        List<Menu> menuList = menuRepository.findByUseFlag(true, Sort.by("menuLevelNo").and(Sort.by("sortOrderNo")));

        PropertyMap<Menu, MenuDto> menuMap = new PropertyMap<Menu, MenuDto>() {
            protected void configure() {
                map().setText(source.getMenuName());
            }
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(menuMap);
        List<MenuDto> menuDtos = menuList
                .stream()
                .map(menu -> modelMapper.map(menu, MenuDto.class))
                .collect(Collectors.toList());


        List<MenuDto> parentMenuList = menuDtos.stream()
                                    .filter(menu -> menu.getMenuLevelNo() == 1)
                                    .collect(Collectors.toList());

        log.debug("parent = " + parentMenuList);

        List<MenuDto> childAMenuList = menuDtos.stream()
                                    .filter(menu -> menu.getMenuLevelNo() > 1)
                                    .collect(Collectors.toList());

        Map<String, List<MenuDto>> childMenuMap = menuDtos.stream()
                                            .filter(menu -> menu.getMenuLevelNo() != 1)
                                            .collect(Collectors.groupingBy(MenuDto::getParent));

                                    // Map<Long, List<Category>> childMenuMap = menuList.stream()
                                    // .filter(category -> category.getIdParent() != CategoryConstant.PARENT_ROOT)
                                    // .collect(Collectors.groupingBy(Category::getIdParent))
                                    // ;

        log.debug("childMenuMap = " + childMenuMap);


        Map<MenuDto, List<MenuDto>> menuTree = new LinkedHashMap<>();
        for (MenuDto parent : parentMenuList) {
            String id = String.valueOf(parent.getId());
            List<MenuDto> child = childMenuMap.get(id);

            menuTree.put(parent, child);
        }

        log.debug("menuTree = " + menuTree);

        // Map<MenuDto, List<MenuDto>> sortedMenuMap = 
        //     menuTree.entrySet().stream()
        //     .sorted((e1, e2)-> {

        //         log.info("e1" + e1);
        //         log.info("e2" + e2);
        //         if (e1.getKey().getSortOrderNo() == e2.getKey().getSortOrderNo()) {
        //             return 0;
        //         } else if (e1.getKey().getSortOrderNo() > e2.getKey().getSortOrderNo()) {
        //             return 1;
        //         } else {
        //             return -1;
        //         }
        //     })
        //     .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        // log.info("sortedMenuMap = " + sortedMenuMap);
       

        return menuTree;
    }

    /**
     * 메뉴관리 조회
     * @return
     */
    public List<MenuDto> retrieveMenuList() {

        List<Menu> menus = menuRepository.findAll(Sort.by("menuLevelNo").and(Sort.by("sortOrderNo")));

        PropertyMap<Menu, MenuDto> menuMap = new PropertyMap<Menu, MenuDto>() {
            protected void configure() {
                map().setText(source.getMenuName());
            }
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(menuMap);
        List<MenuDto> menuDtos = menus
                                .stream()
                                .map(menu -> modelMapper.map(menu, MenuDto.class))
                                .collect(Collectors.toList());

        log.debug(menuDtos.toString());

        return menuDtos;
    }

    /**
     * 메뉴 저장
     * @param menuDto
     */
    public void saveMenu(MenuDto menuDto) {
        log.debug("menuDto = " + menuDto);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
        PrincipalDetails principalDetails = (PrincipalDetails) principal;

        PropertyMap<MenuDto, Menu> menuMap = new PropertyMap<MenuDto, Menu>() {
            protected void configure() {
                map().setParentId(source.getParent());
            }
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(menuMap);
        Menu menu = modelMapper.map(menuDto, Menu.class);

        log.debug("menu = " + menu);

        menu.setCreatedBy(principalDetails.getId());

        menuRepository.save(menu);
    }

    /**
     * 메뉴 삭제
     * @param menuDto
     */
    public void deleteMenu(MenuDto menuDto) {
        log.debug("menuDto = " + menuDto);

        menuRepository.deleteById(menuDto.getId());
    }
}

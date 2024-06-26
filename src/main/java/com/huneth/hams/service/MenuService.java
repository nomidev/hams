package com.huneth.hams.service;

import com.huneth.hams.dto.MenuDto;
import com.huneth.hams.model.Menu;
import com.huneth.hams.repository.MenuRepository;
import com.huneth.hams.commonEnum.YnFlag;
import com.huneth.hams.config.auth.PrincipalDetails;
import com.huneth.hams.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 공통 메뉴 조회
     * @return
     */
    public Map<MenuDto, List<MenuDto>> retrieveEnableMenuList() {

        List<Menu> menuList = menuRepository.findByUseFlag(YnFlag.Y, Sort.by("menuLevelNo").and(Sort.by("sortOrderNo")));

        PropertyMap<Menu, MenuDto> menuMap = new PropertyMap<Menu, MenuDto>() {
            protected void configure() {
                map().setText(source.getMenuName());
            }
        };
        TypeMap<Menu, MenuDto> typeMap = modelMapper.getTypeMap(Menu.class, MenuDto.class);

        if (typeMap == null) {
            modelMapper.addMappings(menuMap);
        }

        // List<MenuDto> menuDtos = menuList
        //         .stream()
        //         .map(menu -> modelMapper.map(menu, MenuDto.class))
        //         .collect(Collectors.toList());
        // CommonUtil.mapList는 위 내용과 같은 기능을 수행한다.
        List<MenuDto> menuDtos = CommonUtil.mapList(modelMapper, menuList, MenuDto.class);

        // MenuLevel1
        List<MenuDto> parentMenuList = menuDtos.stream()
                                    .filter(menu -> menu.getMenuLevelNo() == 1)
                                    .collect(Collectors.toList());

        log.info("parent = " + parentMenuList);

        // MenuLevel2
        Map<String, List<MenuDto>> childMenuMap = menuDtos.stream()
                                            .filter(menu -> menu.getMenuLevelNo() != 1)
                                            .collect(Collectors.groupingBy(MenuDto::getParent));

        log.info("childMenuMap = " + childMenuMap);

        // 순서를 보장하기 위해 LinkedHashMap을 사용한다.
        Map<MenuDto, List<MenuDto>> menuTree = new LinkedHashMap<>();
        for (MenuDto parent : parentMenuList) {
            String id = String.valueOf(parent.getId());
            List<MenuDto> child = childMenuMap.get(id);

            menuTree.put(parent, child);
        }

        log.info("menuTree = " + menuTree);

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
     * 서브메뉴 조회
     * @param req
     * @return
     */
    public List<MenuDto> retrieveSubMenu(HttpServletRequest req) {
        String uri = req.getRequestURI();

        if (uri.indexOf("/", 1) > 0) {
            uri = uri.substring(0, uri.indexOf("/", 1));
        }

        Menu menu = menuRepository.findByMenuUrlAndUseFlag(uri, YnFlag.Y);

        if (menu == null || menu.getMenuLevelNo() == 0) {
            return new ArrayList<MenuDto>();
        }

        String parentId = String.valueOf(menu.getId());
        List<Menu> subMenu = menuRepository.findByParentIdAndUseFlag(parentId, YnFlag.Y, Sort.by("menuLevelNo").and(Sort.by("sortOrderNo")));

        List<MenuDto> subMenus = CommonUtil.mapList(modelMapper, subMenu, MenuDto.class);

        return subMenus;
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
        TypeMap<Menu, MenuDto> typeMap = modelMapper.getTypeMap(Menu.class, MenuDto.class);

        if (typeMap == null) {
            modelMapper.addMappings(menuMap);
        }

        List<MenuDto> menuDtos = CommonUtil.mapList(modelMapper, menus, MenuDto.class);

        return menuDtos;
    }

    /**
     * 메뉴 저장
     * @param menuDto
     */
    public void saveMenu(MenuDto menuDto) {
        log.info("menuDto = " + menuDto);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
        PrincipalDetails principalDetails = (PrincipalDetails) principal;

        PropertyMap<MenuDto, Menu> menuMap = new PropertyMap<MenuDto, Menu>() {
            protected void configure() {
                map().setParentId(source.getParent());
            }
        };

        TypeMap<MenuDto, Menu> typeMap = modelMapper.getTypeMap(MenuDto.class, Menu.class);
        if (typeMap == null) {
            modelMapper.addMappings(menuMap);
        }

        Menu menu = modelMapper.map(menuDto, Menu.class);

        log.info("menu = " + menu);

        menuRepository.save(menu);
    }

    /**
     * 메뉴 삭제
     * @param menuDto
     */
    public void deleteMenu(MenuDto menuDto) {
        log.info("menuDto = " + menuDto);

        menuRepository.deleteById(menuDto.getId());
    }
}

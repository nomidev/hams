package com.huneth.hams.admin.service;

import java.security.Principal;
import java.util.List;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<MenuDto> retrieveEnableMenuList() {
        return null;
    }

    public List<MenuDto> retrieveMenuList() {

        List<Menu> menus = menuRepository.findAll(Sort.by(Sort.Direction.ASC, "sortOrderNo"));

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
}

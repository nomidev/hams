package com.huneth.hams.admin.service;

import com.huneth.hams.admin.dto.MenuDto;
import com.huneth.hams.admin.model.Menu;
import com.huneth.hams.admin.repository.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<MenuDto> retrieveMenuList() {

        List<Menu> menus = menuRepository.findAll();

        PropertyMap<Menu, MenuDto> MenuMap = new PropertyMap<Menu, MenuDto>() {
            protected void configure() {
                map().setText(source.getMenuName());
            }
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(MenuMap);
        List<MenuDto> menuDtos = menus
                                .stream()
                                .map(menu -> modelMapper.map(menu, MenuDto.class))
                                .collect(Collectors.toList());

        log.debug(menuDtos.toString());

        return menuDtos;
    }
}

package com.huneth.hams.common.controller;

import com.huneth.hams.admin.dto.MenuDto;
import com.huneth.hams.admin.model.Menu;
import com.huneth.hams.admin.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalController {

    @Autowired
    private MenuService menuService;

    // @RequestMapping 어노테이션이 붙은 메소드가 호출될 때마다 그 메소드 호출 전에 @ModelAttribute가 붙은 메소드가 일단 먼저 호출되고
    // 그 이후 @RequestMapping이 붙은 메소드가 호출되는데 이때 @ModelAttribute 메소드 실행 결과로 리턴되는 객체(데이터)는
    // 자동으로 @RequestMapping 어노테이션이 붙은 메소드의 Model에 저장이되고 그 이후에 .jsp(View)에서 @ModelAttribute 메소드가 반환한 데이터를 사용할수 있다.
    // Tiles? 타일즈?
    @ModelAttribute
    public void retrieveMenu(Model model) {
        log.debug("retrieveMenu");

        List<MenuDto> result = menuService.retrieveEnableMenuList();

        log.debug("retrieveMenu" + result);

        model.addAttribute("menuList", result);
    }
}

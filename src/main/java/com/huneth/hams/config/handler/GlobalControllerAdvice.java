package com.huneth.hams.config.handler;

import java.util.List;
import java.util.Map;

import com.huneth.hams.dto.MenuDto;
import com.huneth.hams.service.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private MenuService menuService;

    // @RequestMapping 어노테이션이 붙은 메소드가 호출될 때마다 그 메소드 호출 전에 @ModelAttribute가 붙은 메소드가 일단 먼저 호출되고
    // 그 이후 @RequestMapping이 붙은 메소드가 호출되는데 이때 @ModelAttribute 메소드 실행 결과로 리턴되는 객체(데이터)는
    // 자동으로 @RequestMapping 어노테이션이 붙은 메소드의 Model에 저장이되고 그 이후에 .jsp(View)에서 @ModelAttribute 메소드가 반환한 데이터를 사용할수 있다.
    // Tiles? 타일즈?
    @ModelAttribute
    public void retrieveMenu(HttpServletRequest req, Model model) {
        String uri = req.getRequestURI();

        // 로그인 페이지는 메뉴정보를 불러올 필요가 없다.
        if ("/login".equals(uri)) {
            return;
        }

        if (uri.contains("api")) {
            return;
        }

        Map<MenuDto, List<MenuDto>> result = menuService.retrieveEnableMenuList();

        List<MenuDto> subMenus = menuService.retrieveSubMenu(req);

        log.info("retrieveMenu = " + result);
        model.addAttribute("menuList", result);
        model.addAttribute("subMenuList", subMenus);
    }

    /*@ExceptionHandler(IllegalArgumentException.class)
    public String handler(HttpServletRequest request, IllegalArgumentException e, Model model) {
        log.debug("ExceptionHandler !!!!!!!!!!!!!!!!!!! ExceptionHandler");
        log.error(e.getMessage());
        e.printStackTrace();
        model.addAttribute("erroMessage", e.getMessage());

        return "error/error";
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public String accessDeniedHandler(AccessDeniedException e) {
        log.debug(e.getMessage());
        e.printStackTrace();
        return "error/error";
    }*/
}

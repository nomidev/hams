package com.huneth.hams.common.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
// @ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
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
    }
}

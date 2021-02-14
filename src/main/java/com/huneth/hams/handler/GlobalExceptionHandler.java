package com.huneth.hams.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public String handler(Model model, Exception e, HttpStatus httpStatus) {
        HttpStatus status = httpStatus.INTERNAL_SERVER_ERROR;
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

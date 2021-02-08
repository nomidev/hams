package com.huneth.hams.handler;

import org.springframework.http.HttpStatus;
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

        model.addAttribute("erroMessage", e.getMessage());
        model.addAttribute("erroMessage", httpStatus.getClass();

        return "error/error";
    }
}

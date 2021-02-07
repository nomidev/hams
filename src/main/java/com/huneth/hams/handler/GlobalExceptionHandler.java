package com.huneth.hams.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public String handler(Exception e, Model model) {
        e.getMessage();
        e.getCause();

        return "error/error";
    }
}

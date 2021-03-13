package com.huneth.hams.common.controller;

import java.util.Map;

import com.huneth.hams.admin.service.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CommonApiController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/api/menu")
    public ResponseEntity<Object> getMenu(@RequestBody Map param) {
        log.debug("menu");
        log.debug("param = " + param);

        menuService.retrieveEnableMenuList();

        return new ResponseEntity<Object>(HttpStatus.OK);
    }
    
}

package com.huneth.hams.controller;

import java.util.List;

import com.huneth.hams.dto.MenuDto;
import com.huneth.hams.service.MenuService;
import com.huneth.hams.dto.ResponseDto;
import com.huneth.hams.commonEnum.StatusEnum;
import com.huneth.hams.model.Role;
import com.huneth.hams.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/menu")
    public String menu(Model model) {
        
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);

        return "admin/menu";
    }

    @GetMapping("/menu/api")
    @ResponseBody
    public ResponseEntity<ResponseDto> getMenu() {
        List<MenuDto> menuDtos = menuService.retrieveMenuList();

        ResponseDto responseDto = ResponseDto.builder()
                .data(menuDtos)
                .statusCode(StatusEnum.OK)
                .message("SUCCESS")
                .build();

        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/menu/api")
    @ResponseBody
    public ResponseEntity<ResponseDto> saveMenu(@RequestBody MenuDto menuDto) {
        log.debug("menuDto = " + menuDto);

        menuService.saveMenu(menuDto);

        ResponseDto responseDto = ResponseDto.builder()
                .statusCode(StatusEnum.OK)
                .message("SUCCESS")
                .build();

        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/menu/api")
    @ResponseBody
    public ResponseEntity<ResponseDto> deleteMenu(@RequestBody MenuDto menuDto) {
        log.debug("menuDto = " + menuDto);

        menuService.deleteMenu(menuDto);

        ResponseDto responseDto = ResponseDto.builder()
                .statusCode(StatusEnum.OK)
                .message("SUCCESS")
                .build();

        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }
}

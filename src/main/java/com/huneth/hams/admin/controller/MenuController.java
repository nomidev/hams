package com.huneth.hams.admin.controller;

import com.huneth.hams.admin.model.Menu;
import com.huneth.hams.admin.repository.MenuRepository;
import com.huneth.hams.common.model.ResponseDto;
import com.huneth.hams.common.model.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("/menu")
    public String menu() {
        return "admin/menu";
    }

    @GetMapping("/menu/api")
    @ResponseBody
    public ResponseEntity<ResponseDto> getMenu() {
        List<Menu> menus = menuRepository.findAll();

        ResponseDto responseDto = ResponseDto.builder()
                                    .data(menus)
                                    .statusCode(StatusEnum.OK)
                                    .message("SUCCESS")
                                    .build();

        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }
}

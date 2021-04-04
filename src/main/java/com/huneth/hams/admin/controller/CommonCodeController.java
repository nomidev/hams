package com.huneth.hams.admin.controller;

import com.huneth.hams.admin.model.CommonCode;
import com.huneth.hams.admin.service.CommonCodeService;
import com.huneth.hams.common.dto.ResponseDto;
import com.huneth.hams.common.commonEnum.StatusEnum;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/admin")
public class CommonCodeController {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private CommonCodeService commonCodeService;

    @RequestMapping("/commonCode")
    public String cmmcList() {
        return "admin/commonCodeList";
    }

    @RequestMapping("/commonCode/api")
    @ResponseBody
    public ResponseEntity<ResponseDto> cmmcList(Model model) {
        List<CommonCode> commonCodeList = commonCodeService.retrieveCommonCodeList();

        // Toast UI Grid는 contents에 데이터를 담아야 한다.
        Map result = new HashMap();
        result.put("contents", commonCodeList);

        ResponseDto responseDto = ResponseDto.builder()
                .data(result)
                .message("SUCCESS")
                .statusCode(StatusEnum.OK)
                .result(true)
                .build();

        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/commonCode/api")
    @ResponseBody
    public ResponseEntity<ResponseDto> save(@RequestBody Map<String, Object> param) {
        log.info("param = " + param);


        List<CommonCode> commonCodeList = commonCodeService.retrieveCommonCodeList();

        // Toast UI Grid는 contents에 데이터를 담아야 한다.
        Map result = new HashMap();
        result.put("contents", commonCodeList);

        ResponseDto responseDto = ResponseDto.builder()
                .data(result)
                .message("SUCCESS")
                .statusCode(StatusEnum.OK)
                .result(true)
                .build();

        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/form")
    public String cmmcForm() {
        return "admin/cmmcForm";
    }

    @PostMapping("/form")
    public void cmmcSave() {

    }
}

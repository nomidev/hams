package com.huneth.hams.admin.controller;

import com.huneth.hams.admin.model.CommonCode;
import com.huneth.hams.admin.service.CommonCodeService;
import com.huneth.hams.common.commonEnum.StatusEnum;
import com.huneth.hams.common.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/admin")
public class CommonCodeController {

    @Autowired
    private CommonCodeService commonCodeService;

    @RequestMapping("/commonCode")
    public String cmmcList() {
        return "admin/commonCodeList";
    }

    @RequestMapping("/commonCode/api")
    @ResponseBody
    public ResponseEntity<ResponseDto> list(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("pageable" + pageable);


        List<CommonCode> commonCodeList = commonCodeService.retrieveCommonCodeList();

        // Toast UI Grid는 contents에 데이터를 담아야 한다.
        Map result = new HashMap();
        Map pageMap = new HashMap();

        pageMap.put("page", 1);
        pageMap.put("totalCount", 6);

        result.put("contents", commonCodeList);
        result.put("pagination", pageMap);

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
    public ResponseEntity<ResponseDto> save(@RequestBody Map<String, List<CommonCode>> param) {
        // Map<String, List<HashMap>> param => Generic 을 명시하지 않으면 LinkedHashMap으로 넘어온다.
        log.info("param = " + param);

        List<CommonCode> createdRows = (List<CommonCode>) param.get("createdRows");
        List<CommonCode> updatedRows = (List<CommonCode>) param.get("updatedRows");
        List<CommonCode> deletedRows = (List<CommonCode>) param.get("deletedRows");

        // insert
        if (createdRows.size() > 0) {
            for (CommonCode cRow : createdRows) {
                commonCodeService.saveCommonCode(cRow);
            }
        }

        // update
        if (updatedRows.size() > 0) {
            for (CommonCode uRow : updatedRows) {
                commonCodeService.updateCommonCode(uRow);
            }
        }

        // delete
        if (deletedRows.size() > 0) {
            for (CommonCode dRow : deletedRows) {
                commonCodeService.deleteCommonCode(dRow);
            }
        }

        ResponseDto responseDto = ResponseDto.builder()
                .message("SUCCESS")
                .statusCode(StatusEnum.OK)
                .result(true)
                .build();

        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

}

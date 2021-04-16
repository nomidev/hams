package com.huneth.hams.admin.controller;

import com.huneth.hams.admin.dto.BulletinDto;
import com.huneth.hams.admin.model.Bulletin;
import com.huneth.hams.admin.service.BulletinService;
import com.huneth.hams.common.commonEnum.StatusEnum;
import com.huneth.hams.common.config.auth.PrincipalDetails;
import com.huneth.hams.common.dto.ResponseDto;
import com.huneth.hams.member.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/admin")
public class BulletinController {

    @Autowired
    private BulletinService bulletinService;

    @RequestMapping("/bulletin/api")
    public ResponseEntity<ResponseDto> list(@RequestParam int page, @RequestParam int perPage,
                                            @ModelAttribute Bulletin param) {
        // 페이징 설정
        Pageable pageable = PageRequest.of(page, perPage);
        List<Bulletin> bulletinList = bulletinService.retrieveBulletinList(param, pageable);

        // Toast UI Grid는 contents에 데이터를 담아야 한다.
        Map result = new HashMap();
        Map pageMap = new HashMap();

        // Toast UI Grid pageOptions 설정
        pageMap.put("page", pageable.getPageNumber());
        pageMap.put("totalCount", bulletinService.retrieveTotalCount(param));

        result.put("contents", bulletinList);
        result.put("pagination", pageMap);

        ResponseDto responseDto = ResponseDto.builder()
                .data(result)
                .message("SUCCESS")
                .statusCode(StatusEnum.OK)
                .result(true)
                .build();

        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/bulletin/api")
    public ResponseEntity<ResponseDto> save(@RequestBody Map<String, List<Bulletin>> param,
                                            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        // Map<String, List<HashMap>> param => Generic 을 명시하지 않으면 LinkedHashMap으로 넘어온다.
        log.info("param = " + param);

        List<Bulletin> createdRows = (List<Bulletin>) param.get("createdRows");
        List<Bulletin> updatedRows = (List<Bulletin>) param.get("updatedRows");
        List<Bulletin> deletedRows = (List<Bulletin>) param.get("deletedRows");

        User user = principalDetails.getUser();

        // insert
        if (createdRows.size() > 0) {
            for (Bulletin cRow : createdRows) {
                bulletinService.saveBulletin(cRow);
            }
        }

        // update
        if (updatedRows.size() > 0) {
            for (Bulletin uRow : updatedRows) {
                bulletinService.updateBulletin(uRow);
            }
        }

        // delete
        if (deletedRows.size() > 0) {
            for (Bulletin dRow : deletedRows) {
                bulletinService.deleteBulletin(dRow);
            }
        }

        ResponseDto responseDto = ResponseDto.builder()
                .message("SUCCESS")
                .statusCode(StatusEnum.OK)
                .result(true)
                .build();

        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    /*@GetMapping("/bulletin/list")
    public String bulletinlist(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {
        // Page<bulletin> bulletinList = bulletinRepository.findAll(PageRequest.of(0, 20)); // jpa page가 0부터 시작한다.
        // Page<bulletin> bulletinList = bulletinRepository.findAll(pageable); // jpa page가 0부터 시작한다.
        Page<Bulletin> bulletinList = bulletinRepository.findByTitleContaining(searchText, pageable); // jpa page가 0부터 시작한다.
        // bulletinList.getTotalElements();

        int startPage = Math.max(1, bulletinList.getPageable().getPageNumber() - 2);
        int endPage = Math.min(bulletinList.getTotalPages(), bulletinList.getPageable().getPageNumber() + 2);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("bulletinList", bulletinList);
        return "admin/bulletin/bulletinList";
    }

    @GetMapping("/bulletin/form")
    // @Secured(value = "hasRole('ROLE_ADMIN')")
    public String bulletinForm(Model model, @RequestParam(required = false) Integer id, Bulletin bulletin) {
        // PathVariable이 넘어오지 않을경 우 에러가 난다.
        // Optional을 사용할 수 있다.
        if (id == null) {
            // id가 없을 경우 새로운 bulletin를 생성해 화면으로 전달한다.
            // model.addAttribute("bulletin", new Bulletin());
        } else {
            Bulletin bulletinResult = bulletinRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No data found"));
            model.addAttribute("bulletin", bulletinResult);
        }

        List<CommonCode> bulletinCodeType = commonCodeRepository.findByCodeTypeOrderBySortNoAsc("BULLETIN");
        model.addAttribute("bulletinCodeType", bulletinCodeType);

        return "admin/bulletin/bulletinForm";
    }

    @PostMapping("/bulletin/form")
    public String bulletinSave(@Valid Bulletin bulletin, BindingResult bindingResult,
                               @AuthenticationPrincipal PrincipalDetails principalDetails) {

        // Authentication authentication 파라미터로도 인증정보를 가져올 수 있다.
        // bulletinValidator.validate(bulletin, bindingResult);

        if (bindingResult.hasErrors()) {
            return "admin/bulletin/bulletinForm";
        }

        if (principalDetails == null) {
            return "redirect:/login?needLogin";
        }

        // 인증정보로 사용자를 가져와 저장한다.
        *//*String username = authentication.getName();
        User user = userRepository.findByUsername(username);*//*

        bulletin.setUser(principalDetails.getUser());
        bulletinRepository.save(bulletin);
        return "redirect:/admin/bulletin/list";
    }

    public ResponseEntity<Bulletin> apiTest(@Valid @RequestBody Bulletin bulletin,
                                  @AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println(bulletin);

        if (principalDetails != null) {
            // return new ResponseEntity<>(bulletin, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            String username = principalDetails.getUsername();
            User user = userRepository.findByUsername(username);

            bulletin.setUser(user);
            bulletinRepository.save(bulletin);
        }

        return new ResponseEntity<>(bulletin, HttpStatus.OK);
    }*/

}

package com.huneth.hams.common.config.handler;

import com.huneth.hams.common.commonEnum.StatusEnum;
import com.huneth.hams.common.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalRestControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handler(HttpServletRequest request, Exception e, HttpStatus status) {
        log.debug("GlobalApiExceptionHandler !!!!!!!!!!!!!!!!!!!");
        log.error(e.getMessage());

        ResponseDto responseDto = ResponseDto.builder()
                .message(e.getMessage())
                .statusCode(StatusEnum.INTERNAL_SERER_ERROR)
                .result(false)
                .build();

        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

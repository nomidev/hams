package com.huneth.hams.common.dto;

import com.huneth.hams.common.commonEnum.StatusEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto {

    // 빌더 기본값 정의
    @Builder.Default private StatusEnum statusCode = StatusEnum.BAD_REQUEST;
    private String message;
    private Boolean result; // Toast UI Grid 성공 true, 실패 false
    private Object data;
}

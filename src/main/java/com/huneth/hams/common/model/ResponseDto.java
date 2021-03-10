package com.huneth.hams.common.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto {

    // 빌더 기본값 정의
    @Builder.Default private StatusEnum statusCode = StatusEnum.BAD_REQUEST;
    private String message;
    private Object data;
}

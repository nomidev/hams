package com.huneth.hams.config.validator;


import com.huneth.hams.model.Board;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

/**
 * 게시판 Validator
 */
@Component
public class BoardValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Board.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Board obj = (Board) o;

        if (StringUtils.isEmpty(obj.getTitle())) {
//            errors.reject("제목을 입력하세요");
            errors.rejectValue("title", "emptyTitle", "제목을 입력하세요");
        } else if (StringUtils.isEmpty(obj.getContent())) {
            // 2번째는 메세지 키값(errorCode), 없으면 3번째 내용을 출력(defaultMessage)
            errors.rejectValue("content", "emptyContent", "내용을 입력하세요");
        }
    }
}

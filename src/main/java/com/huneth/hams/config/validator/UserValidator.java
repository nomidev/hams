package com.huneth.hams.config.validator;

import com.huneth.hams.member.dto.UserDto;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Member Validator
 */
@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDto.class.equals(aClass);
    }

    /**
     * 비밀번호와 확인 비밀번호 일치하지 않는지 확인.
     * @param o
     * @param errors
     */
    @Override
    public void validate(Object o, Errors errors) {
        UserDto obj = (UserDto) o;

        if (!obj.getPassword().equals(obj.getPasswordChk())) {
            errors.rejectValue("passwordChk", "notEquals", "비밀번호가 다릅니다.");
        }

    }
}

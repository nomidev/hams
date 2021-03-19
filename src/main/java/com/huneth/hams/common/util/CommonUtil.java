package com.huneth.hams.common.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public class CommonUtil {

    /**
     * Entity -> DTO 변환
     * @param <S>
     * @param <T>
     * @param modelMapper
     * @param source
     * @param targetClass
     * @return
     */
    // Generic Method에 Generic 파라미터가 있을 경우 리턴타입 앞에 <S, T>를 넣어줘야 한다.
    public static <S, T> List<T> mapList(ModelMapper modelMapper, List<S> source, Class<T> targetClass) {
        return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
    }

}

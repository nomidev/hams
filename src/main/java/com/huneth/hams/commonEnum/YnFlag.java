package com.huneth.hams.commonEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum YnFlag {
    N(0, false),
    Y(1, true);

    int statusCode;
    boolean status;
}

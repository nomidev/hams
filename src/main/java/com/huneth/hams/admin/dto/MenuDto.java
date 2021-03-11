package com.huneth.hams.admin.dto;

import lombok.Data;

@Data
public class MenuDto {

    private int id;
    private String parent;
    private String text;
    private String menuName;
    private String menuDesc;
    private String menuUrl;
    private String menuIcon;
    private int menuLevelNo;
    private int sortOrderNo;
    private Boolean useFlag;
}

package com.huneth.hams.admin.dto;

import com.huneth.hams.common.commonEnum.YnFlag;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class MenuDto {

    private int id;
    private String parent;
    private String text;
    private String menuName;
    private String menuDesc;
    private String menuRole;
    private String menuUrl;
    private String menuActiveCode;
    private String menuIcon;
    private int menuLevelNo;
    private int sortOrderNo;
    private YnFlag useFlag;
}

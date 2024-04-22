package com.huneth.hams.model;

import com.huneth.hams.commonEnum.YnFlag;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * 메뉴 관리 Entity
 */

@Data
@Entity
public class Menu extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String parentId;

    @NotBlank
    private String menuName;
    private String menuDesc;

    @NotBlank
    private String menuRole;

    @NotBlank
    private String menuUrl;

    private String menuActiveCode;
    private int menuLevelNo;
    private int sortOrderNo;

    private String menuIcon;

    @Enumerated(EnumType.ORDINAL)
    private YnFlag useFlag;

}

package com.huneth.hams.model;

import com.huneth.hams.commonEnum.YnFlag;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CommonCode extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String codeType;
    private String code;
    private String codeName;
    private String codeDesc;
    private String parentCode;

    @Enumerated(EnumType.ORDINAL)
    private YnFlag useFlag;
    private int sortNo;

    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;

}

package com.huneth.hams.common.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommonCodeDto {
    private int id;
    private String codeType;
    private String commonCode;
    private String codeName;
    private String codeDesc;
    private String parentCode;
    private Boolean userFlag;
    private int sortNo;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    private int createdBy;
    private Timestamp creationDate;
    private Timestamp lastUpdateDate;
}

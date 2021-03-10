package com.huneth.hams.admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class BulletinDto {

    private int id;
    private String title;
    private String type;
    private Boolean useFlag;
    private int userId;
    private String userMemberName;
    private Timestamp creationDate;
    private Timestamp lastUpdateDate;

}

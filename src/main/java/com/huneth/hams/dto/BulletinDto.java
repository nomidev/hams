package com.huneth.hams.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huneth.hams.commonEnum.YnFlag;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class BulletinDto {

    private int id;
    private String title;
    private String type;
    private YnFlag useFlag;
    private int userId;
    private String userMemberName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp creationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp lastUpdateDate;

}

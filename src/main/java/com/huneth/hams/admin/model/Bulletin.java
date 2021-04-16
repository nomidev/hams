package com.huneth.hams.admin.model;

import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huneth.hams.common.commonEnum.YnFlag;
import com.huneth.hams.common.model.BaseEntity;
import com.huneth.hams.member.model.User;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

/**
 * 게시판 관리 Entity
 */

@Data
@Entity
public class Bulletin extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String title;
    
    @NotBlank
    private String type;

    @Enumerated(EnumType.ORDINAL)
    private YnFlag useFlag;

}

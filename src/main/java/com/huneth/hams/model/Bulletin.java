package com.huneth.hams.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.huneth.hams.commonEnum.YnFlag;

import lombok.Data;

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

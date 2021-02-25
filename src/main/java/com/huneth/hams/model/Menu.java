package com.huneth.hams.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 메뉴 관리 Entity
 */

@Data
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int highMenuId;
    private String menuName;
    private String menuDesc;
    private int programId;
    private String menuUrl;
    private int menuLevelNo;
    private int sortOrderNo;
    private Boolean useFlag;

    @CreatedBy
    private int createdBy;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime creationDate;

    @UpdateTimestamp
    private LocalDateTime lastUpdateDate;
}

package com.huneth.hams.admin.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    private String parentId;

    @NotBlank
    private String menuName;
    private String menuDesc;

    @NotBlank
    private String menuUrl;
    private int menuLevelNo;
    private int sortOrderNo;

    private String menuIcon;

    @Column(columnDefinition = "boolean default true")
    private Boolean useFlag;

    @CreatedBy
    private int createdBy;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime creationDate;

    @UpdateTimestamp
    private LocalDateTime lastUpdateDate;
}

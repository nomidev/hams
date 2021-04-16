package com.huneth.hams.admin.model;

import com.huneth.hams.common.commonEnum.YnFlag;
import com.huneth.hams.common.model.BaseEntity;
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

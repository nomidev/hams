package com.huneth.hams.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * 권한 관리 Entity
 */

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String roleName;

    @Enumerated(EnumType.STRING)
    private RoleType roleCode;

    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoleList;

    private int createdBy;

    @CreationTimestamp
    private Timestamp creationDate;

    @UpdateTimestamp
    private Timestamp lastUpdateDate;
}

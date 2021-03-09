package com.huneth.hams.admin.model;

import java.sql.Timestamp;

import javax.persistence.*;

import com.huneth.hams.member.model.User;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * 게시판 관리 Entity
 */

@Data
@Entity
public class Bulletin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String type;
    private Boolean useFlag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdBy")
    private User user;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp creationDate;

    @UpdateTimestamp
    private Timestamp lastUpdateDate;

}

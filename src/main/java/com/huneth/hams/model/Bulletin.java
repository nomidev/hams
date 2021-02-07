package com.huneth.hams.model;

import java.sql.Timestamp;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Bulletin
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
    @JoinColumn(name = "createdBy", referencedColumnName = "userId")
    private User user;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp creationDate;

    @UpdateTimestamp
    private Timestamp lastUpdateDate;

}

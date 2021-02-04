package com.huneth.hams.model;

import java.sql.Timestamp;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
public class Bulletin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String boardId;
    private String boardTitle;
    private String boardTypeCode;

    @ManyToOne
    @JoinColumn(name = "createdBy", referencedColumnName = "userId")
    private User user;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp creationDate;

    @UpdateTimestamp
    private Timestamp lastUpdateDate;

}

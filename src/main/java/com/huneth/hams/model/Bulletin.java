package com.huneth.hams.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class Bulletin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String boardId;
    private String boardTitle;
    private String boardTypeCode;
    private int createdBy;

    @CreationTimestamp
    private Timestamp creationDate;

    private int lastUpdatedBy;

    @CreationTimestamp
    private Timestamp lastUpdateDate;

}

package com.huneth.hams.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.sql.Timestamp;

public class Academy {

    private int id;
    private String academyName;
    private int createdBy;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp creationDate;

    @UpdateTimestamp
    private Timestamp lastUpdateDate;
}

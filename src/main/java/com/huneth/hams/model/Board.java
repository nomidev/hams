package com.huneth.hams.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //strategy 전략
    private int id;

    private String title;
    private String content;

    @ColumnDefault("-1")
    private Integer createdBy;
    private Timestamp creationDate;

    @ColumnDefault("-1")
    private Integer lastUpdatedBy;
    private Timestamp lastUpdateDate;

}

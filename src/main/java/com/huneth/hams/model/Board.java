package com.huneth.hams.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //strategy 전략
    private int id;

    @NotNull
    @Size(min = 1, max = 30)
//    @Size(min = 1, max = 30, message = "") 메세지도 입력가능
    private String title;

    @NotNull
    private String content;

    @ColumnDefault("-1")
    private Integer createdBy;

    @CreationTimestamp
    private Timestamp creationDate;

    @ColumnDefault("-1")
    private Integer lastUpdatedBy;

    @CreationTimestamp
    private Timestamp lastUpdateDate;

}

package com.huneth.hams.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Board
 */
@Data
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //strategy 전략
    private int id;

    @ManyToOne
    private Bulletin bulletin;

    @NotNull
    @Size(min = 1, max = 30)
//    @Size(min = 1, max = 30, message = "") 메세지도 입력가능
    private String title;

    @NotNull
    private String content;

    private String attachedFile;
    private int hits;

    @ManyToOne
    @JoinColumn(name = "createdBy", referencedColumnName = "userId")
    private User user;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp creationDate;

    @UpdateTimestamp
    private Timestamp lastUpdateDate;

}

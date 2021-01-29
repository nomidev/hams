package com.huneth.hams.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
// @DynamicInsert //null은 제외하고
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int userId;

    private String userName;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50, unique = true)
    private String emailAddress;

    @Column(length = 30)
    private String phoneNumber;

    @Column(length = 50)
    private String affiliation; //소속

    @Column(length = 50)
    private String licenseNumber; //면허번호

    @Column(length = 30)
    private String occupation; //직업

    //@ColumnDefault("USER") //널인 경우 들어가지 않고 jpa는 인서트를 시켜서 사용하지 않는다.
    @Enumerated(EnumType.STRING)
    @ColumnDefault(value = "USER")
    private RoleType role;

    private Boolean enabled;

    private int createdBy;

    @CreationTimestamp
    private Timestamp creationDate;

    private int lastUpdatedBy;

    @CreationTimestamp
    private Timestamp lastUpdateDate;
}


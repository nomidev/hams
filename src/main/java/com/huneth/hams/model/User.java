package com.huneth.hams.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

//@Table(name = "users")
@Data
// @NoArgsConstructor
// @AllArgsConstructor
// @DynamicInsert //null은 제외하고
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int userId;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    @Column(length = 100)
    private String password;

    @NotNull
    private String memberName;

    @NotNull
    @Column(length = 30)
    private String phoneNumber;

    @NotNull
    @Email
    @Column(length = 50, unique = true)
    private String email;

    @Column(length = 50)
    private String affiliation; //소속

    @Column(length = 50)
    private String licenseNumber; //면허번호

    @Column(length = 30)
    private String occupation; //직업

    //@ColumnDefault("USER") //널인 경우 들어가지 않고 jpa는 인서트를 시켜서 사용하지 않는다.
    /*@Enumerated(EnumType.STRING)
    @ColumnDefault(value = "'ROLE_USER'")
    private RoleType role;*/

    @Enumerated(EnumType.STRING)
    private RoleType role;

    private Boolean enabled;

    @CreationTimestamp
    private Timestamp creationDate;

}


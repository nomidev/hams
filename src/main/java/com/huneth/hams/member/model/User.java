package com.huneth.hams.member.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 사용자 Entity
 */

//@Table(name = "users")
@Data
// @NoArgsConstructor
// @AllArgsConstructor
// @DynamicInsert //null은 제외하고
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int id;

//    @NotNull // null 체크
//    @NotEmpty // null, "" 체크
    @NotBlank // null, "", " " 체크
    @Column(unique = true)
    private String username;

    @NotBlank
    @Column(length = 100)
    private String password;

    @NotBlank
    private String memberName;

    @NotBlank
    @Column(length = 30)
    private String phoneNumber;

    @NotBlank
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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserRole> userRoles = new ArrayList<>();

    private Boolean enabled;

    @CreationTimestamp
    private Timestamp creationDate;

}


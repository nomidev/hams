package com.huneth.hams.member.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

/**
 * 사용자 Entity
 */

//@Table(name = "users")
@Data
// @NoArgsConstructor
// @AllArgsConstructor
// @DynamicInsert //null은 제외하고
// 유효성 관련 annotaion은 제외하고 컬럼 설정만 한다.
// 유효성 체크는 UserDto에서 한다.
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int id;

    // @NotNull // null 체크 유효성 검사
    // @NotEmpty // null, "" 체크 유효성 검사
    // @NotNull // null, "", " " 체크 유효성 검사
    // nullable = false는 ddl에 not null만 적용한다.
    @Column(unique = true, nullable = false)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(nullable = false)
    private String memberName;

    @Column(length = 30, nullable = false)
    private String phoneNumber;

    @Column(length = 50, unique = true)
    private String email;

    @Column(length = 50)
    private String affiliation; //소속

    @Column(length = 50)
    private String licenseNumber; //면허번호

    @Column(length = 30)
    private String occupation; //직업

    // @ColumnDefault("USER") //널인 경우 들어가지 않고 jpa는 인서트를 시켜서 사용하지 않는다.
    /* @Enumerated(EnumType.STRING)
    @ColumnDefault(value = "'ROLE_USER'")
    private RoleType role; */

    // @ManyToOne, @OneToOne과 같이 @XXXToOne 어노테이션들은 기본이 즉시 로딩(EAGER) 이다.
    // 꼭 LAZY로 명시적으로 설정해서 사용하자
    // @OneToMany와 @ManyToMany는 기본이 지연 로딩(LAZY)이다.

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserRole> userRoles = new ArrayList<>();

    @Column(columnDefinition = "boolean default true")
    private Boolean enabled;

    @CreationTimestamp
    private Timestamp creationDate;

}


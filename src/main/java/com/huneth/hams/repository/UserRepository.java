package com.huneth.hams.repository;

import com.huneth.hams.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

// Users 모델에 Id를 제네렉으로 넣어준다.
// Dao?
// 자동으로 IOC 등록 됨
// @Repository 생략가능 컴포넌트 스캔할때 자동으로 올라온다(IoC). JpaRepository를 상속했기 때문
// CRUD 함수를 JpaRepository가 들고 있다.
public interface UserRepository extends JpaRepository<User, Integer> {

    // findBy...까지는 규칙임
    public User findByUsername(String username); // Jpa Query Method findBy... 로 시작한다.
}

package com.huneth.hams.repository;

import com.huneth.hams.model.Users;

import org.springframework.data.jpa.repository.JpaRepository;

//Users 모델에 Id를 제네렉으로 넣어준다.
// Dao?
// 자동으로 IOC 등록 됨
//@Repository 생략가능 컴포넌트 스캔할때 자동으로 올라온다.
public interface UserRepository extends JpaRepository<Users, Integer> {
}

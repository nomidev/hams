package com.huneth.hams.admin.service;

import com.huneth.hams.model.CommonCode;
import com.huneth.hams.model.QCommonCode;
import com.huneth.hams.commonEnum.YnFlag;
import com.huneth.hams.repository.BoardRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@DataJpaTest
// 실제 DB를 사용하고 싶다면 아래 설정 추가 (기본은 내장 H2)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MyRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void saveTest() {
        // Given, When, Then 로직
    }
}
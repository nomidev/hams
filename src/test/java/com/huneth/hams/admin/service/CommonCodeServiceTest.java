package com.huneth.hams.admin.service;

import com.huneth.hams.admin.model.CommonCode;
import com.huneth.hams.admin.model.QCommonCode;
import com.huneth.hams.common.commonEnum.YnFlag;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

// Jpa테스트
@DataJpaTest
// 메모리DB 사용안함
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class CommonCodeServiceTest {

    // JPA 영속성 컨텍스트
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void 공통코드_queryDsl_테스트() {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QCommonCode qCommonCode = QCommonCode.commonCode;

        CommonCode commonCode = new CommonCode();

        YnFlag useFlag = YnFlag.Y;
        commonCode.setUseFlag(useFlag);

        log.info("useFlag" + commonCode.getUseFlag());

        // int useFlag = YnFlag.Y.getUseFlag();
        // log.info(YnFlag.valueOf(YnFlag.Y.getUseFlag()));

        List<CommonCode> commonCodeList = jpaQueryFactory.selectFrom(qCommonCode)
                .where(qCommonCode.useFlag.eq(useFlag))
                .fetch();

        log.info("commonCodeList : " + commonCodeList);
    }
}
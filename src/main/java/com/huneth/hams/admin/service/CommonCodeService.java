package com.huneth.hams.admin.service;

import com.huneth.hams.admin.model.CommonCode;
import com.huneth.hams.admin.model.QCommonCode;
import com.huneth.hams.common.commonEnum.YnFlag;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommonCodeService {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public List<CommonCode> retrieveCommonCodeList() {
        QCommonCode qCommonCode = QCommonCode.commonCode;

        List<CommonCode> commonCodeList = jpaQueryFactory.selectFrom(qCommonCode)
                        .where(qCommonCode.useFlag.eq(YnFlag.Y))
                        .orderBy(qCommonCode.code.desc())
                        .fetch();

        return commonCodeList;
    }
}

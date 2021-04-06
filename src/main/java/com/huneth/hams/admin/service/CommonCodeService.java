package com.huneth.hams.admin.service;

import com.huneth.hams.admin.model.CommonCode;
import com.huneth.hams.admin.model.QCommonCode;
import com.huneth.hams.admin.repository.CommonCodeRepository;
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

    @Autowired
    private CommonCodeRepository commonCodeRepository;

    public List<CommonCode> retrieveCommonCodeList() {
        QCommonCode qCommonCode = QCommonCode.commonCode;

        List<CommonCode> commonCodeList = jpaQueryFactory.selectFrom(qCommonCode)
                        .where(qCommonCode.useFlag.eq(YnFlag.Y))
                        .orderBy(qCommonCode.code.desc())
                        .fetch();

        return commonCodeList;
    }

    /**
     * 공통코드 추가
     * @param commonCode
     */
    public void saveCommonCode(CommonCode commonCode) {
        commonCodeRepository.save(commonCode);
    }

    /**
     * 공통코드 수정
     * @param commonCode
     */
    public void updateCommonCode(CommonCode commonCode) {
        commonCodeRepository.save(commonCode);
    }

    /**
     * 공통코드 삭제
     * @param commonCode
     */
    public void deleteCommonCode(CommonCode commonCode) {
        commonCodeRepository.delete(commonCode);
    }
}

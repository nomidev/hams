package com.huneth.hams.service;

import com.huneth.hams.model.CommonCode;
import com.huneth.hams.model.QCommonCode;
import com.huneth.hams.repository.CommonCodeRepository;
import com.huneth.hams.commonEnum.YnFlag;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class CommonCodeService {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private CommonCodeRepository commonCodeRepository;

    private final QCommonCode qCommonCode = QCommonCode.commonCode;

    private BooleanExpression eqUseFlag(YnFlag ynFlag) {
        if (ynFlag == null) {
            return null;
        }
        return qCommonCode.useFlag.eq(ynFlag);
    }

    private BooleanExpression eqCodeType(String codeType) {
        if (StringUtils.isEmpty(codeType)) {
            return null;
        }
        return qCommonCode.codeType.eq(codeType);
    }

    private BooleanExpression eqCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        return qCommonCode.code.eq(code);
    }

    private BooleanExpression eqCodeName(String codeName) {
        if (StringUtils.isEmpty(codeName)) {
            return null;
        }
        return qCommonCode.codeName.eq(codeName);
    }

    public List<CommonCode> retrieveCommonCode(CommonCode param) {
        List<CommonCode> commonCodeList = jpaQueryFactory.selectFrom(qCommonCode)
                .where(eqUseFlag(param.getUseFlag())
                        ,eqCodeType(param.getCodeType()))
                .orderBy(qCommonCode.codeType.asc(), qCommonCode.sortNo.asc())
                .fetch();

        return commonCodeList;
    }

    public List<CommonCode> retrieveCommonCodeList(CommonCode param, Pageable pageable) {
        List<CommonCode> commonCodeList = jpaQueryFactory.selectFrom(qCommonCode)
                        .where(eqUseFlag(param.getUseFlag())
                                ,eqCodeType(param.getCodeType())
                                ,eqCode(param.getCode())
                                ,eqCodeName(param.getCodeName()))
                        .orderBy(qCommonCode.codeType.asc(), qCommonCode.sortNo.asc())
                        .limit(pageable.getPageSize())
                        .offset(pageable.getOffset() - pageable.getPageSize()) // 0부터 시작하기 때문에...?
                        .fetch();

        return commonCodeList;
    }

    /**
     * Total count
     * @param param
     * @return
     */
    public Long retrieveTotalCount(CommonCode param) {
        JPAQuery<CommonCode> jpaQuery = jpaQueryFactory.selectFrom(qCommonCode)
                .where(eqUseFlag(param.getUseFlag())
                        ,eqCodeType(param.getCodeType())
                        ,eqCode(param.getCode())
                        ,eqCodeName(param.getCodeName()));

        return jpaQuery.fetchCount();
    }

    /**
     * 공통코드 추가
     * @param param
     */
    public void saveCommonCode(CommonCode param) {
        log.info("param = " + param);
        commonCodeRepository.save(param);
    }

    /**
     * 공통코드 수정
     * @param param
     */
    public void updateCommonCode(CommonCode param) {
        log.info("param = " + param);
        commonCodeRepository.save(param);
    }

    /**
     * 공통코드 삭제
     * @param param
     */
    public void deleteCommonCode(CommonCode param) {
        log.info("param = " + param);
        commonCodeRepository.delete(param);
    }
}

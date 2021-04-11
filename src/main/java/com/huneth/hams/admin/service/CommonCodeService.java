package com.huneth.hams.admin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huneth.hams.admin.model.CommonCode;
import com.huneth.hams.admin.model.QCommonCode;
import com.huneth.hams.admin.repository.CommonCodeRepository;
import com.huneth.hams.common.commonEnum.YnFlag;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class CommonCodeService {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private CommonCodeRepository commonCodeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CommonCode> retrieveCommonCodeList() {
        QCommonCode qCommonCode = QCommonCode.commonCode;

        List<CommonCode> commonCodeList = jpaQueryFactory.selectFrom(qCommonCode)
                        .where(qCommonCode.useFlag.eq(YnFlag.Y))
                        .orderBy(qCommonCode.codeType.asc(), qCommonCode.sortNo.asc())
                        .fetch();

        return commonCodeList;
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

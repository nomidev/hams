package com.huneth.hams.service;

import com.huneth.hams.model.Bulletin;
import com.huneth.hams.model.QBulletin;
import com.huneth.hams.repository.BulletinRepository;
import com.huneth.hams.commonEnum.YnFlag;
import com.huneth.hams.repository.UserRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class BulletinService {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private BulletinRepository bulletinRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final QBulletin qBulletin = QBulletin.bulletin;

    private BooleanExpression eqUseFlag(YnFlag ynFlag) {
        if (ynFlag == null) {
            return null;
        }
        return qBulletin.useFlag.eq(ynFlag);
    }

    private BooleanExpression eqTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            return null;
        }
        return qBulletin.title.eq(title);
    }

    private BooleanExpression eqType(String type) {
        if (StringUtils.isEmpty(type)) {
            return null;
        }
        return qBulletin.type.eq(type);
    }

    public List<Bulletin> retrieveBulletinList(Bulletin param, Pageable pageable) {
        List<Bulletin> bulletinList = jpaQueryFactory.selectFrom(qBulletin)
                        .where(eqUseFlag(param.getUseFlag())
                                ,eqTitle(param.getTitle())
                                ,eqType(param.getType()))
                        .orderBy(qBulletin.id.asc())
                        .limit(pageable.getPageSize())
                        .offset(pageable.getOffset() - pageable.getPageSize()) // 0부터 시작하기 때문에...?
                        .fetch();

        // PropertyMap<Bulletin, BulletinDto> menuMap = new PropertyMap<Menu, MenuDto>() {
        //     protected void configure() {
        //         map().setText(source.getMenuName());
        //     }
        // };
        // TypeMap<Bulletin, BulletinDto> typeMap = modelMapper.getTypeMap(Bulletin.class, BulletinDto.class);
        //
        // if (typeMap == null) {
        //     modelMapper.addMappings(menuMap);
        // }

        return bulletinList;
    }

    /**
     * Total count
     * @param param
     * @return
     */
    public Long retrieveTotalCount(Bulletin param) {
        JPAQuery<Bulletin> jpaQuery = jpaQueryFactory.selectFrom(qBulletin)
                .where(eqUseFlag(param.getUseFlag())
                        ,eqTitle(param.getTitle())
                        ,eqType(param.getType()));

        return jpaQuery.fetchCount();
    }

    /**
     * 공통코드 추가
     * @param bulletin
     */
    public void saveBulletin(Bulletin bulletin) {
        log.info("param = " + bulletin);
        bulletinRepository.save(bulletin);
    }

    /**
     * 공통코드 수정
     * @param bulletin
     */
    public void updateBulletin(Bulletin bulletin) {
        log.info("param = " + bulletin);
        bulletinRepository.save(bulletin);
    }

    /**
     * 공통코드 삭제
     * @param bulletin
     */
    public void deleteBulletin(Bulletin bulletin) {
        log.info("param = " + bulletin);
        bulletinRepository.delete(bulletin);
    }
}

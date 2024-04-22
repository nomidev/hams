package com.huneth.hams.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// queryDsl을 사용하기 위한 Configuration
@Configuration
public class JpaQueryFactoryConfig {

    @PersistenceContext
    private EntityManager entityManager;

    // Configuration에 등록된 Bean은 IoC되어 DI로 사용할 수 있다.
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    // Entity -> DTO 변환 ModelMapper Bean 등록
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

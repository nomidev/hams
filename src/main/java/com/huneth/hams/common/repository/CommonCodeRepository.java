package com.huneth.hams.common.repository;

import com.huneth.hams.common.model.CommonCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommonCodeRepository extends JpaRepository<CommonCode, Integer> {

    List<CommonCode> findByCodeTypeOrderBySortNoAsc(String codeType);
}

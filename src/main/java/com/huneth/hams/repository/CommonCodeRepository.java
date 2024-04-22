package com.huneth.hams.repository;

import com.huneth.hams.model.CommonCode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommonCodeRepository extends JpaRepository<CommonCode, Integer> {

    List<CommonCode> findByCodeTypeOrderBySortNoAsc(String codeType);

    List<CommonCode> findByCodeDescOrCodeTypeOrCodeOrderByCodeAsc(
            String codeDesc, String codeType, String commonCode, Pageable pageable);
}

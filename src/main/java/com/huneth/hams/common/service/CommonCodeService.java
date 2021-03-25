package com.huneth.hams.common.service;

import com.huneth.hams.common.dto.CommonCodeDto;
import com.huneth.hams.common.repository.CommonCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CommonCodeService {

    @Autowired
    private CommonCodeRepository commonCodeRepository;

    public List<CommonCodeDto> retrieveCommonCodeList(@ModelAttribute CommonCodeDto commonCodeDto) {



        // commonCodeRepository.findByCodeDescOrCodeTypeOrCommonCodeOrderByCommonCodeAsc();
        return new ArrayList<CommonCodeDto>();
    }
}

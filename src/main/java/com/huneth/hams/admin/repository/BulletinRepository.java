package com.huneth.hams.admin.repository;

import com.huneth.hams.admin.model.Bulletin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BulletinRepository extends JpaRepository<Bulletin, Integer> {

    // board_title like ''
    Page<Bulletin> findByTitleContaining(String title, Pageable pageable);

}

package com.huneth.hams.repository;

import com.huneth.hams.model.Bulletin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BulletinRepository extends JpaRepository<Bulletin, Integer> {

    Page<Bulletin> findByBoardTitle(String title, Pageable pageable);
}

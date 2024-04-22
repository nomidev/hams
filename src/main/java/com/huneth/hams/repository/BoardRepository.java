package com.huneth.hams.repository;

import com.huneth.hams.model.Board;
import com.huneth.hams.model.Bulletin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 생략가능하다
public interface BoardRepository extends JpaRepository<Board, Integer> { // board는 모델, integer는 board의 PK(ID)

    // 게시판 검색
    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

    // A AND (B OR C) == (A AND B) OR (A AND C)
    Page<Board> findByBulletinAndTitleContainingOrBulletinAndContentContaining(
            Bulletin bulletin, String title, Bulletin bulletin2, String content, Pageable pageable);

}

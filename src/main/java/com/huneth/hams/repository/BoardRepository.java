package com.huneth.hams.repository;

import com.huneth.hams.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 생략가능하다
public interface BoardRepository extends JpaRepository<Board, Integer> { // board는 모델, integer는 board의 PK(ID)

}

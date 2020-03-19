package jr.kings.webtoon.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jr.kings.webtoon.domain.Board;

/**
 * BoardRepository
 */

public interface BoardRepository extends JpaRepository<Board, Integer> {

    // 리스트 화면 // 게시글 등록 member id로 하는지 아니면 nickname으로 하는지...
    @Query(value = "SELECT b, m, a, count(r) FROM Board b JOIN b.member m LEFT JOIN b.attachList a LEFT JOIN b.replyList r GROUP BY b ORDER BY b.boardno DESC"
            ,countQuery = "SELECT count(b) FROM Board b")
    public List<Object[]> getBoardAttachReplyListPage(Pageable page);

    // 조회 화면 (첨부파일)
    @Query("SELECT b, m, a FROM Board b JOIN b.member m LEFT JOIN b.attachList a where b.boardno = :boardno")
    public List<Object[]> getBoardAttachReadPage(Integer boardno);

    // 조회 화면 (댓글)
    @Query("SELECT b, m, r FROM Board b JOIN b.member m LEFT JOIN b.replyList r where b.boardno = :boardno")
    public List<Object[]> getBoardReplyReadPage(Integer boardno);

    // 검색
    public Page<Board> findBytitleContaining(String title, Pageable page);


}
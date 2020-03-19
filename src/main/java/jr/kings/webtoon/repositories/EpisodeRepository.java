package jr.kings.webtoon.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jr.kings.webtoon.domain.Episode;

/**
 * EpisodeRepository
 */

public interface EpisodeRepository extends JpaRepository<Episode,Integer> {

    // 특정 웹툰 조회페이지
    // 에피소드, 웹툰 파일
    @Query("SELECT e, wf FROM Episode e LEFT JOIN e.webtoonFileList wf where e.eno = :eno")
    public List<Object[]> getEpisodeWebtoonFileList(Integer eno);

    // 에피소드, 댓글
    @Query(value = "SELECT e, r FROM Episode e LEFT JOIN e.replyList r where e.eno = :eno ORDER BY r.rno DESC"
            ,countQuery = "SELECT count(r) FROM Reply r where r.Episode.eno = :eno")
    public List<Object[]> getEpisodeReplyPage(Integer eno, Pageable page);

}
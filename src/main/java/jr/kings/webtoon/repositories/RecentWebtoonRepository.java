package jr.kings.webtoon.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jr.kings.webtoon.domain.RecentWebtoon;

/**
 * RecentWebtoonRepository
 */

public interface RecentWebtoonRepository extends JpaRepository<RecentWebtoon, Integer>{

    // @Query("INSERT INTO RecentWebtoon (RecentWebtoon.episode.eno, RecentWebtoon.member.id) values (RecentWebtoon.episode.eno : 1, RecentWebtoon.member.id : rnjsdndrl)")
    // public void insertRecent();

    // 최근본웹툰 : 리센트웹툰, 에피소드, 웹툰
    @Query(value = "SELECT rw, e, w FROM RecentWebtoon rw LEFT JOIN rw.episode e LEFT JOIN e.webtoon w where rw.member.id = :id ORDER BY rw.recentno DESC"
            ,countQuery = "SELECT rw FROM RecentWebtoon rw where rw.member.id = :id")
    public List<Object[]> getRecentWebtoonEpisodeWebtoon(String id,Pageable page);
 
    // 최근본웹툰 : 리센트웹툰, 에피소드, 웹툰
    @Query(value = "SELECT w.thumbnailPath ,rw , e.subtitle FROM RecentWebtoon rw JOIN rw.episode e JOIN e.webtoon w where rw.member.id = :id")
    public List<Object[]> getRecentWebtoonEpisodeWebtoon(String id);

}
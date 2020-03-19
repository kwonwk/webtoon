package jr.kings.webtoon.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jr.kings.webtoon.domain.Webtoon;

public interface WebtoonRepository extends JpaRepository<Webtoon, Integer>{

    // Main page
    // 오늘의 웹툰
    @Query(value = "SELECT w, avg(e.grade), g FROM Webtoon w JOIN w.episodeList e JOIN w.genreList g where w.updateDays =:updateDays GROUP BY w"
            ,countQuery = "SELECT count(w) FROM Webtoon w where w.updateDays = :updateDays")
    public List<Object[]> getWebtoonEpisodeGenreByTodayList(String updateDays, Pageable page);

    // 신작 웹툰
    // 서브쿼리 사용해서 regdate DESC 정렬하고 10개 가져오기
    // 우선 다 가져오고 자바스크립트로 10개 찍도록
    @Query(value = "SELECT w, avg(e.grade), g FROM Webtoon w JOIN w.episodeList e JOIN w.genreList g GROUP BY w ORDER BY w.regdate DESC"
            ,countQuery = "SELECT count(w) FROM Webtoon w")
    public List<Object[]> getWebtoonEpisodeGenreByNewList(Pageable page);

    // 인기작
    // 서브쿼리 사용해서 avg(e.grade) DESC 정렬하고 10개 가져오기
    @Query(value = "SELECT w, avg(e.grade), g FROM Webtoon w JOIN w.episodeList e JOIN w.genreList g GROUP BY w ORDER BY avg(e.grade) DESC"
            ,countQuery = "SELECT count(w) FROM Webtoon w")
    public List<Object[]> getWebtoonEpisodeGenreByPopularList(Pageable page);

    // 요일별, 장르별, top100 
    // 전체 데이터 가져오면 된다.
    @Query("SELECT w, avg(e.grade), g FROM Webtoon w JOIN w.episodeList e JOIN w.genreList g GROUP BY w")
    public List<Object[]> getWebtoonEpisodeGenreList();

    //검색 Query
    @Query("SELECT w FROM Webtoon w JOIN w.genreList g WHERE g.genre "
    +"LIKE CONCAT('%',:search ,'%') OR w.title LIKE CONCAT('%',:search ,'%') OR w.author LIKE CONCAT('%',:search ,'%')")
    public List<Object> getSearchList(String search);


    // 웹툰 리스트 페이지
    @Query(value = "SELECT w, e, avg(e.grade), count(i) FROM Webtoon w LEFT JOIN w.episodeList e LEFT JOIN w.interestedList i where w.wno = :wno GROUP BY e ORDER BY e.eno DESC"
            ,countQuery = "SELECT count(e) FROM Episode e where w.wno = :wno")
    public List<Object[]> getWebtoonEpisodeInterestedList(Integer wno, Pageable page);

}
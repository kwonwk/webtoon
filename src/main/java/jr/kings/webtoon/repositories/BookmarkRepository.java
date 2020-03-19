package jr.kings.webtoon.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jr.kings.webtoon.domain.Bookmark;

/**
 * BookmarkRepository
 */

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer>{
 
    @Query("SELECT w.thumbnailPath, w.title, e.subtitle, b.regdate, b.bno FROM Webtoon w INNER JOIN w.episodeList e INNER JOIN e.bookmarkList b WHERE b.member.id = :id")
    public List<Object[]> getBookMark(String id);

    //웹툰 에피소드 북마크
    @Query(value = "SELECT b, w, e FROM Bookmark b LEFT JOIN b.episode.webtoon w LEFT JOIN b.episode e where b.member.id = :id ORDER BY bno DESC"
            ,countQuery = "SELECT count(b) FROM Bookmark b where b.member.id = :id")
    public List<Object[]> getBookmarkWebtoonEpisodeList(String id,Pageable page);

}
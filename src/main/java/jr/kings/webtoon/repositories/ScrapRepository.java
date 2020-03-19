package jr.kings.webtoon.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jr.kings.webtoon.domain.Scrap;

/**
 * ScrapRepository
 */

public interface ScrapRepository extends JpaRepository<Scrap, Integer> {

    // 스크랩, 앨범
    @Query(value = "SELECT s, af FROM Scrap s LEFT JOIN s.albumFileList af where s.albumno = :albumno ORDER BY af.afno"
            ,countQuery = "SELECT count(s.albumFileList) FROM Scrap s where s.albumno = :albumno")
    public List<Object[]> getScrapAlbumList(Integer albumno, Pageable page);

//     @Query(value = "SELECT s, af FROM Scrap s LEFT JOIN s.albumFileList af where s.albumno = :albumno ORDER BY af.afno")
//     public List<Object[]> getScrapAlbumList(Integer albumno);

    @Query("SELECT s FROM Scrap s where s.albumName = :albumName")
    public Scrap getScrap(String albumName);

    @Query("SELECT s FROM Scrap s where s.member.id = :id")
    public List<Scrap> getAlbumList(String id);

    @Query(value = "SELECT s FROM Scrap s where s.member.id = :id ORDER BY s.albumno DESC"
            ,countQuery = "SELECT count(s) FROM Scrap s where s.member.id = :id")
            public List<Scrap> getAlbumList(String id, Pageable page);
}
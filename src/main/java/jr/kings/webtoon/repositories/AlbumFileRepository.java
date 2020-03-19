package jr.kings.webtoon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jr.kings.webtoon.domain.AlbumFile;

/**
 * AlbumFileRepository
 */
public interface AlbumFileRepository extends JpaRepository<AlbumFile, Integer> {

    @Query(value = "SELECT a FROM AlbumFile a WHERE a.scrap.albumno = :albumno")
    public List<AlbumFile> getAlbumFiles(Integer albumno);
}

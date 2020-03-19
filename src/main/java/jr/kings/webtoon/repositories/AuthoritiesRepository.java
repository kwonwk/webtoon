package jr.kings.webtoon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import jr.kings.webtoon.domain.Authorities;

/**
 * AlbumFileRepository
 */
public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {

}
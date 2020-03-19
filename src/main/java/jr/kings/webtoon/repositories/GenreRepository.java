package jr.kings.webtoon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import jr.kings.webtoon.domain.Genre;

/**
 * GenreRepository
 */

public interface GenreRepository extends JpaRepository<Genre,Integer>{
  
}
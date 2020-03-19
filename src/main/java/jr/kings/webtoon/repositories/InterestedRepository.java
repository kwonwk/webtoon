package jr.kings.webtoon.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jr.kings.webtoon.domain.Interested;

/**
 * InterestedRepository
 */

public interface InterestedRepository extends JpaRepository<Interested, Integer>{

    @Query(value = "SELECT i, w FROM Interested i LEFT JOIN i.webtoon w where i.member.id = :id ORDER BY ino DESC"
            ,countQuery = "SELECT count(i) FROM Interested i where i.member.id = :id")
    public List<Object[]> getInterestedWebtoon(String id, Pageable page);

}
package jr.kings.webtoon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import jr.kings.webtoon.domain.Alarm;

/**
 * AlarmRepository
 */
public interface AlarmRepository extends JpaRepository<Alarm, Integer>{


}
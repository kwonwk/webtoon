package jr.kings.webtoon.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jr.kings.webtoon.domain.Member;

/**
 * MemberRepository
 */

public interface MemberRepository extends JpaRepository<Member, Integer> {

      @Query("SELECT m FROM Member m where m.id = :id")
      public Member getMember(String id);

      // 알람, 멤버
      @Query(value = "SELECT m, a FROM Member m LEFT JOIN m.alarmList a where m.id = :id ORDER BY a.ano DESC", countQuery = "SELECT count(m.alarmList) FROM Member m where m.id = :id")
      public List<Object[]> getMemberAlarm(String id, Pageable page);

      @Query(value = "SELECT m, a FROM Member m LEFT JOIN m.authorities a WHERE m.id = :id")
      public List<Object[]> getMemberAuth(String id);

}
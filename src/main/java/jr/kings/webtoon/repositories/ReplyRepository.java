package jr.kings.webtoon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import jr.kings.webtoon.domain.Reply;

/**
 * ReplyRepository
 */
public interface ReplyRepository extends JpaRepository<Reply,Integer>{
    

}
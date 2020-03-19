package jr.kings.webtoon.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

/**
 * BoardService
 */

public interface BoardService {

    public List<Object[]> getBoardList(Pageable page);

    public List<Object[]> readBoardAttach(Integer boardno);

    public List<Object[]> readBoardReply(Integer boardno);

    
}
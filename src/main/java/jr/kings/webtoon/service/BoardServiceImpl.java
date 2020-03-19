package jr.kings.webtoon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jr.kings.webtoon.repositories.BoardRepository;

/**
 * BoardServiceImpl
 */

@Service
public class BoardServiceImpl implements BoardService {
    
    @Autowired
    private BoardRepository boardRepository;
    
    @Override
    public List<Object[]> getBoardList(Pageable page) {

        return boardRepository.getBoardAttachReplyListPage(page);

    }

    @Override
    public List<Object[]> readBoardAttach(Integer boardno) {
    
        return boardRepository.getBoardAttachReadPage(boardno);
    
    }

    @Override
    public List<Object[]> readBoardReply(Integer boardno) {

        return boardRepository.getBoardReplyReadPage(boardno);

    }


    
}
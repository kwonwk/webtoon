package jr.kings.webtoon.repositories;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import jr.kings.webtoon.domain.Attach;
import jr.kings.webtoon.domain.Board;
import jr.kings.webtoon.domain.Reply;
import jr.kings.webtoon.dto.SearchDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * BoardRepository
 */

@SpringBootTest
@Slf4j
public class BoardTests {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testInsertBoardWithAttach(){

        log.info("testInsertBoardWithAttach......");

        IntStream.range(0, 30).forEach(i->{

            Attach attach = new Attach();

            attach.setFileName("fileName..."+i);
            attach.setFilePath("filePath..."+i);
            attach.setUuid("uuid..."+i);

            Board board = new Board();

            board.setContent("content..."+i);
            board.setTitle("title..."+i);
            board.setMember(memberRepository.getMember("user"+i%5));   

            board.addAttach(attach);

            log.info(""+boardRepository.save(board));

        });
    }

    @Test
    public void testInsertReply(){

        log.info("testInsertReply......");

        IntStream.range(1, 10).forEach(i->{

            Reply reply = new Reply();

            reply.setComment("comment..."+i);
            reply.setBoard(boardRepository.getOne(i%5+1));
            reply.setMember(memberRepository.getMember("user"+(i%5+1)));

            log.info(""+replyRepository.save(reply));

        });
    }

    @Test
    public void testGetBoardAttachReply(){

        log.info("testGetBoardAttachReply......");

        Pageable page = PageRequest.of(0, 10);

        List<Object[]> result = boardRepository.getBoardAttachReplyListPage(page);

        result.forEach(i->{

            log.info("i[0]: "+i[0]);
            log.info("i[1]: "+i[1]);
            log.info("i[2]: "+i[2]);
            log.info("i[3]: "+i[3]);

        });

        log.info("result.size(): "+result.size());

    }

    @Test
    public void testGetBoardAttachReadPage(){

        log.info("testGetBoardAttachReadPage......");

        List<Object[]> result = boardRepository.getBoardAttachReadPage(1);

        result.forEach(i->{

            log.info("i[0]: "+i[0]);
            log.info("i[1]: "+i[1]);
            log.info("i[2]: "+i[2]);

        });

        log.info("result.size(): "+result.size());

    }
    
    @Test
    public void testGetBoardReplyReadPage(){

        log.info("testGetBoardReplyReadPage......");

        List<Object[]> result = boardRepository.getBoardReplyReadPage(1);

        result.forEach(i->{

            log.info("i[0]: "+i[0]);
            log.info("i[1]: "+i[1]);
            log.info("i[2]: "+i[2]);

        });

        log.info("result.size(): "+result.size());

    }

    // 검색 테스트
    @Test
    public void testSearchByTitle(){

        log.info("testSearchByTitle......");

        Pageable page = PageRequest.of(0, 10);

        Page<Board> result = boardRepository.findBytitleContaining("5", page);

        Stream<Board> stream = result.get();
        
        List<Board> list = stream.collect(Collectors.toList());

        log.info(""+list);

    }

    @Test
    public void testSearchTitle(){

        log.info("testSearchTitle......");

        SearchDTO dto = new SearchDTO();
        
        dto.setType("t");
        dto.setKeyword("5");

        // Pageable page = PageRequest.of(0, 10);

        // Page<Board> result = boardRepository.findAll(BoardSearchPredicate.searchSimple(dto));

    }

}
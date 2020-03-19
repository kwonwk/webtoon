package jr.kings.webtoon.repositories;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import jr.kings.webtoon.domain.Episode;
import jr.kings.webtoon.domain.Member;
import jr.kings.webtoon.domain.Reply;
import lombok.extern.slf4j.Slf4j;

/**
 * ReplyTests
 */
@SpringBootTest
@Slf4j
public class ReplyTests {

    @Autowired
    ReplyRepository replyRepository;

    @Test
    @Transactional
    @Commit
    public void testReplyInsert(){

        log.info("testReplyInsert.......");

        Member member = new Member();
        member.setId("user");
        Episode episode = new Episode();
        episode.setEno(1);
        IntStream.range(1, 35).forEach(i->{
            Reply reply = new Reply();
            reply.setComment("comment"+i);
            reply.setLikes(i);
            reply.setDislike(i+1);
            reply.setEpisode(episode);
            reply.setMember(member);
            replyRepository.save(reply);

        });
    }
}
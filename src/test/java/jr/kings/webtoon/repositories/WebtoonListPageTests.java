package jr.kings.webtoon.repositories;

import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;

import jr.kings.webtoon.domain.Episode;
import jr.kings.webtoon.domain.Reply;
import jr.kings.webtoon.domain.WebtoonFile;
import lombok.extern.slf4j.Slf4j;

/**
 * EpisodeTests
 */

@SpringBootTest
@Slf4j
public class WebtoonListPageTests {

    @Autowired
    private WebtoonRepository webtoonRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testWebtoonEpisodeInterested(){

        log.info("testWebtoonEpisodeInterested......");
    
        Pageable page = PageRequest.of(0, 10);

        List<Object[]> result = webtoonRepository.getWebtoonEpisodeInterestedList(1, page);


        result.forEach(i->{

            log.info("i[0]: "+i[0]);
            log.info("i[1]: "+i[1]);
            log.info("i[2]: "+i[2]);
            log.info("i[3]: "+i[3]);

        });

        log.info("result.size(): "+result.size());

    }

    @Test
    public void testInsertEpisodeWithWebtoonFile(){

        log.info("testAddEpisodeWithWebtoonFile.......");

        WebtoonFile w1 = new WebtoonFile();
        w1.setOcrjson("ocrjson1");

        WebtoonFile w2 = new WebtoonFile();
        w2.setOcrjson("ocrjson2");

        Episode episode = new Episode();
        episode.setSubtitle("subtitle...a");

        episode.addWebtoonFile(w1);
        episode.addWebtoonFile(w2);

        log.info(""+episodeRepository.save(episode));

    }

    @Test
    @Transactional
    @Commit
    public void testAddWebtoonFile(){

        log.info("testAddWebtoonFile.......");

        Episode episode = episodeRepository.getOne(73);

        WebtoonFile webtoonFile = new WebtoonFile();
        webtoonFile.setOcrjson("new ocrjson");

        episode.addWebtoonFile(webtoonFile);

        log.info(""+episodeRepository.save(episode));

    }

    @Test
    public void testInsertReply(){

        log.info("testInsertReply.......");

        IntStream.range(1, 11).forEach(i->{

            Reply reply = new Reply();
    
            reply.setComment("comment"+i);
            reply.setEpisode(episodeRepository.getOne(i));
            reply.setMember(memberRepository.getMember("user"+i));

            log.info(""+replyRepository.save(reply));

        });
    }

    @Test
    public void testGetEpisodeWebtoonFile(){

        log.info("testEpisodeWebtoonFile.......");

        List<Object[]> result = episodeRepository.getEpisodeWebtoonFileList(1);

        result.forEach(i->{

            log.info("i[0]: "+i[0]);
            log.info("i[1]: "+i[1]);

        });

        log.info("result.size(): "+result.size());

    }

    @Test
    public void testgetEpisdoeReplyPage(){

        log.info("testgetEpisdoeReplyPage......");

        Pageable page = PageRequest.of(0, 10);

        List<Object[]> result = episodeRepository.getEpisodeReplyPage(1,page);

        result.forEach(i->{

            log.info("i[0]: "+i[0]);
            log.info("i[1]: "+i[1]);

        });
    }
    
}
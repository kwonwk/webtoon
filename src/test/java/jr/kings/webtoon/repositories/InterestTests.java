package jr.kings.webtoon.repositories;

import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import jr.kings.webtoon.domain.Interested;
import lombok.extern.slf4j.Slf4j;

/**
 * InterestTests
 */

@SpringBootTest
@Slf4j
public class InterestTests {

    @Autowired
    private InterestedRepository interestedRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private WebtoonRepository webtoonRepository;

    @Test
    @Transactional
    @Commit
    public void testInsertedInterested(){

        log.info("testInsertInterested.......");

        IntStream.range(1, 35).forEach(i->{

            Interested interested = new Interested();

            interested.setMember(memberRepository.getMember("user"+i));
            interested.setWebtoon(webtoonRepository.getOne(i));

            log.info(""+interestedRepository.save(interested));

        });
    }

    @Test
    public void testGetInterestedWebtoon(){

        log.info("testGetInterestedWebtoon......");

    }

    
}
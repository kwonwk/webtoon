package jr.kings.webtoon.repositories;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import jr.kings.webtoon.domain.Member;
import jr.kings.webtoon.domain.Scrap;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ScrapTests {

    @Autowired
    private ScrapRepository scrapRepository;

    @Test
    @Commit
    @Transactional
    public void testInsert(){
    
        Member member = new Member();
        member.setId("user");
        IntStream.range(1,5).forEach(i -> {
            Scrap scrap = new Scrap();
            scrap.setMember(member);
            scrap.setAlbumName("albumName"+i);
            scrapRepository.save(scrap);
            // log.info(scrapRepository.getScrap(scrap)+"");
        });
    }

    @Test
    @Transactional
    public void selectTest(){
        Scrap scrap = scrapRepository.getScrap("eeee");
        log.info(scrap+"");
    }

    // @Test
    // @Transactional
    // public void selectScrapTest(){
    //     List<Object[]> objArrList = scrapRepository.getScrapAlbumList(19);
    //     objArrList.forEach(objArr -> {
    //         log.info(objArr[0]+"");
    //         log.info(objArr[1]+"");
    //     });
    // }

    @Test
    @Transactional
    public void selectAlbumListTest(){
        Pageable page = PageRequest.of(1, 5);
        log.info(scrapRepository.getAlbumList("user", page)+"");
    }
}
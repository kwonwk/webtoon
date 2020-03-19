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

import jr.kings.webtoon.domain.AlbumFile;
import jr.kings.webtoon.domain.Bookmark;
import jr.kings.webtoon.domain.Interested;
import jr.kings.webtoon.domain.RecentWebtoon;
import jr.kings.webtoon.domain.Scrap;
import lombok.extern.slf4j.Slf4j;

/**
 * InterestTests
 */

@SpringBootTest
@Slf4j
public class MypageTests {

    @Autowired
    private InterestedRepository interestedRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private WebtoonRepository webtoonRepository;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private RecentWebtoonRepository recentWebtoonRepository;

    @Autowired
    private ScrapRepository scrapRepository;

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

        Pageable page = PageRequest.of(0, 10);

        List<Object[]> result = interestedRepository.getInterestedWebtoon("user1",page);

        result.forEach(i->{

            log.info("i[0]: "+i[0]);
            log.info("i[1]: "+i[1]);

        });

        log.info("result.size(): "+result.size());

    }

    @Test
    public void testInsertBookmark(){

        log.info("testInsertBookmark.......");

        IntStream.range(1, 30).forEach(i->{

            Bookmark bookmark = new Bookmark();

            bookmark.setEpisode(episodeRepository.getOne(i));
            bookmark.setMember(memberRepository.getMember("user"+i));

            log.info(""+bookmarkRepository.save(bookmark));

        });

    }


    @Test
    public void testGetBookmarkWebtoonEpisodeList(){

        log.info("testGetBookmarkWebtoonEpisodeList......");

        Pageable page = PageRequest.of(0, 10);

        List<Object[]> result = bookmarkRepository.getBookmarkWebtoonEpisodeList("user1",page);

        result.forEach(i->{

            log.info("i[0]: "+i[0]);
            log.info("i[1]: "+i[1]);
            log.info("i[2]: "+i[2]);

        });

        log.info("result.size(): "+result.size());

    }

    @Test
    public void testInsertRecentWebtoon(){

        log.info("testInsertRecentWebtoon.......");

        IntStream.range(1, 15).forEach(i->{

            RecentWebtoon recentWebtoon = new RecentWebtoon();

            recentWebtoon.setEpisode(episodeRepository.getOne(i));
            recentWebtoon.setMember(memberRepository.getMember("user"+i));

            recentWebtoonRepository.save(recentWebtoon);

        });
    }

    @Test
    public void testGetRecentWebtoonEpisodeWebtoon(){

        log.info("testGetRecentWebtoonEpisodeWebtoon......");

        Pageable page = PageRequest.of(0, 10);

        List<Object[]> result = recentWebtoonRepository.getRecentWebtoonEpisodeWebtoon("user1",page);

        result.forEach(i->{

            log.info("i[0]: "+i[0]);
            log.info("i[1]: "+i[1]);
            log.info("i[2]: "+i[2]);

        });

        log.info("result.size(): "+result.size());

    }

    @Test
    public void testInsertScrapAlbumFile(){

        log.info("testInsertScrapAlbum.......");

        AlbumFile a1 = new AlbumFile();
        a1.setAlbumContent("albumContent1");
        a1.setAlbumFilePath("albumFilePath1");
        
        AlbumFile a2 = new AlbumFile();
        a2.setAlbumContent("albumContent2");
        a2.setAlbumFilePath("albumFilePath2");

        Scrap scrap = new Scrap();
        scrap.setAlbumName("albumName");
        scrap.setMember(memberRepository.getMember("user1"));

        scrap.addAlbumFile(a1);
        scrap.addAlbumFile(a2);

        log.info(""+scrapRepository.save(scrap));

    }

    @Test
    @Transactional
    @Commit
    public void testaddAlbumFile(){

        Scrap scrap = scrapRepository.getOne(1);

        AlbumFile albumFile = new AlbumFile();
        albumFile.setAlbumContent("addAlbumContent");
        albumFile.setAlbumFilePath("addAlbumFilePath");

        scrap.addAlbumFile(albumFile);

        log.info(""+scrapRepository.save(scrap));

    }

    @Test
    @Transactional
    @Commit
    public void testModifyAlbumFile(){

        Scrap scrap = scrapRepository.getOne(1);

        AlbumFile albumFile = new AlbumFile();
        albumFile.setAlbumContent("수정222222222");
        
        scrap.replaceAlbumFile(albumFile);

        log.info(""+scrapRepository.save(scrap));

    }

    @Test
    @Transactional
    public void testgetScrapAlbum(){

        log.info("testgetScrapAlbum.......");

        Pageable page = PageRequest.of(2, 5);

        List<Object[]> result = scrapRepository.getScrapAlbumList(1,page);

        result.forEach(i->{

            log.info("i[0]: "+i[0]);
            log.info("i[1]: "+i[1]);

        });

        log.info("result.size(): "+result.size());
    }

    @Test
    public void testDeleteIntestedWebtoon(){

        log.info("testDeleteIntestedWebtoon.......");

        interestedRepository.deleteById(68);

    }

}
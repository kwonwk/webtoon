package jr.kings.webtoon.repositories;

import java.time.LocalDate;
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
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import jr.kings.webtoon.domain.Episode;
import jr.kings.webtoon.domain.Genre;
import jr.kings.webtoon.domain.Webtoon;
import jr.kings.webtoon.domain.WebtoonFile;
import lombok.extern.slf4j.Slf4j;

/**
 * WebtoonTests
 */

@SpringBootTest
@Slf4j
public class WebtoonTests {

    @Autowired
    private WebtoonRepository webtoonRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    // @Autowired
    // private RecentWebtoonRepository recentWebtoonRepository;

    // @Autowired
    // private InterestedRepository interestedRepository;

 

    // 웹툰 insert
    @Test
    public void testWebtoonInsert(){

        IntStream.range(1, 35).forEach(i -> {
            
            Webtoon webtoon = new Webtoon();

            webtoon.setAuthor("author..." + i);
            webtoon.setThumbnailPath("thumbnailPath..." + i);
            webtoon.setTitle("title..." + i);
            
            if(i<=5){ webtoon.setUpdateDays("MONDAY"); }
            else if(5<i && i<=10){ webtoon.setUpdateDays("TUESDAY"); }    
            else if(10<i && i<=15){ webtoon.setUpdateDays("WEDNESDAY"); }   
            else if(15<i && i<=20){ webtoon.setUpdateDays("THURSDAY"); }   
            else if(20<i && i<=25){ webtoon.setUpdateDays("FRIDAY"); }   
            else if(25<i && i<=30){ webtoon.setUpdateDays("SATURDAY"); } 
            else{ webtoon.setUpdateDays("SUNDAY"); }          
    
            log.info(""+webtoonRepository.save(webtoon));

        });
    }
    
    @Test
    public void testWebtoonListPaging(){

        log.info("testWebtoonListPaging.....");

        Pageable page = PageRequest.of(0,10,Direction.DESC,"wno");
        
        Page<Webtoon> result = webtoonRepository.findAll(page);

        log.info(""+result);

        log.info(""+result.getPageable());
        log.info(""+result.getTotalPages());

        Stream<Webtoon> stream = result.get();

        List<Webtoon> list = stream.collect(Collectors.toList());

        log.info(""+list);

    }

    // 장르 insert
    @Test
    @Transactional
    @Commit
    public void testInsertGenre(){

        log.info("testInsertGenre......");

        IntStream.range(1, 35).forEach(i->{

            Webtoon webtoon = webtoonRepository.getOne(i);

            Genre genre = webtoon.makeGenre();
            
            genre.setGenre("genre...."+i);
            
            log.info(""+genreRepository.save(genre));

        });
    }

    // 에피소드 insert
    @Test
    @Transactional
    @Commit
    public void testInsertEpisode(){

        log.info("testInsertEpisode......");

        IntStream.range(1, 35).forEach(i->{

            Webtoon webtoon = webtoonRepository.getOne(i);

            Episode episode = webtoon.makeEpisode();
            
            episode.setDatano(i);
            episode.setGrade((double)i%12);
            episode.setPageView(i);
            episode.setSubtitle("subtitle" + i);
            episode.setThumbnailPath("thumbnailPath" + i);
            
            log.info(""+episodeRepository.save(episode));

            

        });
    }




    @Test
    public void testGetWebtoonEpisodeGenre(){

        log.info("testGetWebtoonEpisodeGenre......");

        List<Object[]> result = webtoonRepository.getWebtoonEpisodeGenreList();

        result.forEach(i->{

            log.info(""+i[0]);
            log.info(""+i[1]);
            log.info(""+i[2]);

        });

        log.info("result.size(): "+result.size());

    }

    @Test
    public void testGetWebtoonEpisodeGenreByToday(){

        log.info("testGetWebtoonEpisodeGenreByToday......");

        LocalDate days = LocalDate.now();

        String updateDays = days.getDayOfWeek().toString();

        Pageable page = PageRequest.of(0, 10);

        List<Object[]> result = webtoonRepository.getWebtoonEpisodeGenreByTodayList(updateDays, page);

        result.forEach(i->{

            log.info(""+i[0]);
            log.info(""+i[1]);
            log.info(""+i[2]);

        });

        log.info("result.size(): "+result.size());

    }

    @Test
    public void testGetWebtoonEpisodeGenreByNew(){

        log.info("testGetWebtoonEpisodeGenreByNew......");

        Pageable page = PageRequest.of(0, 10);

        List<Object[]> result = webtoonRepository.getWebtoonEpisodeGenreByNewList(page);

        result.forEach(i->{

            log.info("i[0]"+i[0]);
            log.info("i[1]"+i[1]);
            log.info("i[2]"+i[2]);

        });

        log.info("result.size(): "+result.size());

    }

    @Test
    public void testGetWebtoonEpisodeGenreByPopular(){

        log.info("testGetWebtoonEpisodeGenreByPopular......");

        Pageable page = PageRequest.of(0, 10);

        List<Object[]> result = webtoonRepository.getWebtoonEpisodeGenreByPopularList(page);

        result.forEach(i->{

            log.info(""+i[0]);
            log.info(""+i[1]);
            log.info(""+i[2]);

        });

        log.info("result.size(): "+result.size());

    }


    @Test
    public void testInsertWebtoonFile(){

        log.info("testInsertWebtoonFile.......");

        IntStream.range(1, 35).forEach(i->{

            WebtoonFile webtoonFile = new WebtoonFile();

            webtoonFile.setEpisode(episodeRepository.getOne(i));
            webtoonFile.setOcrjson("ocrjson"+i);

            //////////////////////
            //////////////////////
            //////////////////////
            //////////////////////


        });



    }

}
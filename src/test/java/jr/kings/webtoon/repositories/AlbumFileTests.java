package jr.kings.webtoon.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

/**
 * AlbumFileTests
 */
@SpringBootTest
@Slf4j
public class AlbumFileTests {

    @Autowired
    private AlbumFileRepository albumFileRepository;

    @Test
    public void selectTest(){
        log.info(albumFileRepository.getAlbumFiles(19)+"");
    }
    
}
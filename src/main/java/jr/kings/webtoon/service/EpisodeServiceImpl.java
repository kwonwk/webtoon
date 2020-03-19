package jr.kings.webtoon.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jr.kings.webtoon.domain.AlbumFile;
import jr.kings.webtoon.domain.Scrap;
import jr.kings.webtoon.repositories.AlbumFileRepository;
import jr.kings.webtoon.repositories.EpisodeRepository;
import jr.kings.webtoon.repositories.ScrapRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EpisodeServiceImpl implements EpisodeService {

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private ScrapRepository scrapRepository;

    @Autowired
    private AlbumFileRepository albumFileRepository;

    @Override
    public List<Object[]> getShowEpisode(Integer eno) {
        List<Object[]> list = null;
        try {
            list = episodeRepository.getEpisodeWebtoonFileList(eno);
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;

    }

    @Override
    public List<Object[]> getShowReply(Integer eno, Integer pageNum, Integer amount) {
        Pageable page = PageRequest.of(pageNum, amount);
        return episodeRepository.getEpisodeReplyPage(eno, page);
    }

    @Override
    @Transactional
    public void RegisterScrapFile(AlbumFile albumFile) {

        Scrap scrap = scrapRepository.getScrap(albumFile.getScrap().getAlbumName());
        log.info("1");
        if(scrap!=null){
            albumFile.setScrap(scrap);
        }
        albumFileRepository.save(albumFile);
    }

    @Override
    public List<Scrap> getScrapList(String id) {
        return scrapRepository.getAlbumList(id);
    }

    @Override
    public List<Scrap> getScrapList(String id, Integer pageNum) {
        Pageable page = PageRequest.of(pageNum, 5);
        return scrapRepository.getAlbumList(id, page);
    }

    
}
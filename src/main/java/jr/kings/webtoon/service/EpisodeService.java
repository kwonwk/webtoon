package jr.kings.webtoon.service;

import java.util.List;

import jr.kings.webtoon.domain.AlbumFile;
import jr.kings.webtoon.domain.Scrap;

/**
 * EpisodeService
 */

public interface EpisodeService {

    public List<Object[]> getShowEpisode(Integer eno);

    public List<Object[]> getShowReply(Integer eno, Integer page, Integer amount);
    
    public void RegisterScrapFile(AlbumFile albumFile);

    public List<Scrap> getScrapList(String id);

    public List<Scrap> getScrapList(String id, Integer page);
}
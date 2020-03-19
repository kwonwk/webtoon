package jr.kings.webtoon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jr.kings.webtoon.repositories.InterestedRepository;
import jr.kings.webtoon.repositories.WebtoonRepository;

/**
 * WebtoonServiceImpl
 */

@Service
public class WebtoonServiceImpl implements WebtoonService {

    @Autowired
    private WebtoonRepository webtoonRepository;

    @Autowired
    private InterestedRepository interestedRepository;

    @Override
    public List<Object[]> getTodayWebtoon(String updateDays, Pageable page) {

        return webtoonRepository.getWebtoonEpisodeGenreByTodayList(updateDays, page);

    }

    @Override
    public List<Object[]> getNewWebtoon(Pageable page) {

        return webtoonRepository.getWebtoonEpisodeGenreByNewList(page);

    }

    @Override
    public List<Object[]> getPopularWebtoon(Pageable page) {

        return webtoonRepository.getWebtoonEpisodeGenreByPopularList(page);

    }

    @Override
    public List<Object[]> getWebtoon() {

        return webtoonRepository.getWebtoonEpisodeGenreList();

    }

    // 검색 페이지
    @Override
    public List<Object> getSearch(String search) {

        return webtoonRepository.getSearchList(search);

    }

    // 웹툰 조회 페이지
    @Override
    public List<Object[]> getWebtoonList(Integer wno, Pageable page) {

        return webtoonRepository.getWebtoonEpisodeInterestedList(wno, page);

    }

    @Override
    public List<Object[]> getInterestedWebtoon(String id, Integer pageNum) {
        Pageable page = PageRequest.of(pageNum, 10);
        return interestedRepository.getInterestedWebtoon(id, page);
    }

    @Override
    public void deleteInterestedWebtoon(Integer[] ino) {
        
        for(Integer inoList : ino){
            interestedRepository.deleteById(inoList);
        }

    }

    

}
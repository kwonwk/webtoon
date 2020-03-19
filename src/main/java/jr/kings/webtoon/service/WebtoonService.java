package jr.kings.webtoon.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

/**
 * WebtoonService
 */

public interface WebtoonService {
    
    // 메인 화면
    public List<Object[]> getTodayWebtoon(String updateDays, Pageable page);

    public List<Object[]> getNewWebtoon(Pageable page);

    public List<Object[]> getPopularWebtoon(Pageable page);

    public List<Object[]> getWebtoon();

    // 웹툰 검색 화면
    public List<Object> getSearch(String search);

    // 웹툰 리스트 화면
    public List<Object[]> getWebtoonList(Integer wno, Pageable page);

    // 마이페이지 관심 웹툰
    public List<Object[]> getInterestedWebtoon(String id, Integer page);

    // 관심웹툰 삭제
    public void deleteInterestedWebtoon(Integer[] ino);

}
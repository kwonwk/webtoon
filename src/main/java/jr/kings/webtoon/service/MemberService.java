package jr.kings.webtoon.service;

import java.util.List;

import jr.kings.webtoon.domain.AlbumFile;
import jr.kings.webtoon.domain.Interested;
import jr.kings.webtoon.domain.Member;
import jr.kings.webtoon.domain.Scrap;

/**
 * WebtoonService
 */

public interface MemberService {
    
    // webtoonList페이지에서의 관심 웹툰 등록
    public void interestRegister(Interested interest);

    public void albumRegister(Scrap scrap);

    public void albumDelete(Integer albumno);

    public List<AlbumFile> getAlbumFiles(Integer albumno);

    public void deleteAlbumFile(Integer afno);

    public void deleteInterested(Integer ino);

    public void memberRegister(Member member);
}
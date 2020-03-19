package jr.kings.webtoon.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jr.kings.webtoon.domain.AlbumFile;
import jr.kings.webtoon.domain.Authorities;
import jr.kings.webtoon.domain.Interested;
import jr.kings.webtoon.domain.Member;
import jr.kings.webtoon.domain.Scrap;
import jr.kings.webtoon.repositories.AlbumFileRepository;
import jr.kings.webtoon.repositories.AuthoritiesRepository;
import jr.kings.webtoon.repositories.InterestedRepository;
import jr.kings.webtoon.repositories.MemberRepository;
import jr.kings.webtoon.repositories.ScrapRepository;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private InterestedRepository interestRepository;

    @Autowired
    private ScrapRepository scrapRepository;

    @Autowired
    private AlbumFileRepository albumFileRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AuthoritiesRepository authoritiesRepository;
    
    @Autowired
    private PasswordEncoder pwEncoder;

    @Override
    public void interestRegister(Interested interest) {
        interestRepository.save(interest);
    }

    @Override
    public void albumRegister(Scrap scrap) {
        scrapRepository.save(scrap);
    }

    @Override
    public void albumDelete(Integer albumno) {
        scrapRepository.deleteById(albumno);
    }

    @Override
    public List<AlbumFile> getAlbumFiles(Integer albumno) {
        return albumFileRepository.getAlbumFiles(albumno);
    }

    @Override
    public void deleteAlbumFile(Integer afno) {
        albumFileRepository.deleteById(afno);
    }

    @Override
    public void deleteInterested(Integer ino) {
        interestRepository.deleteById(ino);
    }

    @Override
    @Transactional
    public void memberRegister(Member member) {
        member.setPw(pwEncoder.encode(member.getPw()));

        Authorities auth = new Authorities();
        auth.setMember(member);
        auth.setAuthority("USER");
        memberRepository.save(member);
        authoritiesRepository.save(auth);
    }
    
    // @Override
    // public List<Object[]> getAlbumFiles(Integer albumno) {
    //     return scrapRepository.getScrapAlbumList(albumno);
    // }

    


}
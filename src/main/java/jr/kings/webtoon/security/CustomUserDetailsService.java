package jr.kings.webtoon.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jr.kings.webtoon.domain.Authorities;
import jr.kings.webtoon.domain.Member;
import jr.kings.webtoon.repositories.MemberRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * CustomUserDetailsService
 */
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        List<Object[]> list = memberRepository.getMemberAuth(id);
        
        Member member = (Member) list.get(0)[0];
        log.info(member+", userDetailsService...........................");
        UserDetails result = new CustomUser(member);

        return result;
    }
    
}
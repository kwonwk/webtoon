package jr.kings.webtoon.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import jr.kings.webtoon.domain.Member;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class CustomUser extends User {

	private static final long serialVersionUID = 1L;

	private Member member;

	public CustomUser(String username, String password, 
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public CustomUser(Member vo) {

		super(vo.getId(), vo.getPw(), vo.getAuthorities().stream()
				.map(auth -> new SimpleGrantedAuthority("ROLE_"+auth.getAuthority())).collect(Collectors.toList()));
				//.map(auth -> new SimpleGrantedAuthority("ROLE_USER")).collect(Collectors.toList()));
		this.member = vo;

	}
}
